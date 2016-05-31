package decc_gui.mainwin.convmanag;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import decc.ui.ICom;
import decc.ui.IDecc;
import decc_gui.convwin.ConvWin;
import decc_gui.mainwin.ScrollableList;

/**
 * Main class for conversation management
 * @author nyradr
 */
public class ConvManagementTab extends JPanel{
	
	private IDecc decc;
	
	private ScrollableList convList;
	
	private JButton closeConv;
	private JButton openConv;
	private JButton changeName;
	
	private Map<String, ConvWin> convwins;
	
	public ConvManagementTab(IDecc decc){
		super();
		
		this.decc = decc;
		convwins = new TreeMap<>();
		
		build();
	}
	
	private void build(){
		this.setLayout(new BorderLayout());
		
		convList = new ScrollableList(buildListElements());
		this.add(convList, BorderLayout.CENTER);
		
		JPanel cmdP = new JPanel();
		cmdP.setLayout(new BorderLayout());
		
		// changing name
		changeName = new JButton("Change name");
		changeName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nname = JOptionPane.showInputDialog("New name");
				
				decc.setname(nname);
			}
		});
		cmdP.add(changeName);
		
		// close conversation
		closeConv = new JButton("Close conversation");
		closeConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(convList.getSelectedElement() != null){
					decc.closeCom(decc.getComs()[convList.getSelectedIndex()].getComid());
				}
			}
		});
		
		cmdP.add(closeConv, BorderLayout.NORTH);
		
		// open new conversation
		openConv = new JButton("Open new conversation");
		openConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String target = JOptionPane.showInputDialog("Name of the target to reach");
				
				decc.startCom(target);
			}
		});
		
		cmdP.add(openConv, BorderLayout.SOUTH);
		
		this.add(cmdP, BorderLayout.EAST);
	}
	
	private void refresh(){
		convList.resetElements(buildListElements());
	}
	
	/**
	 * Build string array for convList elements
	 * @return
	 */
	private String [] buildListElements(){
		ArrayList<String> elems = new ArrayList<>();
		
		for(ICom ic : decc.getComs())
			elems.add(ic.getTargetName());
		
		return elems.toArray(new String[0]);
	}
	
	public void newConv(String comid){
		ConvWin cv = new ConvWin(decc, decc.getCom(comid));
		cv.setVisible(true);
		convwins.put(comid, cv);
		refresh();
	}
	
	public void onMess(String comid, String mess){
		ICom cm = decc.getCom(comid);
		
		if(cm != null){
			convwins.get(comid).receiveMessage(mess);
		}
	}
	
	public void endConv(String comid){
		ConvWin cv = convwins.get(comid);
		
		if(cv != null){
			cv.setVisible(false);
			convwins.remove(comid);
		}
		
		refresh();
	}
	
	public void failCom(String comid, String target){
		JOptionPane.showMessageDialog(null, "Unable to make a communication with : " + target);
	}
}
