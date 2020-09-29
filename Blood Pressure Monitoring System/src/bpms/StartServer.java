package bpms;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class StartServer {
	private static ServerSocket ss;
	private static Socket socket; 
	
	//������ ����������� server
	public static void runServer() throws IOException, ClassNotFoundException {
		//���������� socket
		ss = new ServerSocket(7777);

		BpmsServerGui.log.append("� server �������� ��������� ...\n\n");

        socket = ss.accept(); //������� ��� �������...
        BpmsServerGui.log.append("�������� ������� �� socket" + socket + "!\n");
        
        //Input stream ��� ���� ��������� ��� client
        InputStream messageStream = socket.getInputStream();
        ObjectInputStream messageInputStream = new ObjectInputStream(messageStream);
        String message = (String) messageInputStream.readObject();
        
        //���������� ��� ������
        if(message.equals("GET_BOOK")) {
        	BpmsServerGui.log.append("������ request: GET_BOOK ��� ��� socket" + socket +"!\n\n");
        	serverSendList();
        }
        
        else if(message.equals("SEND_BOOK")) {
        	BpmsServerGui.log.append("������ request: SEND_BOOK ��� ��� socket" + socket +"!\n\n");
        	serverGetList();
        }
        
        else if(message.equals("CLEAR_BOOK")) {
        	BpmsServerGui.log.append("������ request: CLEAR_BOOK ��� ��� socket" + socket +"!\n\n");
        	Entries.clearBook();
        }
        
        else if(message.equals("SEND_MEASUREMENT")) {
        	BpmsServerGui.log.append("������ request: SEND_MEASUREMENT ��� ��� socket" + socket +"!\n\n");
        	serverGetMeasurement();
        }
        
        else if(message.equals("FIND_MEASUREMENT")) {
        	BpmsServerGui.log.append("������ request: FIND_MEASUREMENT ��� ��� socket" + socket +"!\n\n");
        	serverGetName();
        }
        
        else if(message.equals("DELETE_MEASUREMENT")) {
        	BpmsServerGui.log.append("������ request: DELETE_MEASUREMENT ��� ��� socket" + socket +"!\n\n");
        	serverDeleteName();
        }
        
        else {
        	BpmsServerGui.log.append("������ ������� request ��� ��� socket" + socket +"!\n\n");
        }
	}
	
	//���� ��������� ��� client
	public static void serverGetList() throws IOException, ClassNotFoundException {

        //���� input stream ��� �� ����������� socket
        InputStream inputStream = socket.getInputStream();

        //���������� DataInputStream ��� ���� ���������.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        //������� ��������� �� ���������� ��� �����������
        @SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        Entries.list.addAll(listOfMessages);
        Entries.listSorted.addAll(listOfMessages);

        BpmsServerGui.log.append("�������� �������� ��� ��� socket: " + socket + "!\n\n");

        //�������� ���������

        BpmsServerGui.log.append("��������:\n");

        Entries.display(Entries.sortType);


        BpmsServerGui.log.append("\n�������� ��������.\n");
        
        //�������� ��������

        ss.close();

        socket.close();
        
        runServer();
	}
	
	//���� �������� ��� ����� � client �� ���������
	private static void serverDeleteName() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


		String name = (String) objectInputStream.readObject();

        BpmsServerGui.log.append("�������� �������� ��� ��� socket: " + socket + "!\n\n");

        Entries.deleteName(name);

        BpmsServerGui.log.append("\n�������� ��������.\n");

        ss.close();

        socket.close();
        
        runServer();
	}
	
	//���� �������� ��� ����� � client �� ������
	private static void serverGetName() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


		String name = (String) objectInputStream.readObject();

        BpmsServerGui.log.append("�������� �������� ��� ��� socket: " + socket + "!\n\n");


        Entries.findName(name);

        BpmsServerGui.log.append("\n�������� ��������.\n");

        ss.close();

        socket.close();
        
        runServer();
		
	}
	
	//���� ����������� �������� ��� ��� client
	private static void serverGetMeasurement() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        @SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        Entries.list.addAll(listOfMessages);
        Entries.listSorted.addAll(listOfMessages);

        BpmsServerGui.log.append("�������� �������� ��� ��� socket: " + socket + "!\n\n");


        BpmsServerGui.log.append("��������:\n");

        Entries.display(Entries.sortType);


        BpmsServerGui.log.append("\n�������� ��������.\n");

        ss.close();

        socket.close();
        
        runServer();
		
	}
	
	//�������� ��������� ���� client
	public static void serverSendList() throws IOException, ClassNotFoundException {
		
		OutputStream outputStream = socket.getOutputStream();
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		
		BpmsServerGui.log.append("�� �������� ��������� ���� socket: " + socket + "!\n\n");

		objectOutputStream.writeObject(Entries.list);

		BpmsServerGui.log.append("� ������� ������������.\n\n");
		
		ss.close();
		socket.close();
		runServer();
	}

}
