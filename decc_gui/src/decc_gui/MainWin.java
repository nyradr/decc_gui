package decc_gui;

import java.io.IOException;

import javax.swing.JFrame;

import decc.DeccInstance;
import decc.IDeccUser;
import decc_gui.mainwin.LogTextArea;

/**
 * Main window for DECC management
 * @author nyradr
 *
 */
public class MainWin extends JFrame implements IDeccUser{
	
	private DeccInstance decc;
	
	private LogTextArea lta;

	
	public MainWin(){
		super();
		
		try {
			decc = new DeccInstance(4242, "Foo", this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setSize(400,  200);
		
		build();
	}
	
	private void build(){
		
		
		lta = new LogTextArea();
		this.getContentPane().add(lta);
		
		lta.log("hello");
	}

	@Override
	public void onNewPeer(String host) {
		lta.log("New peer connected : " + host);
	}

	@Override
	public void onPeerDeco(String host) {
		lta.log("Peer disconected : " + host);
	}

	@Override
	public void onNewCom(String comid) {
		lta.log("New communication opened with COMID : " + comid);
	}

	@Override
	public void onComEnd(String comid) {
		lta.log("Communication with COMID " + comid + " has end");
	}

	@Override
	public void onNewRoad(String comid, String hosta, String hostb) {
		lta.log("New road traced with COMID " + comid + " from " + hosta + " to " + hostb);
	}

	@Override
	public void onEroute(String comid, String hosta, String hostb) {
		lta.log("Road with COMID " + comid + " from " + hosta + " to " + hostb + " is now destroyed");
	}

	@Override
	public void onMess(String comid, String mess) {
		lta.log("Message from the COMID " + comid + " received : " + mess);
	}
}
