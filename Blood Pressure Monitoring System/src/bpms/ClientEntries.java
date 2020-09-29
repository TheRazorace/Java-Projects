package bpms;

import java.util.LinkedList;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Comparator;

//����� ��� �������� ��� ����������� �������� ��� ��� �������� ����������� ��� ������������
public class ClientEntries {

	// � ���������� ��� ��������� ������� ���� ��� ������ LinkedList ��� ��������
	// �������������� �����
	// � ����� list ����� � ����� ������������ �����������
	// ����������� ���� ���������� ��������� ������� ��� ����� listSorted
	
	public static LinkedList<Data> measurement = new LinkedList<Data>();
	public static LinkedList<Data> clientList = new LinkedList<Data>();
	public static LinkedList<Data> clientListSorted = new LinkedList<Data>();

	// ������ ��� ���������� ��� �������� ��� ���� ��� �����������
	public static String sortType = "�����������";
	public static int sortChoose = 0;

	// ���������� ��� String ��� ����������� ��� ���� ����� ��� ������ ��� �������
	// ��� ����������
	public static String categories = "�������" + "\t\t|" + "��������� ����� (mmHg)" + "\t\t|"
			+ "���������� ����� (mmHg)" + "\t\t|" + "������� ��� �����" + "\t|" + "����������" + "\t|" + "���" + "\n";

	// ������� ��������� ��� ������������ ���� main gui
	// ������� �� ��� ���������� ��� ��� ������ ������������, ������� � ����������
	// ���������� ��� ����������� � �����
	public static void display(String s) {
		BpmsClientGui.log.append("\n����������: " + s + "\n" + categories);
		if (sortChoose == 0) {
			for (Data person : clientList) {
				BpmsClientGui.log.append(person + "\n");
			}
			BpmsClientGui.log.append("\n");
			return;
		} else if (sortChoose == 1) {
			sortId();
		} else if (sortChoose == 2) {
			sortSystolic();
		} else if (sortChoose == 3) {
			sortDiastolic();
		} else if (sortChoose == 4) {
			sortRate();
		}
		for (Data person : clientListSorted) {
			BpmsClientGui.log.append(person + "\n");
		}
		BpmsClientGui.log.append("\n");

	}

	// �� ������������ �������� �� ��� ����� Comparator
	// ����������� -> ������ ������� ������
	public static void sortTime() {
		sortType = "�����������";
		BpmsSortGui.status3Text.setText("� ���������� ����� ������ ����������� ������������");
		sortChoose = 0;
	}

	// B���� ����������� �����
	public static void sortId() {
		clientListSorted.sort(Comparator.comparing(Data::getId));
		BpmsSortGui.status3Text.setText("� ���������� ����� ����������� ����� ������������");
		sortType = "����������";
		sortChoose = 1;
	}

	// B���� ���������� ������
	public static void sortSystolic() {
		clientListSorted.sort(Comparator.comparingInt(Data::getSystolic));
		BpmsSortGui.status3Text.setText("� ���������� ����� ���������� ������ ������������");
		sortType = "������� ��������� �����";
		sortChoose = 2;
	}

	// B���� ����������� ������
	public static void sortDiastolic() {
		clientListSorted.sort(Comparator.comparing(Data::getDiastolic));
		BpmsSortGui.status3Text.setText("� ���������� ����� ����������� ������ ������������");
		sortType = "������� ���������� �����";
		sortChoose = 3;
	}

	// B���� ������� ��� �����
	public static void sortRate() {
		clientListSorted.sort(Comparator.comparing(Data::getRate));
		BpmsSortGui.status3Text.setText("� ���������� ����� ������� ��� ����� ������������");
		sortType = "����� ������� ������� ��� �����";
		sortChoose = 4;
	}

	// ������� ���� �����������
	public static void add(int addType) {
		BpmsEntryGui.statusText.setText("");

		try {
			// �������� � constructor ��� Data
			Data person = new Data(BpmsEntryGui.userIdTextField.getText(), BpmsEntryGui.systolicTextField.getText(),
					BpmsEntryGui.diastolicTextField.getText(), BpmsEntryGui.heartRateTextField.getText());

			// �� ��� ���������� ������ null ���� �������� ��� �� �������� ���� ������ ���
			// ������ �� ����������
			if (person.userId != null) {
				if(addType == 1) {
					clientList.add(person);
					clientListSorted.add(person);
					display(sortType);
				}
				else {
					measurement.add(person);
				}		
				BpmsEntryGui.userIdTextField.setText("");
				BpmsEntryGui.systolicTextField.setText("");
				BpmsEntryGui.diastolicTextField.setText("");
				BpmsEntryGui.heartRateTextField.setText("");
				BpmsEntryGui.statusText.setText("");
			}

		} // ������ ���������
		catch (DataException error) {
			BpmsEntryGui.statusText.setText(error.toString());
		}
		BpmsEntryGui.statusText.setText("");

	}

