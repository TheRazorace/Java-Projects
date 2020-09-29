package bpms;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;

//Κλάση δημιουργίας των text fields
//Δέχεται ως όρισμα τις διαστάσεις του κουμπιού, το μέρος που τοποθετείται και το τι αναγράφεται σε αυτό
public class MenuTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	MenuTextField(int coord1, int coord2, int width, int height, JFrame frame) {
		setBounds(coord1, coord2, width, height);
		setFont(new Font("Arial", Font.BOLD, 12));
		frame.add(this);
	}
}
