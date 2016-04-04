package decc_gui.convwin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * Lists of messages
 * @author nyradr
 */
public class MessagesBox extends JScrollPane{
	/**
	 * Place the message at the left of the panel
	 */
	public static final boolean SIDE_LEFT = true;
	
	/**
	 * Place the message at the right of the panel
	 */
	public static final boolean SIDE_RIGHT = false;
	
	
	private ArrayList<Message> messages;
	private JPanel panel;
	
	public MessagesBox(){
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
		
		messages = new ArrayList<>();
		
		this.setLayout(new ScrollPaneLayout());
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		this.setViewportView(panel);
		
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
		
		panel.add(mess);
		messages.add(mess);
	}
}