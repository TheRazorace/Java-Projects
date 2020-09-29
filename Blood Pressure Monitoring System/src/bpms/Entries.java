package bpms;

import java.util.LinkedList;
import java.io.*;
import java.io.IOException;
import java.util.Comparator;

//����� ��� �������� ��� ����������� �������� ��� ��� �������� ����������� ��� ������������
public class Entries {

	// � ���������� ��� ��������� ������� ���� ��� ������ LinkedList ��� ��������
	// �������������� �����
	// � ����� list ����� � ����� ������������ �����������
	// ����������� ���� ���������� ��������� ������� ��� ����� listSorted

	public static LinkedList<Data> temp = new LinkedList<Data>();
	public static LinkedList<Data> list = new LinkedList<Data>();
	public static LinkedList<Data> listSorted = new LinkedList<Data>();

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
		BpmsServerGui.log.append("\n����������: " + s + "\n" + categories);
		if (sortChoose == 0) {
			for (Data person : list) {
				BpmsServerGui.log.append(person + "\n");
			}
			BpmsServerGui.log.append("\n");
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
		for (Data person : listSorted) {
			BpmsServerGui.log.append(person + "\n");
		}
		BpmsServerGui.log.append("\n");

	}

	// �� ������������ �������� �� ��� ����� Comparator
	// ����������� -> ������ �������� ������
	public static void sortTime() {
		sortType = "�����������";
		BpmsSortGui.status3Text.setText("� ���������� ����� ������ ����������� ������������");
		sortChoose = 0;
	}

	// B���� ����������� �����
	public static void sortId() {
		listSorted.sort(Comparator.comparing(Data::getId));
		BpmsSortGui.status3Text.setText("� ���������� ����� ����������� ����� ������������");
		sortType = "����������";
		sortChoose = 1;
	}

	// B���� ���������� ������
	public static void sortSystolic() {
		listSorted.sort(Comparator.comparingInt(Data::getSystolic));
		BpmsSortGui.status3Text.setText("� ���������� ����� ���������� ������ ������������");
		sortType = "������� ��������� �����";
		sortChoose = 2;
	}

	// B���� ����������� ������
	public static void sortDiastolic() {
		listSorted.sort(Comparator.comparing(Data::getDiastolic));
		BpmsSortGui.status3Text.setText("� ���������� ����� ����������� ������ ������������");
		sortType = "������� ���������� �����";
		sortChoose = 3;
	}

	// B���� ������� ��� �����
	public static void sortRate() {
		listSorted.sort(Comparator.comparing(Data::getRate));
		BpmsSortGui.status3Text.setText("� ���������� ����� ������� ��� ����� ������������");
		sortType = "����� ������� ������� ��� �����";
		sortChoose = 4;
	}

	// ������� ���� �����������
	public static void add() {
		BpmsEntryGui.statusText.setText("");

		try {
			// �������� � constructor ��� Data
			Data person = new Data(BpmsEntryGui.userIdTextField.getText(), BpmsEntryGui.systolicTextField.getText(),
					BpmsEntryGui.diastolicTextField.getText(), BpmsEntryGui.heartRateTextField.getText());

			// �� ��� ���������� ������ null ���� �������� ��� �� �������� ���� ������ ���
			// ������ �� ����������
			if (person.userId != null) {
				list.add(person);
				listSorted.add(person);
				display(sortType);
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

		for (Data person : list) {
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
		if (list.size() == 0) {
			BpmsSearchGui.status2Text.setText("������! ��� �������� ������������.");
		}

		// ������� �� ������� ���������� �� �� ����� ��� ����� � �������
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("������! ��� ������� ���������� �� ���� �� �������.");
		}

		else {

			// ���������� ������ ��� ������ ��������
			BpmsServerGui.log.append("������������ ����������:\n" + categories);
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// ���������� ����� ��������
					index = i;

					// ������ ��� ��� ����� ��������� ��� ����������� �� ����������� person ���
					// ��������
					for (Data person : list) {
						if (s == index) {
							BpmsServerGui.log.append(person + "\n");
							BpmsSearchGui.status2Text.setText("� ���������� ������� ��������.");

						}
						s++;
					}
					s = 0;
				}
			}
			BpmsServerGui.log.append("\n");
		}

	}

