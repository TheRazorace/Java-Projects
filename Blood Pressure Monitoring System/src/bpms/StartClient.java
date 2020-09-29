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
	
	//Αποστολή τοπικών μετρήσεων στον server
	public static void clientSendList()  throws IOException, ClassNotFoundException {
		//Δημιουργία σύνδεσης με server
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		//Δημιουργία output stream για αποστολή μηνύματος
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("SEND_BOOK");
		BpmsClientGui.log.append("Στάλθηκε request: SEND_BOOK στον server!\n");

		//Δημιουργία output stream για αποστολή δεδομένων

		OutputStream outputStream = socket.getOutputStream();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		BpmsClientGui.log.append("Τα δεδομένα στάλθηκαν στον server\n\n");

		objectOutputStream.writeObject(ClientEntries.clientList);

		BpmsClientGui.log.append("Η σύνδεση τερματίστηκε.\n\n");
		
		//Κλείσιμο σύνδεσης
		socket.close();
	}
	
	//Λήψη μετρήσεων server και αποθήκευση ως τοπικές μετρήσεις
	public static void clientGetList() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("GET_BOOK");
		BpmsClientGui.log.append("Στάλθηκε request: GET_BOOK στον server!\n");
		
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		@SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        ClientEntries.clientList = listOfMessages;
        ClientEntries.clientListSorted = listOfMessages;

        BpmsClientGui.log.append("Λήφθηκαν δεδομένα από τον socket: " + socket + "\n\n");


        BpmsClientGui.log.append("Δεδομένα:\n");

        ClientEntries.display(ClientEntries.sortType);


        BpmsClientGui.log.append("\nΚλείσιμο σύνδεσης.\n");
		socket.close();
	}
	
	//Λήψη μεμονομένης μέτρησης στον server
	public static void clientSendMeasurement() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("SEND_MEASUREMENT");
		BpmsClientGui.log.append("Στάλθηκε request: SEND_MEASUREMENT στον server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("Τα δεδομένα στάλθηκαν στον server\n\n");

		objectOutputStream.writeObject(ClientEntries.measurement);

		BpmsClientGui.log.append("Η σύνδεση τερματίστηκε.\n\n");

		socket.close();
		
	}
	
	//Καθαρισμός μετρήσεων του server
	public static void clientClearServerBook() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("CLEAR_BOOK");
		BpmsClientGui.log.append("Στάλθηκε request: CLEAR_BOOK στον server!\n");


		BpmsClientGui.log.append("Η σύνδεση τερματίστηκε.\n\n");

		socket.close();
		
	}
	
	//Aποστολή ονόματος που ψάχνει ο client, στον server
	public static void clientSendEntryName() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("FIND_MEASUREMENT");
		BpmsClientGui.log.append("Στάλθηκε request: FIND_MEASUREMENT στον server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("Τα δεδομένα στάλθηκαν στον server\n\n");

		objectOutputStream.writeObject(BpmsSearchGui.searchTextField.getText());

		BpmsClientGui.log.append("Η σύνδεση τερματίστηκε.\n\n");

		socket.close();
	}
	
	//Aποστολή ονόματος που θέλει ο client να διαγραφεί από τον server
	public static void clientDeleteServerEntry() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7777);
		BpmsClientGui.log.append("Ο client συνδέθηκε!\n\n");
		
		OutputStream messageStream = socket.getOutputStream();
		
		ObjectOutputStream messageOutputStream = new ObjectOutputStream(messageStream);
		
		messageOutputStream.writeObject("DELETE_MEASUREMENT");
		BpmsClientGui.log.append("Στάλθηκε request: DELETE_MEASUREMENT στον server!\n");


		OutputStream outputStream = socket.getOutputStream();


		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


		BpmsClientGui.log.append("Τα δεδομένα στάλθηκαν στον server\n\n");

		objectOutputStream.writeObject(BpmsSearchGui.searchTextField.getText());

		BpmsClientGui.log.append("Η σύνδεση τερματίστηκε.\n\n");

		socket.close();
	}
	
}
