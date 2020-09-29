package bpms;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

//����� ����������� ��� ��������
//������� �� ������ ��� ���������� ��� ��������, �� ����� ��� ������������ ��� �� �� ����������� �� ����
public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;

	MenuButton(String digit, int coord1, int coord2, int width, int height, JFrame frame) {
		super(digit);
		setBounds(coord1, coord2, width, height);
		setFont(new Font("Arial", Font.BOLD, 12));
		frame.add(this);
	}
}
