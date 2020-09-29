package bpms;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;

//����� ����������� ��� text fields
//������� �� ������ ��� ���������� ��� ��������, �� ����� ��� ������������ ��� �� �� ����������� �� ����
public class MenuTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	MenuTextField(int coord1, int coord2, int width, int height, JFrame frame) {
		setBounds(coord1, coord2, width, height);
		setFont(new Font("Arial", Font.BOLD, 12));
		frame.add(this);
	}
}
