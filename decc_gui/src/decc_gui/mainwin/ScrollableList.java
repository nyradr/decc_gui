package decc_gui.mainwin;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;

/**
 * Scrollable list of strings 
 * @author nyradr
 */
public class ScrollableList extends JScrollPane{
	
	protected JList<String> list;
	protected DefaultListModel<String> model;
	
	public ScrollableList(String [] elements){
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
		
		this.setLayout(new ScrollPaneLayout());
		
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		resetElements(elements);
		
		this.setViewportView(list);
	}
	
	/**
	 * replace old elements in the list by new ones
	 * @param elems new list of element
	 */
	public void resetElements(String [] elems){
		model.clear();
		
		for(String s : elems)
			model.addElement(s);
	}
	
	/**
	 * Get the index of the selected element
	 * @return
	 */
	public int getSelectedIndex(){
		return list.getSelectedIndex(); 
	}
	
	/**
	 * Get the value of the selected element
	 * @return
	 */
	public String getSelectedElement(){
		return list.getSelectedValue();
	}
}
