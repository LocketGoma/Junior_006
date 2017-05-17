// 이 친구는 파일 IO만 담당합니다.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Data_control {
	String file_addr = "custom.txt";
	Object_user user_temp = new Object_user();
	
	private File file;
	
	public Data_control(){
		try{
		file = new File(file_addr);
		user_temp = new Object_user();
		}
		catch (Exception e){
			e.getMessage();
		}
	}
	public Data_control(Object_user user_temp){
		try{
		file = new File(file_addr);
		this.user_temp = user_temp;
		}
		catch (Exception e){
			e.getMessage();
		}
	}
	
	public Object_user file_read(ObjectInputStream ois){
		try {
			return (Object_user)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
			return null;
		}
	}
	public void file_write(int pos, Object_user temp){
		int postion;
		postion=pos;
	}
	public void file_write(Object_user temp){
		int positon = -1;		//저장될 위치값. 이게 계속 -1이면 맨 뒤로.
		
		
		try{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_addr,true));
		oos.writeObject(temp);
		oos.close();
		System.out.println("저장완료");
		}
		catch(Exception e){
			e.getMessage();
		}
		

	}
	public void file_find(int code){   //https://goo.gl/NWRSEo
		System.out.println("비교할 번호"+code);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file_addr));
			while(ois!=null){		//파일 끝일때
					user_temp=file_read(ois);
					try{
						System.out.println("비교번호"+user_temp.getUser_num());
						if(user_temp.getUser_num()==code){
							System.out.println("일치");
						break;						
						}	
						else
							System.out.println("불일치");						
					}
					catch (NullPointerException e){
						break;
					}					
			}
			return;
		} catch (FileNotFoundException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		}
		finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
	}
	
}
