package bpms;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

//Gui ��� ��������� �����������

public class BpmsSearchGui extends JFrame{
	
	//������������ ��� ����������� ��������, labels ��� text fields
	
	private static final long serialVersionUID = 1L;
	public static JFrame searchFrame;
	
	public static JLabel         searchLabel     = new JLabel ("��������� ��������:     ");
	public static JTextField   searchTextField   = new JTextField(10);
	
	public static JButton       searchButton     = new JButton ("���������");
	public static JButton       clear2Button     = new JButton ("����������");	
	public static JLabel        status2Text      = new JLabel ("   ");
	
	//Constructor
	public BpmsSearchGui(int userType, boolean del) {
		//���������� ��� frame
		if (del == true) searchFrame = new CreateFrame("Delete Entry", 360, 150, 780, 450, Color.lightGray);
		else searchFrame = new CreateFrame("Search Entry", 360, 150, 780, 450, Color.lightGray);
		
		//���������� layout ��� ���������� ��������, labels ��� text fields
		searchFrame.getContentPane().setLayout(new FlowLayout());
		searchFrame.getContentPane().add(searchLabel);
		searchFrame.getContentPane().add(searchTextField);
		
		//���������� ��������� ��� ��������
		//�� boolean del == true ����������� � ���������� ��� ����������� 
		//������ ��������� ��� text log ���� ��� ����������� �������
		
		if (del == true) {
			searchButton = new JButton ("��������");
			searchFrame.getContentPane().add(searchButton);
			if(userType == 1) {
				searchButton.addActionListener(event -> {
					try {
						Entries.delete();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
			else if(userType == 2){
				searchButton.addActionListener(event -> {
					try {
						ClientEntries.delete();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
			else {
				searchButton.addActionListener(event -> 
				{
					try {
						StartClient.clientDeleteServerEntry();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			);
		}
		}
		else {
			searchButton = new JButton ("���������");
			searchFrame.getContentPane().add(searchButton);
			if(userType == 1) {
				searchButton.addActionListener(event -> Entries.find());
			}
			else if(userType == 2){
				searchButton.addActionListener(event -> ClientEntries.find());
			}
			else {
				searchButton.addActionListener(event -> 
					{
						try {
							StartClient.clientSendEntryName();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				);
			}
		}
		
		searchFrame.getContentPane().add(clear2Button);
		searchFrame.getContentPane().add(status2Text);
		
		clear2Button.addActionListener(event -> clear());
		
	}
	
	//��������� ���������� ��� ������
	private void clear() {
		searchTextField.setText("");
	}
}
