package bpms;

//����� ��� ������ exception �� ��������� ��������� ��� ���������

public class DataException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataException() {
		super();
	}
	
	public DataException(String message) {
		super(message);
	}
	
}
