import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame; 

public class KlasaKryesore {

	public static void main(String[] args) {
 
		JFrame frame = new JFrame("Linja Game");
		frame.setSize(1100, 700);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  


		
		frame.setMinimumSize(new Dimension(1100, 700));
		frame.setVisible(true);

		 
	}

}