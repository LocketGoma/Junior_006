
public class Exceptional extends Exception {		//5,6
	private static final long serialVersionUID = 1L;
	public Exceptional(String msg){
	super(msg);
	}
	
	public String getMessage(){
		return "입력이 누락되었습니다";
	}
}

class FormatenotcorrectException extends Exception{				//예외 3, 1
	private static final long serialVersionUID = 1L;
	private char character;
	public FormatenotcorrectException(char character){
		this.character=character;
	}
	public String getMessage(){
		return "잘못된"+String.valueOf(character)+"문자가 입력되었습니다.";
	}
}

class DateFormatnotcorrectException extends FormatenotcorrectException{	//예외 1-1
	private static final long serialVersionUID = 1L;
	public DateFormatnotcorrectException(char character) {
		super(character);
		// TODO 자동 생성된 생성자 스텁
	}	
}

class PhoneFormatnotcorrentException extends FormatenotcorrectException{	//예외 1-2
	private static final long serialVersionUID = 1L;
	public PhoneFormatnotcorrentException(char character){
		super(character);
	}
}

class InterruptcodeException extends Exception{			//예외 2
	private static final long serialVersionUID = 1L;
	private int code;
	public InterruptcodeException(int code){
		this.code=code;
	}
	public String getMessage(){
		return String.valueOf(code)+"는 이미 있는 사용자입니다.";
	}
}

class OverSizeException extends Exception{				//예외4
	private static final long serialVersionUID = 1L;
	public String getMessage(){
		return "사이즈가 초과되었습니다.";
	}
}



