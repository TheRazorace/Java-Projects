package bpms;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

//����� ������������ ��� ���������

public class Data implements Serializable{
	
	//����������� ��� ��������� �� ����� String
	//������������� ��� ������� LocalDate ��� LocalTime ��� ������ ����������� ������� ����������� ��� ����
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private LocalTime time;
	
	private String systolicBp;
	private String diastolicBp;
	private String heartRate;
	public  String userId;
	private String dateString;
	private String timeString;
	
	//Boolean ��������� ��� ������ ����������� ��� ��������� ��� ���������� ��� ��� ������
	private boolean correctData;
	
	//Constructor no.1 ��� ����� ���� Strings ��� ��������
	public Data() {
		dateString = "";
		timeString = "";
		systolicBp = "";
		diastolicBp = "";
		heartRate = "";
		userId = "";
	}
	
	//Constructor no.2 ��� ����� ��� �������� ��� ����� ��� ����� � �������
	public Data(String userId, String systolicBp, String diastolicBp, String heartRate) throws DataException{
		
		//� ���� ��� correctData ����������� ��� ��� ��������� dataEvaluation
		correctData = dataEvaluation(userId, systolicBp, diastolicBp, heartRate);
		
		//�� �� �������� ����� ������ � constructor ������ �� ��������� ��� �� ����������� ��� �����
		if (correctData == true) {
			this.userId = userId;
			this.systolicBp = systolicBp;
			this.diastolicBp = diastolicBp;
			this.heartRate = heartRate;
			
			//������ ����������� ����������� ��� ����...
			date = LocalDate.now();
			time = LocalTime.now();
			
			//... ��� ��������� �� ��������� ����� �� ��� ����� DateTimeFormatter
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.dateString = date.format(formatter1);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
			this.timeString = time.format(formatter2);
		}	
	}
	
	//��������� ��� �������� ��� ���������� ��� ��������� ��� ��������
	private boolean dataEvaluation(String userId, String systolicBp, String diastolicBp, String heartRate) {
		
		//������� ����� ������� ��� �������� �� ��� ��������� trim
		userId.trim();
		systolicBp.trim();
		diastolicBp.trim();
		heartRate.trim();
		
		//� ��������� permit ������ ��� ����������
		//������ ������� true
		//�� ������� ������ �����, ������� false ��� ��������� ���� ������ � ����� ��� �� �������� ����� �� ������
		boolean permit = true;
		
		//������� �� ������ ��� �� ����� ��������� ����� ����
		if((userId.length() == 0) || (systolicBp.length() == 0) ||
				(diastolicBp.length() == 0) || (heartRate.length() == 0)){
			permit = false;
			BpmsEntryGui.statusText.setText("������! �� ����� ��� ������ �� ����� ����.");	
		}
		
		//������� �� �� ������� �������� ������� ���� ��������. �� ���, ����� �� ������
		char[] idChars = userId.toCharArray();
	    for(char c : idChars){
	        if(!Character.isLetter(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("������! �� ������� ������ �� �������� ���� ��������.");
	        }
	    }
	    
	    //������� �� �� ������� ���������� ������ ������� ���� ��������. �� ���, ����� �� ������
		char[] sysChars = systolicBp.toCharArray();
	    for(char c : sysChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("������! � ����� ������ �� ����������� �� ��������.");
	        }
	    }
	    
	    //������� �� �� ������� ����������� ������ ������� ���� ��������. �� ���, ����� �� ������
		char[] diaChars = diastolicBp.toCharArray();
	    for(char c : diaChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("������! � ����� ������ �� ����������� �� ��������.");
	        }
	    }
	    
	    //������� �� �� ������� ������� ��� ����� ������� ���� ��������. �� ���, ����� �� ������
		char[] rateChars = heartRate.toCharArray();
	    for(char c : rateChars){
	        if(!Character.isDigit(c)) {
	        	permit = false;
	        	BpmsEntryGui.statusText.setText("������! �� ������� ������ �� ������������ �� ��������.");
	        }
	    }
	    
	    //��������� ��� ���������� 
	    return permit;
	}
	
	//Get ��� Set �����������
	//����������� ��� ��� sort �������� ��� ������� ����� ��� ����������� ��������
	public int getSystolic() {
		return Integer.parseInt(systolicBp);
	}
	
	public int getDiastolic() {
		return Integer.parseInt(diastolicBp);
	}
	
	public int getRate() {
		return Integer.parseInt(heartRate);
	}
	
	public String getId() {
		return userId.toUpperCase();
	}
	
	public void setSystolic(String systolicBp) {
		this.systolicBp = systolicBp;
	}
	
	public void setDiastolic(String diastolicBp) {
		this.diastolicBp = diastolicBp;
	}
	
	public void setRate(String heartRate) {
		this.heartRate = heartRate;
	}
	
	public void setId(String userId) {
		this.userId = userId;
	}
	
	//������� ��� ��������� �������� ��� ������������ ��� ��������������� ������ ���� ����� ������������� �� ��������
	public String toString() {
		return userId + "\t\t\t|" + systolicBp + "\t\t\t\t|" + diastolicBp + "\t\t\t\t|" + heartRate + "\t\t\t|" + dateString + "\t|" + timeString ;
	}
}
