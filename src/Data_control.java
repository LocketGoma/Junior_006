// 이 친구는 파일 IO만 담당합니다.
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
public class Data_control {		// 다시짭시다.
	String file_addr = "custom.txt";
	ObjectOutputStream oos;
	ObjectInputStream ois;


	public Data_control(){
	}
	


	public void file_write(Object_user temp){
//		int positon = -1;		//저장될 위치값. 이게 계속 -1이면 맨 뒤로.
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file_addr,true));
		} catch (FileNotFoundException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		}
		
		try{
		oos.writeObject(temp);
		System.out.println("저장완료");
		}
		catch(Exception e){
			e.getMessage();			
		}
		finally {
			if(oos!=null)try {oos.close();} catch (IOException e){}
		}
		
		

	}
	public void file_find(int code) {   //https://goo.gl/NWRSEo
		System.out.println("비교할 번호"+code);

		
	}
	
}
