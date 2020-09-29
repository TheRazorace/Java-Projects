package bpms;

import java.io.IOException;

//Main κλάση

public class BloodPressureMonitoringSystemV2 {
	static BpmsServerGui serverGui;
	static BpmsClientGui clientGui;
	static StartServer server;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		//Εκκίνηση των gui
		serverGui = new BpmsServerGui();
		clientGui = new BpmsClientGui();
		StartServer.runServer();

	}

}