	// ������� ��������� ���������
	public static void delete() throws IOException {
		BpmsSearchGui.status2Text.setText("");

		// ������� �� � ����� ����� ����
		if (list.size() == 0) {
			BpmsSearchGui.status2Text.setText("������! ��� �������� ������������.");
		}

		// '������� �� ������� �� �������
		else if (isInList(BpmsSearchGui.searchTextField.getText()) == false) {
			BpmsSearchGui.status2Text.setText("������! ��� ������� ���������� �� ���� �� �������.");
		}

		// ���������� ������ ��� ������ ��������
		else {
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// �������� ��� �������� ��� ��������� ������
					list.remove(i);
					listSorted.remove(i);
					display(sortType);
					BpmsSearchGui.status2Text.setText("� ���������� ���������� ��������.");

				}
			}
			BpmsSearchGui.searchTextField.setText("");
			saveToFile();
		}
	}
	
	//���������� ��������� �� ������
	public static void saveToFile() throws IOException {

		if (BpmsServerGui.fileField.getText().length() == 0) {
			BpmsServerGui.log.append("\n������! ����������� �� ����� ��� �������.\n");
		}

		else {
			try {
				FileOutputStream fos = new FileOutputStream(BpmsServerGui.fileField.getText());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				fos.close();
				BpmsServerGui.log.append("\n�� ��������� ������������� �������� ��� Server Book!\n");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	//������� ��������� ��� ������
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {

		// ������� �� � ����� ����� ����
		if (list.size() == 0) {
			BpmsServerGui.log.append("\n������! ��� �������� ������������.\n\n");
		}

		else if (BpmsServerGui.fileField.getText().length() == 0) {
			BpmsServerGui.log.append("\n������! ����������� �� ����� ��� �������.\n\n");
		}

		try {
			FileInputStream fis = new FileInputStream(BpmsServerGui.fileField.getText());
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (LinkedList<Data>) ois.readObject();
			listSorted = list;
			ois.close();
			BpmsServerGui.log.append("\n�� ��������� ���������� �������� ��� �� Server Book!\n\n");
		} catch (Exception e) {
			BpmsServerGui.log.append("\n������! ��� ������� ������ �� �����o �����.\n\n");
			System.out.println(e);
		}

	}
	
	//���������� ���������
	public static void clearBook() throws IOException {
		list = new LinkedList<Data>();
		listSorted = new LinkedList<Data>();
		saveToFile();
		BpmsServerGui.log.append("\n�� ������ ��������� �������������!\n");
	}
	
	//������� ��������� ��� ��� client
	public static void loadFromClient() throws ClassNotFoundException, IOException {
		StartClient.clientSendList();
		saveToFile();
	}
	
	//E����� ��������� ��� ������ � client
	public static void findName(String name) {

		// ���� ��������� index ������������ � ���� ��� ������������� �����������
		int index;
		int s = 0;

		// ������� �� � ����� ����� ����
		if (list.size() == 0) {
			BpmsServerGui.log.append("������! ��� �������� ������������.");
		}

		// ������� �� ������� ���������� �� �� ����� ��� ����� � �������
		else if (isInList(name) == false) {
			BpmsServerGui.log.append("������! ��� ������� ���������� �� ���� �� �������.");
		}

		else {

			// ���������� ������ ��� ������ ��������
			BpmsServerGui.log.append("������������ ����������:\n" + categories);
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(BpmsSearchGui.searchTextField.getText()) == 0) {

					// ���������� ����� ��������
					index = i;

					// ������ ��� ��� ����� ��������� ��� ����������� �� ����������� person ���
					// ��������
					for (Data person : list) {
						if (s == index) {
							BpmsServerGui.log.append(person + "\n");
							temp.add(person);
							BpmsSearchGui.status2Text.setText("� ���������� ������� ��������.");

						}
						s++;
					}
					s = 0;
				}
			}
			BpmsServerGui.log.append("\n");
		}
	}
	
	//�������� ��������� ��� ������ � client
	public static void deleteName(String name) throws IOException {

		// ������� �� � ����� ����� ����
		if (list.size() == 0) {
			BpmsServerGui.log.append("������! ��� �������� ������������.");
		}

		// '������� �� ������� �� �������
		else if (isInList(name) == false) {
			BpmsServerGui.log.append("������! ��� ������� ���������� �� ���� �� �������.");
		}

		// ���������� ������ ��� ������ ��������
		else {
			for (int i = 0; i < list.size(); i++) {
				String current = list.get(i).getId();
				if (current.compareToIgnoreCase(name) == 0) {

					// �������� ��� �������� ��� ��������� ������
					list.remove(i);
					listSorted.remove(i);
					display(sortType);
					BpmsServerGui.log.append("� ���������� ���������� ��������.");

				}
			}
			saveToFile();
		}
	}

}
