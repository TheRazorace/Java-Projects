package bpms;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class StartClient {
	
	//�������� ������� ��������� ���� server
	public static void clientSendList()  throws IOException, ClassNotFoundException {
		//���������� �������� �� server
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		//���������� output stream ��� �������� ���������
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("SEND_BOOK");
		BpmsClientGui.log.append("�������� request: SEND_BOOK ���� server!\n");

		//���������� output stream ��� �������� ���������

		OutputStream outputStream = socket.getOutputStream();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		BpmsClientGui.log.append("�� �������� ��������� ���� server\n\n");

		objectOutputStream.writeObject(ClientEntries.clientList);

		BpmsClientGui.log.append("� ������� ������������.\n\n");
		
		//�������� ��������
		socket.close();
	}
	
	//���� ��������� server ��� ���������� �� ������� ���������
	public static void clientGetList() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("GET_BOOK");
		BpmsClientGui.log.append("�������� request: GET_BOOK ���� server!\n");
		
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		@SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        ClientEntries.clientList = listOfMessages;
        ClientEntries.clientListSorted = listOfMessages;

        BpmsClientGui.log.append("�������� �������� ��� ��� socket: " + socket + "\n\n");


        BpmsClientGui.log.append("��������:\n");

        ClientEntries.display(ClientEntries.sortType);


        BpmsClientGui.log.append("\n�������� ��������.\n");
		socket.close();
	}
	
	//���� ����������� �������� ���� server
	public static void clientSendMeasurement() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("SEND_MEASUREMENT");
		BpmsClientGui.log.append("�������� request: SEND_MEASUREMENT ���� server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("�� �������� ��������� ���� server\n\n");

		objectOutputStream.writeObject(ClientEntries.measurement);

		BpmsClientGui.log.append("� ������� ������������.\n\n");

		socket.close();
		
	}
	
	//���������� ��������� ��� server
	public static void clientClearServerBook() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("CLEAR_BOOK");
		BpmsClientGui.log.append("�������� request: CLEAR_BOOK ���� server!\n");


		BpmsClientGui.log.append("� ������� ������������.\n\n");

		socket.close();
		
	}
	
	//A������� �������� ��� ������ � client, ���� server
	public static void clientSendEntryName() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("FIND_MEASUREMENT");
		BpmsClientGui.log.append("�������� request: FIND_MEASUREMENT ���� server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("�� �������� ��������� ���� server\n\n");

		objectOutputStream.writeObject(BpmsSearchGui.searchTextField.getText());

		BpmsClientGui.log.append("� ������� ������������.\n\n");

		socket.close();
	}
	
	//A������� �������� ��� ����� � client �� ��������� ��� ��� server
	public static void clientDeleteServerEntry() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("� client ���������!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("DELETE_MEASUREMENT");
		BpmsClientGui.log.append("�������� request: DELETE_MEASUREMENT ���� server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("�� �������� ��������� ���� server\n\n");

		objectOutputStream.writeObject(BpmsSearchGui.searchTextField.getText());

		BpmsClientGui.log.append("� ������� ������������.\n\n");

		socket.close();
	}
	
}
