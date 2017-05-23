
public class Exceptional extends Exception {		//5,6
	private static final long serialVersionUID = 1L;
	public Exceptional(String msg){
	super(msg);
	}
	
	public String getMessage(){
		return "�Է��� �����Ǿ����ϴ�";
	}
}

class FormatenotcorrectException extends Exception{				//���� 3, 1
	private static final long serialVersionUID = 1L;
	private char character;
	public FormatenotcorrectException(char character){
		this.character=character;
	}
	public String getMessage(){
		return "�߸���"+String.valueOf(character)+"���ڰ� �ԷµǾ����ϴ�.";
	}
}

class DateFormatnotcorrectException extends FormatenotcorrectException{	//���� 1-1
	private static final long serialVersionUID = 1L;
	public DateFormatnotcorrectException(char character) {
		super(character);
		// TODO �ڵ� ������ ������ ����
	}	
}

class PhoneFormatnotcorrentException extends FormatenotcorrectException{	//���� 1-2
	private static final long serialVersionUID = 1L;
	public PhoneFormatnotcorrentException(char character){
		super(character);
	}
}

class InterruptcodeException extends Exception{			//���� 2
	private static final long serialVersionUID = 1L;
	private int code;
	public InterruptcodeException(int code){
		this.code=code;
	}
	public String getMessage(){
		return String.valueOf(code)+"�� �̹� �ִ� ������Դϴ�.";
	}
}

class OverSizeException extends Exception{				//����4
	private static final long serialVersionUID = 1L;
	public String getMessage(){
		return "����� �ʰ��Ǿ����ϴ�.";
	}
}



