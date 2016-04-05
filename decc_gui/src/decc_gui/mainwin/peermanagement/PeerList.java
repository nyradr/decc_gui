package decc_gui.mainwin.peermanagement;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;

/**
 * Represent a scalable list of peers
 * @author nyradr
 */
public class PeerList extends JScrollPane{
	
	private JList<String> list;
	private DefaultListModel<String> elements;
	
	public PeerList(String[] peers){
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
		
		
		build(peers);
	}
	
	private void build(String [] peers){
		this.setLayout(new ScrollPaneLayout());
		
		elements = new DefaultListModel<>();
		list = new JList<>(peers);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		this.setViewportView(list);
	}
	
	/**
	 * Get the selected element in the list
	 * @return ip selected
	 */
	public String getSelectedPeer(){
		return list.getSelectedValue();
	}
	
	/**
	 * Reset all element of the list by the ips elements
	 * @param ips array of string 
	 */
	public void resetValues(String [] ips){
		elements.clear();
		
		for(String s : ips)
			elements.addElement(s);
	}
}
