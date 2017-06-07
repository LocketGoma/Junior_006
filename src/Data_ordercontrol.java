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
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Data_ordercontrol {
	String file_addr = "sale.txt";
//	String buffer=null;					//�ֹ� ���� �α׸� �� ��Ʈ�� ����.
	Data_menu master=null;
	Object_order objbuf = new Object_order();
	
	File file = new File(file_addr);
	int[] counter =  new int[12];		// �ش� �Ⱓ�� ��� �ֹ��ߴ��� ���� �뵵.
	
	

	
	FileReader fr=null;
	FileWriter fw=null;

	
	
	Date dt_temp=new Date();
	
	
	
		Data_ordercontrol(){

		}
		Data_ordercontrol(Data_menu master){
		this.master = master;

	}
		
	public synchronized void write_order(Object_order temp){		// ���� # �޴� # \r\n
		
		try {
			System.out.println("��ũ�� �׽�Ʈ:��� 10��");
			Thread.sleep(10000);
			fw=new FileWriter(file,true);
			fw.write(temp.getDate());
			fw.write("#");//������
			fw.write(temp.getMenu());
			fw.write("#");//������
			fw.write("\r\n");
			fw.flush();
		} catch (IOException | InterruptedException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
		}
	}
	public synchronized void write_order(String date, String menu){		// ���� # �޴� # \r\n
		
	//	sync=true;
		System.out.println("�ۼ� �׽�Ʈ");
		try {			
			fw=new FileWriter(file,true);
			fw.write(date);
			fw.write("#");//������
			fw.write(menu);
			fw.write("#");//������
			fw.write("\r\n");
			fw.flush();
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
//			fw=null;
		}
	}
	
	
	public String read_order(String start,String end){			//void -> string
		boolean reader=false;		//start ������ true, ������ �����ų� �������� false
		
		String temp_st=new String();
		String temp_line=new String();
		int i;
		int count=0;
		int positon=-1;
		
		try {
			fr=new FileReader(file);
			while((i=fr.read())!=-1){		//EOF����
				if(i==35){					//= #
					if(count==0){
						if(temp_st.equals(start)){
//							System.out.println("ã��"+temp_st);
							reader=true;
						}
						else if(checker(temp_st,end)==1){//���������� �񶧸��±�.
							reader=false;
							break;
						}
						else
							;
					}
					else	;
					
					count++;
				}
				else if (i==10){
					positon++;
//					System.out.println("postion"+positon);
					if(reader){
						System.out.println(temp_line);		//�ҷ��´�!
						counter[match_menu(parse_menu(temp_line))]++;
						//break;
					}
					temp_st="";
					temp_line="";
					count=0;								
				}
				else
					temp_st+=(char)i;
					temp_line+=(char)i;
				
				
			}	//while ��
		}	
		catch (IOException e) {
		// TODO �ڵ� ������ catch ���
		e.printStackTrace();
		}
		finally {if(fr!=null)try{fr.close();}catch(IOException e){}	}
		
		for (int p = 0 ; p <12;p++){
			System.out.println(p+"::"+counter[p]);
		}
		
		return order_log();
		
		
	}
	private String parse_menu(String temp){		//�޴� ���� ����.
		String menu="";
		boolean ismenu=false;
		int j=0;
		for (int i=0;i<temp.length();i++){
			j=temp.charAt(i);
			if(j==35){
				if(ismenu)break;
				else ismenu=true;
			}
			else if(ismenu){
				menu+=(char)j;
			}
			
		}
		return menu;
	}
	
	public Date parse_date(String day){			//date ����. ����? �˻��ؾ���!
		int j=0;
		int count=0;
		Date temp=new Date();
		String buffer=new String();
		for (int i=0;i<day.length();i++){
			j=day.charAt(i);
			if (j==45){				
				switch(count){
				case 0:temp.year=Integer.parseInt(buffer);break;
				case 1:temp.month=Integer.parseInt(buffer);break;
				}
				buffer="";
				
				count++;
			}			
			else
				buffer+=(char)j;
			
			if(i==day.length()-1){
				temp.day=Integer.parseInt(buffer);
			}
		}
		System.out.println(temp.day);
		
		return temp;
	}
	private int checker(String A, String B){		//A&B ������ 0  A�� ũ�� 1 A�� ������ -1, ��� if �� ����
		Date dt1;
		Date dt2;
		
		dt1=parse_date(A);
		dt2=parse_date(B);
			if(dt1.year==dt2.year){		//1. ���� ������
				
				 if(dt1.month==dt2.month){
					 
					 if (dt1.day==dt2.day){
						 return 0;
					 }
					 else if (dt1.day>dt2.day){
						 return 1;
					 }
					 else if (dt1.day<dt2.day){
						 return -1;
					 }
					 
				 }
				 else if(dt1.month>dt2.month){
					 return 1;
				 }
				 else if (dt1.month<dt2.month){
					 return -1;
				 }
				
				
			}
			else if (dt1.year>dt2.year){		//1-2. A�� ������ �� Ŭ��.
				return 1;
			}
			else if (dt1.year<dt2.year){		//1-3. A�� ������ �� ������.
				return -1;
			}
		
		
		
		return 0;
	}
	
	
	
	/*�ڷ� �ؼ��� �޼ҵ�*/
	private int match_menu(String menu){			//input �޴��� ������ ����� ��ȯ
		if (menu.equals("��ä���"))
			return 1;
		else if (menu.equals("��ġ���"))
			return 2;
		else if (menu.equals("�Ұ����"))
			return 3;
		else if (menu.equals("������"))
			return 4;
		else if (menu.equals("ġ�����"))
			return 5;
		else if (menu.equals("����"))
			return 6;
		else if (menu.equals("�������"))
			return 7;
		else if (menu.equals("ġ��"))
			return 8;
		else if (menu.equals("������"))
			return 9;
		else if (menu.equals("������"))
			return 10;
		else if (menu.equals("��ġ����������"))
			return 11;
		else
			return 0;		
	}
	private String menu_return(int i){			//����� ��ȯ�� ���� �ٽ� �޴��� ��ȯ
		switch(i){
			case 1 : return"��ä���";			
			case 2 : return"��ġ���";
			case 3 : return"�Ұ����";			
			case 4 : return"������";
			case 5 : return"ġ�����";			
			case 6 : return"����";
			case 7 : return"�������";			
			case 8 : return"ġ��";
			case 9 : return"������";			
			case 10 : return"������";
			case 11 : return"��ġ����������";			
		}
		return null;
	}
	
	private int cost_menu(int code){				//�޴� �ڵ忡 �´� ���� ����.
		switch (code){
		case 1 : return 1000; 
		case 2 : return 1500; 
		case 3 : return 2000;
		case 4 : return 2000;
		case 5 : return 2500; 
		case 6 : return 2500; 
		case 7 : return 2500;
		case 8 : return 3000;
		case 9 : return 3000; 
		case 10 : return 3500; 
		case 11 : return 4000;
		default : return 0;
		}		
	}
	
	private String order_log(){						//�α� ���� ������
		String buffer = new String();
		StringBuffer strBuf = new StringBuffer();
//		System.out.println("��� �غ�");
		for (int i=1;i<12;i++){
			if (counter[i]!=0){
//				System.out.println(counter[i]);
				strBuf.append(menu_return(i)+"       ");
				strBuf.append(Integer.toString(counter[i])+"       ");
				strBuf.append(Integer.toString(counter[i]*cost_menu(i)));
				strBuf.append("\n");
				buffer+=menu_return(i);
//				System.out.println(strBuf.toString());
//				buffer+="       ";
//				buffer+=Integer.toString(counter[i]);
//				buffer+="       ";
//				buffer+=Integer.toString(counter[i]*cost_menu(i));
//				buffer+="\n";						
			}
		}
		
		
		
		return strBuf.toString();
	}
	
}

class Date{
	/*�˻���*/
	int year=0;
	int month=0;
	int day=0;
}
