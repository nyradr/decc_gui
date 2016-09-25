package decc_gui.mainwin;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import decc.DeccBuilder;
import decc.accounts.Account;
import decc.options.Crypto;
import decc.ui.IDecc;
import decc.ui.IDeccUser;
import decc_gui.mainwin.convmanag.ConvManagementTab;
import decc_gui.mainwin.logstab.LogTabPanel;
import decc_gui.mainwin.peermanagement.PeerManagementTab;

/**
 * Main window for DECC management
 * @author nyradr
 *
 */
public class MainWin extends JFrame implements IDeccUser{
	
	private IDecc decc;
	
	private JTabbedPane tabs;
	private LogTabPanel logTab;
	private PeerManagementTab peersTab;
	private ConvManagementTab convTab;
	
	public MainWin(){
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				decc.close();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		try {
			Account acc = Account.create("decccli", Crypto.DEF_RSA_LEN);
			
			decc = DeccBuilder.getDecc(4242, acc, this);
			decc.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		this.setSize(400,  200);
		this.setLocationRelativeTo(null);
		
		build();
	}
	
	private void build(){
		tabs = new JTabbedPane();
		this.getContentPane().add(tabs, BorderLayout.CENTER);
		
		peersTab = new PeerManagementTab(decc);
		tabs.add("Peers", peersTab);
		
		convTab = new ConvManagementTab(decc);
		tabs.add("Conversation", convTab);
		
		logTab = new LogTabPanel();
		tabs.add("Log", logTab);
	}

	@Override
	public void onNewPeer(String host) {
		logTab.log("New peer connected : " + host);
		peersTab.newPeer();
	}

	@Override
	public void onPeerDeco(String host) {
		logTab.log("Peer disconected : " + host);
		peersTab.peerdeco();
	}

	@Override
	public void onNewCom(String comid) {
		logTab.log("New communication opened with COMID : " + comid);
		convTab.newConv(comid);
	}

	@Override
	public void onComEnd(String comid) {
		logTab.log("Communication with COMID " + comid + " has end");
		convTab.endConv(comid);
	}
	
	@Override
	public void onComFail(String comid, String target) {
		logTab.log("Failed to establish a communicatino with : " + target);
		convTab.failCom(comid, target);
	}

	@Override
	public void onNewRoad(String comid, String hosta, String hostb) {
		logTab.log("New road traced with COMID " + comid + " from " + hosta + " to " + hostb);
	}

	@Override
	public void onEroute(String comid, String hosta, String hostb) {
		logTab.log("Road with COMID " + comid + " from " + hosta + " to " + hostb + " is now destroyed");
	}

	@Override
	public void onMess(String comid, String mess, boolean verified) {
		logTab.log("Message from the COMID " + comid + " received : " + mess);
		convTab.onMess(comid, mess);
	}

}
