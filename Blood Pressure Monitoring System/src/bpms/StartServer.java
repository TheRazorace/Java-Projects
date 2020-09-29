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
	
	//Έναρξη λειτουργίας server
	public static void runServer() throws IOException, ClassNotFoundException {
		//Δημιουργία socket
		ss = new ServerSocket(7777);

		BpmsServerGui.log.append("Ο server αναμένει συνδέσεις ...\n\n");

        socket = ss.accept(); //Αναμονή για σύνδεση...
        BpmsServerGui.log.append("Επιτυχής σύνδεση με socket" + socket + "!\n");
        
        //Input stream για λήψη μηνύματος από client
        InputStream messageStream = socket.getInputStream();
        ObjectInputStream messageInputStream = new ObjectInputStream(messageStream);
        String message = (String) messageInputStream.readObject();
        
        //Λειτουργία ανά μήνυμα
        if(message.equals("GET_BOOK")) {
        	BpmsServerGui.log.append("Ελήφθη request: GET_BOOK από τον socket" + socket +"!\n\n");
        	serverSendList();
        }
        
        else if(message.equals("SEND_BOOK")) {
        	BpmsServerGui.log.append("Ελήφθη request: SEND_BOOK από τον socket" + socket +"!\n\n");
        	serverGetList();
        }
        
        else if(message.equals("CLEAR_BOOK")) {
        	BpmsServerGui.log.append("Ελήφθη request: CLEAR_BOOK από τον socket" + socket +"!\n\n");
        	Entries.clearBook();
        }
        
        else if(message.equals("SEND_MEASUREMENT")) {
        	BpmsServerGui.log.append("Ελήφθη request: SEND_MEASUREMENT από τον socket" + socket +"!\n\n");
        	serverGetMeasurement();
        }
        
        else if(message.equals("FIND_MEASUREMENT")) {
        	BpmsServerGui.log.append("Ελήφθη request: FIND_MEASUREMENT από τον socket" + socket +"!\n\n");
        	serverGetName();
        }
        
        else if(message.equals("DELETE_MEASUREMENT")) {
        	BpmsServerGui.log.append("Ελήφθη request: DELETE_MEASUREMENT από τον socket" + socket +"!\n\n");
        	serverDeleteName();
        }
        
        else {
        	BpmsServerGui.log.append("Ελήφθη άγνωστο request από τον socket" + socket +"!\n\n");
        }
	}
	
	//Λήψη μετρήσεων από client
	public static void serverGetList() throws IOException, ClassNotFoundException {

        //Λήψη input stream από το συνδεδεμένο socket
        InputStream inputStream = socket.getInputStream();

        //Δημιουργία DataInputStream για λήψη δεδομένων.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        //Ανάθεση δεδομένων σε μεταβλητές για επεξεργασία
        @SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        Entries.list.addAll(listOfMessages);
        Entries.listSorted.addAll(listOfMessages);

        BpmsServerGui.log.append("Λήφθηκαν δεδομένα από τον socket: " + socket + "!\n\n");

        //Εκτύπωση δεδομένων

        BpmsServerGui.log.append("Δεδομένα:\n");

        Entries.display(Entries.sortType);


        BpmsServerGui.log.append("\nΚλείσιμο σύνδεσης.\n");
        
        //Κλείσιμο σύνδεσης

        ss.close();

        socket.close();
        
        runServer();
	}
	
	//Λήψη ονόματος που θέλει ο client να διαγραφεί
	private static void serverDeleteName() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


		String name = (String) objectInputStream.readObject();

        BpmsServerGui.log.append("Λήφθηκαν δεδομένα από τον socket: " + socket + "!\n\n");

        Entries.deleteName(name);

        BpmsServerGui.log.append("\nΚλείσιμο σύνδεσης.\n");

        ss.close();

        socket.close();
        
        runServer();
	}
	
	//Λήψη ονόματος που θέλει ο client να βρεθεί
	private static void serverGetName() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


		String name = (String) objectInputStream.readObject();

        BpmsServerGui.log.append("Λήφθηκαν δεδομένα από τον socket: " + socket + "!\n\n");


        Entries.findName(name);

        BpmsServerGui.log.append("\nΚλείσιμο σύνδεσης.\n");

        ss.close();

        socket.close();
        
        runServer();
		
	}
	
	//Λήψη μεμονομένης μέτρησης από τον client
	private static void serverGetMeasurement() throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


        @SuppressWarnings("unchecked")
		LinkedList<Data> listOfMessages = (LinkedList<Data>) objectInputStream.readObject();
        Entries.list.addAll(listOfMessages);
        Entries.listSorted.addAll(listOfMessages);

        BpmsServerGui.log.append("Λήφθηκαν δεδομένα από τον socket: " + socket + "!\n\n");


        BpmsServerGui.log.append("Δεδομένα:\n");

        Entries.display(Entries.sortType);


        BpmsServerGui.log.append("\nΚλείσιμο σύνδεσης.\n");

        ss.close();

        socket.close();
        
        runServer();
		
	}
	
	//Αποστολή μετρήσεων στον client
	public static void serverSendList() throws IOException, ClassNotFoundException {
		
		OutputStream outputStream = socket.getOutputStream();
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		
		BpmsServerGui.log.append("Τα δεδομένα στάλθηκαν στον socket: " + socket + "!\n\n");

		objectOutputStream.writeObject(Entries.list);

		BpmsServerGui.log.append("Η σύνδεση τερματίστηκε.\n\n");
		
		ss.close();
		socket.close();
		runServer();
	}

}
