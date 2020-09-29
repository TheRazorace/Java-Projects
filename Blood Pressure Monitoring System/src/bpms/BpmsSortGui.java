package bpms;

import java.awt.*;
import javax.swing.*;

//Gui ��� ������� ������ �����������

public class BpmsSortGui extends JFrame{
	
	//������������ ��� ����������� ��������, labels ��� text fields
	
	private static final long serialVersionUID = 1L;
	public static JFrame sortFrame;
	
	public static JButton      okButton       = new JButton (" OK ");
	public static JButton     sortTime        = new JButton ("���������� ����� ������ �����������");
	public static JButton      sortId         = new JButton ("���������� ����� ����������� ������");
	public static JButton    sortSystolic     = new JButton ("���������� ����� ���������� �����");
	public static JButton    sortDiastolic    = new JButton ("���������� ����� ����������� �����");
	public static JButton      sortRate       = new JButton ("���������� ����� ������� ��� �����");
	
	public static JLabel   status3Text   = new JLabel ("    ");
	
	//Constructor
	public BpmsSortGui(int userType) {
		//���������� ��� frame
		sortFrame = new CreateFrame("List Sort", 345, 270, 780, 450, Color.lightGray);
		
		//���������� layout ��� ���������� ��������, labels ��� text fields
		sortFrame.getContentPane().setLayout(new FlowLayout());
		sortFrame.getContentPane().add(sortTime);
		sortFrame.getContentPane().add(sortId);
		sortFrame.getContentPane().add(sortSystolic);
		sortFrame.getContentPane().add(sortDiastolic);
		sortFrame.getContentPane().add(sortRate);	
		sortFrame.getContentPane().add(status3Text);
		sortFrame.getContentPane().add(okButton);
		
		//���������� ��������� ��� ��������
		//���� ������ ����������� �� ��� ����������� ������ ����������� ��� ���������
		if(userType == 1) {
			sortTime.addActionListener(event -> Entries.sortTime());
			sortId.addActionListener(event -> Entries.sortId());
			sortSystolic.addActionListener(event -> Entries.sortSystolic());
			sortDiastolic.addActionListener(event -> Entries.sortDiastolic());
			sortRate.addActionListener(event -> Entries.sortRate());
		}
		else {
			sortTime.addActionListener(event -> ClientEntries.sortTime());
			sortId.addActionListener(event -> ClientEntries.sortId());
			sortSystolic.addActionListener(event -> ClientEntries.sortSystolic());
			sortDiastolic.addActionListener(event -> ClientEntries.sortDiastolic());
			sortRate.addActionListener(event -> ClientEntries.sortRate());
		}
		
		okButton.addActionListener(event -> sortFrame.dispose());
	}
}
