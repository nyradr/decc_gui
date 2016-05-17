package decc_gui.convwin;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Box for message storage and draw
 * @author nyradr
 */
class Message extends JPanel{
	
	/**
	 * Message to display
	 */
	private String message;
	
	private JLabel lbl_mess;
	
	/**
	 * @param message message to display
	 * @param align FlowLayout alignment
	 */
	public Message(String message, int align){
		super();
		
		this.message = message;
		
		this.setLayout(new FlowLayout(align));
		build();
	}
	
	private void build(){
		lbl_mess = new JLabel(this.message);
		this.add(lbl_mess);
	}
}
