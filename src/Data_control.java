// �� ģ���� ���� IO�� ����մϴ�.
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
public class Data_control {		// �ٽ�¬�ô�.
	static int bonus=2;					//���ʽ��� ���� ���° ���� �ֹ��� �ٰ��ΰ�. (2 = 3��° �ֹ��� ����)
	
	String file_addr = "custom.txt";
	File file = new File(file_addr);
	Data_finder head_finder;
	
	FileReader fr=null;
	FileWriter fw=null;
	
	Object_user temp_user=null;
	
	/*�� ���Ͽ�.*/
	public Object_user getTemp_user() {
		return temp_user;
	}
	public void setTemp_user(Object_user temp_user) {
		this.temp_user = temp_user;
	}
	


	public Data_control(){
	}
	public Data_control(Data_finder head_finder){
		this.head_finder=head_finder;
	}
	


	public void file_write(Object_user temp){		//���� �Ϸ�, �Ƹ���?
	//oos �̿��� ������.
		
		System.out.println(temp.getName());
		try {
			fw=new FileWriter(file,true);

			fw.write(Integer.toString(temp.getUser_num()));
			fw.write("#");//������
			fw.write(temp.getName());
			fw.write("#");//������
			fw.write(temp.getPhnum());
			fw.write("#");//������
			fw.write(temp.getBirth());
			fw.write("#");//������
			fw.write(Integer.toString(temp.getUse_count()));
			fw.write("#");//������
			fw.write("\r\n");
			fw.flush();
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
		}
	}
	public int file_find(int code) {   //https://goo.gl/NWRSEo, ���� �Ϸ�
		System.out.println("���� ��ȣ"+code);
		int finder=code;//ã�� ȸ����ȣ.
		String temp_st=new String();
		String temp_line=new String();
		int i;
		int count=0;
		int positon=-1;
		boolean breaker=false;
		
		try {
//			fw=new FileWriter(file);
			fr=new FileReader(file);
		while((i=fr.read())!=-1){		//EOF����
			if(i==35){
				if(count==0){
					if(finder==Integer.parseInt(temp_st)){
						System.out.println("ã��"+temp_st);
						breaker=true;
					}
				}
				else	;
				
				count++;
			}
			else if (i==10){
				positon++;
				if(breaker){
					System.out.println(temp_line);		//�ҷ��´�!
					this.user_read(temp_line);					
					break;
				}
				temp_st="";
				temp_line="";
				count=0;								
			}
			else
				temp_st+=(char)i;
				temp_line+=(char)i;
			
			}
		if (breaker!=true){
			System.out.println("��ã��..");
			positon=-1;
			this.setTemp_user(null);
		}
		}	
		catch (IOException e) {
		// TODO �ڵ� ������ catch ���
		e.printStackTrace();
		}
		finally {
			if(fr!=null)try{fr.close();}catch(IOException e){}
		}
		return positon+1;
		
		
		
	}
	
	
	public void file_read(){					//1�� �б�, ����?
		Object_user temp=new Object_user();
		String temp_st=new String();
		int i=0;
		int count=0; //# ���?
			try {
				fr=new FileReader(file);			
			while((i=fr.read())!=10){
			if(i==35){
				if(count==0){
					temp.setUser_num(Integer.parseInt(temp_st));
				}
				else if(count==1){
					temp.setName(temp_st);
				}
				else if(count==2){
					temp.setPhnum(temp_st);
				}
				else if(count==3){
					temp.setBirth(temp_st);
				}
				else if(count==4){
					temp.setUse_count(Integer.parseInt(temp_st));
				}
				else
					System.out.println(temp_st);
				temp_st="";				
				count++;
			}
			else
			temp_st+=(char)i;
		}
			}
			catch (NumberFormatException | IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
			finally{
			if(fr!=null)try {fr.close();} catch (IOException e) {}
			}
			System.out.println("�Ľ� ������::");
			System.out.println("�̸�:"+temp.name);
			System.out.println("����ȣ:"+temp.phnum);
			System.out.println("������:"+temp.birth);
			System.out.println("��ȣ :: "+temp.user_num);			
	}

	public void user_read(String buffer){		//�Ľ� ��.
//		System.out.println("����Ȯ��, ���� : "+buffer.length());
		int count=0;
		int ct;
		String temp=new String();
		Object_user user=new Object_user();
		
		for (int i=0;i<buffer.length();i++){
			ct=buffer.charAt(i);
//			System.out.println(ct);
			if(ct==10) ;
			else if(ct==35){
				if(count==0){
					user.setUser_num(Integer.parseInt(temp));
				}
				else if(count==1){
					user.setName(temp);
				}
				else if(count==2){
					user.setPhnum(temp);
				}
				else if(count==3){
					user.setBirth(temp);
				}
				else if(count==4){
					user.setUse_count(Integer.parseInt(temp));
				}
				count++;
				temp="";
			}
			else{				
			temp+=(char)ct;
			}
//			System.out.println("���ڿ�:"+temp);
		}
		setTemp_user(user);
		
//		System.out.println("����� ������::");
//		System.out.println("�̸�:"+user.name);
//		System.out.println("����ȣ:"+user.phnum);
//		System.out.println("������:"+user.birth);
//		System.out.println("��ȣ :: "+user.user_num);		
		
	}	
	
	public void file_delete(int code){	//http://mantdu.tistory.com/731 ����
		//�̰� �˻�+�����ݾ�;				//http://aroundck.tistory.com/11 ��ŵ� ����
		System.out.println("������ ��ȣ"+code);
		BufferedReader br=null;
		FileWriter fw=null;

		int postion = this.file_find(code);
		System.out.println("������ ��ġ"+postion);
		int nowline=1;

		if(postion!=0){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));			
			} catch (IOException e1) {
				// TODO �ڵ� ������ catch ���
				e1.printStackTrace();
			}
		String temp="";
		String line="";

