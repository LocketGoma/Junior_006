// 이 친구는 파일 IO만 담당합니다.
import java.io.File;
public class Data_control {
	String file_addr = "\\custom.txt";
	Object_user user_temp = new Object_user();
	
	private File file;
	
	public Data_control(){
		file = new File(file_addr);
	}
	
	public void file_read(){
		
	}
	public void file_write(){
		
	}
}
