package decc_gui;

import javax.swing.JFrame;

import decc.IDeccUser;

/**
 * Main window for DECC management
 * @author nyradr
 *
 */
public class MainWin extends JFrame implements IDeccUser{
	
	public MainWin(){
		super();
	}

	@Override
	public void onNewPeer(String host) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPeerDeco(String host) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNewCom(String comid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComEnd(String comid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNewRoad(String comid, String hosta, String hostb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEroute(String comid, String hosta, String hostb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMess(String comid, String mess) {
		// TODO Auto-generated method stub
		
	}
}
