package bpms;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

//Gui ��� ���������� ���������

public class BpmsEntryGui extends JFrame{
	
	//������������ ��� ����������� ��������, labels ��� text fields
	
	private static final long serialVersionUID = 1L;
	public static JFrame entryFrame;
	
	public static JLabel       systolicLabel     = new JLabel ("  ��������� �����:     ");
	public static JLabel       diastolicLabel    = new JLabel ("  ���������� �����:   ");
	public static JLabel       heartRateLabel    = new JLabel ("  ������� ��� �����: ");
	public static JLabel        userIdLabel      = new JLabel ("        �������:            ");
	
	public static JTextField  systolicTextField  = new JTextField(10);
	public static JTextField  diastolicTextField = new JTextField(10);
	public static JTextField  heartRateTextField = new JTextField(10);
	public static JTextField  userIdTextField    = new JTextField(10);
	
	public static JButton       submitButton     = new JButton ("����������");
	public static JButton       clearButton      = new JButton ("����������");
	public static JLabel         statusText      = new JLabel ("   ");
	
	//Constructor 
	public BpmsEntryGui(int userType) {
		//���������� ��� frame
		entryFrame = new CreateFrame("Data Entry", 360, 250, 780, 450, Color.lightGray);
		
		//���������� layout ��� ���������� ��������, labels ��� text fields
		entryFrame.getContentPane().setLayout(new FlowLayout());
		entryFrame.getContentPane().add(userIdLabel);
		entryFrame.getContentPane().add(userIdTextField);
		entryFrame.getContentPane().add(systolicLabel);
		entryFrame.getContentPane().add(systolicTextField);
		entryFrame.getContentPane().add(diastolicLabel);
		entryFrame.getContentPane().add(diastolicTextField);
		entryFrame.getContentPane().add(heartRateLabel);
		entryFrame.getContentPane().add(heartRateTextField);
		entryFrame.getContentPane().add(submitButton);
		entryFrame.getContentPane().add(clearButton);
		entryFrame.getContentPane().add(statusText);
		
		//���������� ��������� ��� ��������
		
		if(userType == 1) {
			submitButton.addActionListener(event -> {
				try {
					ClientEntries.addToServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else {
			submitButton.addActionListener(event -> {
				try {
					ClientEntries.addAndSave();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		clearButton.addActionListener(event -> clear());
	}
	
	//��������� ���������� ��� ������
	private void clear() {
		userIdTextField.setText("");
		systolicTextField.setText("");
		diastolicTextField.setText("");
		heartRateTextField.setText("");
	}
}
