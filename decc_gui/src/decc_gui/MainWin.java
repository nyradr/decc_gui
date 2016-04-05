package decc_gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import decc.DeccInstance;
import decc.IDeccUser;
import decc_gui.mainwin.logstab.LogTabPanel;
import decc_gui.mainwin.logstab.LogTextArea;

/**
 * Main window for DECC management
 * @author nyradr
 *
 */
public class MainWin extends JFrame implements IDeccUser{
	
	private DeccInstance decc;
	
	private JTabbedPane tabs;
	private LogTabPanel logTab;
	
	public MainWin(){
		super();
		
		this.setSize(400,  200);
		
		build();
		
		try {
			decc = new DeccInstance(4242, "Foo", this);
		} catch (IOException e) {
			logTab.log(e.getMessage());
		}
		
	}
	
	private void build(){
		tabs = new JTabbedPane();
		this.getContentPane().add(tabs, BorderLayout.CENTER);
		
		logTab = new LogTabPanel();
		tabs.add("Log", logTab);
		
		
	}

	@Override
	public void onNewPeer(String host) {
		logTab.log("New peer connected : " + host);
	}

	@Override
	public void onPeerDeco(String host) {
		logTab.log("Peer disconected : " + host);
	}

	@Override
	public void onNewCom(String comid) {
		logTab.log("New communication opened with COMID : " + comid);
	}

	@Override
	public void onComEnd(String comid) {
		logTab.log("Communication with COMID " + comid + " has end");
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
	public void onMess(String comid, String mess) {
		logTab.log("Message from the COMID " + comid + " received : " + mess);
	}
}
