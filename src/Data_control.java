// �� ģ���� ���� IO�� ����մϴ�.
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
public class Data_control {		// �ٽ�¬�ô�.
	String file_addr = "custom.txt";
	ObjectOutputStream oos;
	ObjectInputStream ois;


	public Data_control(){
	}
	


	public void file_write(Object_user temp){
//		int positon = -1;		//����� ��ġ��. �̰� ��� -1�̸� �� �ڷ�.
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file_addr,true));
		} catch (FileNotFoundException e1) {
			// TODO �ڵ� ������ catch ���
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO �ڵ� ������ catch ���
			e1.printStackTrace();
		}
		
		try{
		oos.writeObject(temp);
		System.out.println("����Ϸ�");
		}
		catch(Exception e){
			e.getMessage();			
		}
		finally {
			if(oos!=null)try {oos.close();} catch (IOException e){}
		}
		
		

	}
	public void file_find(int code) {   //https://goo.gl/NWRSEo
		System.out.println("���� ��ȣ"+code);

		
	}
	
}
