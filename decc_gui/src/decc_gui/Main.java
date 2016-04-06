package decc_gui;

import java.awt.EventQueue;

public class Main {
	public static void main(String [] args){
		EventQueue.invokeLater(new Runnable() {
	        
            @Override
            public void run() {
                MainWin ex = new MainWin();
                ex.setVisible(true);
            }
        });
	}
}
