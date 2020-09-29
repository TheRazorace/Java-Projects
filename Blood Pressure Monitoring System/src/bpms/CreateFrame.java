package bpms;

import java.awt.Color;
import javax.swing.JFrame;

//����� ����������� frame

public class CreateFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//Constructor
	//������� �� �������� ��� ����� ��� frame, ��� ���������� ��� ��� ��� ��������� ��� ���� ����� 
	CreateFrame(String title, int size1, int size2, int loc1, int loc2, Color color ){
		super(title);
		setLayout(null);
		//setFont(new Font("Arial", Font.ITALIC, 15));
		getContentPane().setBackground(color);
		setSize(size1, size2);
		setLocation(loc1, loc2);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
