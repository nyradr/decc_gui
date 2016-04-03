package decc_gui.convwin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Lists of messages
 * @author nyradr
 */
public class MessagesBox extends JPanel{
	/**
	 * Place the message at the left of the panel
	 */
	public static final boolean SIDE_LEFT = true;
	
	/**
	 * Place the message at the right of the panel
	 */
	public static final boolean SIDE_RIGHT = false;
	
	
	private ArrayList<Message> messages;
	
	public MessagesBox(){
		super();
		
		messages = new ArrayList<>();
		
		this.setLayout(new GridLayout(10, 1));
	}
	
	/**
	 * Add message into the container
	 * @param message text to display
	 * @param side side of the message, true for left, false for right<br>
	 * 				or use MessagesBox.SIDE_ constants
	 */
	public void addMessage(String message, boolean side){
		Message mess = new Message(message,
				(side)? FlowLayout.LEFT : FlowLayout.RIGHT
						);
		
		this.add(mess);
		messages.add(mess);
	}
}
