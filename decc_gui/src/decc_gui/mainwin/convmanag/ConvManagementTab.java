package decc_gui.mainwin.convmanag;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import decc.DeccInstance;
import decc.ICom;
import decc_gui.mainwin.ScrollableList;

/**
 * Main class for conversation management
 * @author nyradr
 */
public class ConvManagementTab extends JPanel{
	
	private DeccInstance decc;
	
	private ScrollableList convList;
	
	private JButton closeConv;
	private JButton openConv;
	private JButton changeName;
	
	public ConvManagementTab(DeccInstance decc){
		super();
		
		this.decc = decc;
		
		build();
	}
	
	private void build(){
		this.setLayout(new BorderLayout());
		
		convList = new ScrollableList(buildListElements());
		this.add(convList, BorderLayout.CENTER);
		
		JPanel cmdP = new JPanel();
		cmdP.setLayout(new BorderLayout());
		
		changeName = new JButton("Change name");
		changeName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nname = JOptionPane.showInputDialog("New name");
				
				decc.setname(nname);
			}
		});
		cmdP.add(changeName);
		
		
		closeConv = new JButton("Close conversation");
		closeConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(convList.getSelectedElement() != null)
					decc.closeCom(decc.getComs()[convList.getSelectedIndex()].getComid());
				
				convList.resetElements(buildListElements());
			}
		});
		
		cmdP.add(closeConv, BorderLayout.NORTH);
		
		openConv = new JButton("Open new conversation");
		openConv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String target = JOptionPane.showInputDialog("Name of the target to reach");
				
				decc.roadTo(target);
				
				convList.resetElements(buildListElements());
			}
		});
		
		cmdP.add(openConv, BorderLayout.SOUTH);
		
		this.add(cmdP, BorderLayout.EAST);
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
}
