package bpms;

import java.io.IOException;

//Main �����

public class BloodPressureMonitoringSystemV2 {
	static BpmsServerGui serverGui;
	static BpmsClientGui clientGui;
	static StartServer server;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		//�������� ��� gui
		serverGui = new BpmsServerGui();
		clientGui = new BpmsClientGui();
		StartServer.runServer();

	}

}
