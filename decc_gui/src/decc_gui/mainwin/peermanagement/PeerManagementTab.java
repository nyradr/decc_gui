package decc_gui.mainwin.peermanagement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import decc.ui.IDecc;
import decc_gui.mainwin.ScrollableList;

/**
 * Main class for peer management tab
 * @author nyradr
 */
public class PeerManagementTab extends JPanel{
	
	private IDecc decc;
	
	ScrollableList pl;
	
	JButton remPeer;
	JButton addPeer;
	
	public PeerManagementTab(IDecc decc){
		super();
		
		this.decc = decc;
		
		build();
		buildCommandBox();
	}
	
	private void build(){
		this.setLayout(new BorderLayout());
		
		pl = new ScrollableList(decc.getConnectedHosts());
		
		this.add(pl, BorderLayout.CENTER);
	}
	
	private void buildCommandBox(){
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BorderLayout());
		
		// remove peer button
		remPeer = new JButton("Remove peer");
		remPeer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pl.getSelectedElement() != null){	// if selected element -> remove into decc
					decc.disconnect(pl.getSelectedElement());
					pl.resetElements(decc.getConnectedHosts());
				}
			}
		});
		commandPanel.add(remPeer, BorderLayout.NORTH);
		
		// add peer button
		addPeer = new JButton("Add peer");
		addPeer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// show input dialog for enter a ip(or host name)
				String host = JOptionPane.showInputDialog("Type host name or IP of the peer to add");
				
				// try connect and refresh display
				if(decc.connect(host))
					refresh();
				else
					JOptionPane.showMessageDialog(null, "Impossible to reach the target", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		commandPanel.add(addPeer, BorderLayout.SOUTH);
		
		this.add(commandPanel, BorderLayout.EAST);
	}
	
	private void refresh(){
		pl.resetElements(decc.getConnectedHosts());
	}
	
	public void newPeer(){
		refresh();
	}
	
	public void peerdeco(){
		refresh();
	}
}