	// ������� ������� �� �� �������� ����� ��� ��� ����� �� ��� for loop
	private static boolean isInList(String idString) {
		boolean inList = false;

		for (Data person : clientList) {
			if (person.getId().compareToIgnoreCase(idString) == 0) {
				inList = true;
			}
		}
		return inList;
	}

	// ������� ������� ����������� ��� �������� ������������ �����
	public static void find() {
		BpmsSearchGui.status2Text.setText("");

		// ���� ��������� index ������������ � ���� ��� ������������� �����������
		int index;
		int s = 0;

		// ������� �� � ����� ����� ����
		if (clientList.size() == 0) {
			BpmsSearchGui.status2Text.setText("������! ��� �������� ������������.");
		}

		// ������� �� ������� ���������� �� �� ����� ��� ����� � �������
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("������! ��� ������� ���������� �� ���� �� �������.");
		}

		else {

			// ���������� ������ ��� ������ ��������
			BpmsClientGui.log.append("������������ ����������:\n" + categories);
			for (int i = 0; i < clientList.size(); i++) {
				String current = clientList.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// ���������� ����� ��������
					index = i;

					// ������ ��� ��� ����� ��������� ��� ����������� �� ����������� person ���
					// ��������
					for (Data person : clientList) {
						if (s == index) {
							BpmsClientGui.log.append(person + "\n");
							BpmsSearchGui.status2Text.setText("� ���������� ������� ��������.");
							
						}
						s++;
					}
					s = 0;
				}
			}
			BpmsClientGui.log.append("\n");
		}

	}

	// ������� ��������� ���������
	public static void delete() throws IOException {
		BpmsSearchGui.status2Text.setText("");

		// ������� �� � ����� ����� ����
		if (clientList.size() == 0) {
			BpmsSearchGui.status2Text.setText("������! ��� �������� ������������.");
		}

		// '������� �� ������� �� �������
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("������! ��� ������� ���������� �� ���� �� �������.");
		}

		// ���������� ������ ��� ������ ��������
		else {
			for (int i = 0; i < clientList.size(); i++) {
				String current = clientList.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// �������� ��� �������� ��� ��������� ������
					clientList.remove(i);
					clientListSorted.remove(i);
					display(sortType);
					BpmsSearchGui.status2Text.setText("� ���������� ���������� ��������.");

				}
			}BpmsSearchGui.searchTextField.setText("");
			saveToFile();
		}
	}
	
	//���������� ��������� �� ������
	public static void saveToFile() throws IOException {

		if (BpmsClientGui.fileField.getText().length() == 0) {
			BpmsClientGui.log.append("\n������! ����������� �� ����� ��� �������.\n");
		}

		else {
			try {
				FileOutputStream fos = new FileOutputStream(BpmsClientGui.fileField.getText());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(clientList);
				fos.close();
				BpmsClientGui.log.append("\n�� ��������� ������������� �������� ��� Local Book!\n");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	//������� ��������� ��� ������
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {

		// ������� �� � ����� ����� ����
		if (clientList.size() == 0) {
			BpmsClientGui.log.append("\n������! ��� �������� ������������.\n");
		}

		else if (BpmsClientGui.fileField.getText().length() == 0) {
			BpmsClientGui.log.append("\n������! ����������� �� ����� ��� �������.\n");
		}

		try {
			FileInputStream fis = new FileInputStream(BpmsClientGui.fileField.getText());
			ObjectInputStream ois = new ObjectInputStream(fis);
			clientList = (LinkedList<Data>) ois.readObject();
			clientListSorted = clientList;
			ois.close();
			display(sortType);
			BpmsClientGui.log.append("\n�� ��������� ���������� �������� ��� �� Local Book!\n");
		} catch (Exception e) {
			BpmsClientGui.log.append("\n������! ��� ������� ������ �� �����o �����.\n");
			System.out.println(e);
		}

	}
	
	//���������� ���������
	public static void clearBook() throws IOException {
		clientList = new LinkedList<Data>();
		clientListSorted = new LinkedList<Data>();
		saveToFile();
		BpmsClientGui.log.append("\n�� ������ ��������� �������������!\n");
	}
	
	//�������� ��������� ������ ��� ���������� �� ������
	public static void addAndSave() throws IOException {
		add(1);
		saveToFile();
	}
	
	//������� ������� ��������� ��� ��� server
	public static void loadFromServer() throws UnknownHostException, IOException, ClassNotFoundException {
		StartClient.clientGetList();
		saveToFile();
	}
	
	//������� ��������� ���� server
	public static void loadToServer() throws ClassNotFoundException, IOException {
		StartClient.clientSendList();
	}
	
	//�������� �������� ���� server
	public static void addToServer() throws UnknownHostException, IOException {
		add(2);
		StartClient.clientSendMeasurement();
		measurement = new LinkedList<Data>();
	}

}
