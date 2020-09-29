package bpms;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Κλάση δημιουργίας των labels
//Δέχεται ως όρισμα τις διαστάσεις του κουμπιού, το μέρος που τοποθετείται και το τι αναγράφεται σε αυτό
public class MenuLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	MenuLabel(String string, int coord1, int coord2, int width, int height, JFrame frame) {
		this.setText(string);
		this.setForeground(Color.white);
		setBounds(coord1, coord2, width, height);
		setFont(new Font("Arial", Font.BOLD, 12));
		frame.add(this);
	}
}