			try {
				while ((line=br.readLine())!=null){
					System.out.println(line);
					if(postion==nowline){
						System.out.println("���� ��ġ");
					}
					else
						temp+=(line+"\r\n");
									
					nowline++;
				}			
				System.out.println("temp:"+temp+"temp ��");
				fw = new FileWriter(file_addr);
				System.out.println("temp:"+temp+"temp ��");
				fw.write(temp);
				System.out.println("temp:"+temp+"temp ��");
			} catch (IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally{
				try{fw.close();}catch(IOException e){}
			}
		}
		else
			System.out.println("������ �����Ͱ� �����ϴ�");
	}
	
	public int file_countplus(int code){	//ī��Ʈ�� 1 �÷��ִ°�. delete�ϰ� ����.
		System.out.println("�÷��� ��ȣ"+code);
		BufferedReader br=null;
		FileWriter fw=null;
		Object_user temp_user = null;
		int postion = this.file_find(code);
		System.out.println("������ ��ġ"+postion);
		int nowline=1;
		
		if(postion!=0){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));			
			} catch (IOException e1) {
				// TODO �ڵ� ������ catch ���
				e1.printStackTrace();
			}
		String temp="";
		String line="";
		String changed="";
			try {
				while ((line=br.readLine())!=null){
					System.out.println(line);
					if(postion==nowline){
						user_read(line);
						System.out.println("Ȯ��");
						temp_user=getTemp_user();
						setTemp_user(null);
						if(temp_user.getUse_count()!=bonus)
							temp_user.setUse_count(temp_user.getUse_count()+1);
						else
							temp_user.setUse_count(0);
						//setTemp_user(temp_user);
						changed=temp_user.getUser_num()+"#"+temp_user.getName()+"#"+temp_user.getPhnum()+"#"+temp_user.getBirth()+"#"+temp_user.getUse_count()+"#";
						
						
						temp+=(changed+"\r\n");
						
					}
					else
						temp+=(line+"\r\n");
									
					nowline++;
				}			
				System.out.println("temp:"+temp+"temp ��");
				fw = new FileWriter(file_addr);
				System.out.println("temp:"+temp+"temp ��");
				fw.write(temp);
				System.out.println("temp:"+temp+"temp ��");
			} catch (IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally{
				try{fw.close();}catch(IOException e){}
			}
			return 1;
		}
		else
			System.out.println("������ �����Ͱ� �����ϴ�");
		return 0;
	}
	
	public void file_edit(int code){		//��� �ú�...
		
	}
}
