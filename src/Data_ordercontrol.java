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
//	String buffer=null;					//주문 정보 싸그리 들어갈 스트링 버퍼.
	Data_menu master=null;
	Object_order objbuf = new Object_order();
	
	File file = new File(file_addr);
	int[] counter =  new int[12];		// 해당 기간에 몇개나 주문했는지 세는 용도.
	
	

	
	FileReader fr=null;
	FileWriter fw=null;

	
	
	Date dt_temp=new Date();
	
	
	
		Data_ordercontrol(){

		}
		Data_ordercontrol(Data_menu master){
		this.master = master;

	}
		
	public synchronized void write_order(Object_order temp){		// 일자 # 메뉴 # \r\n
		
		try {
			System.out.println("싱크로 테스트:대기 10초");
			Thread.sleep(10000);
			fw=new FileWriter(file,true);
			fw.write(temp.getDate());
			fw.write("#");//구분자
			fw.write(temp.getMenu());
			fw.write("#");//구분자
			fw.write("\r\n");
			fw.flush();
		} catch (IOException | InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
		}
	}
	public synchronized void write_order(String date, String menu){		// 일자 # 메뉴 # \r\n
		
	//	sync=true;
		System.out.println("작성 테스트");
		try {			
			fw=new FileWriter(file,true);
			fw.write(date);
			fw.write("#");//구분자
			fw.write(menu);
			fw.write("#");//구분자
			fw.write("\r\n");
			fw.flush();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
//			fw=null;
		}
	}
	
	
	public String read_order(String start,String end){			//void -> string
		boolean reader=false;		//start 만나면 true, 마지막 만나거나 못만나면 false
		
		String temp_st=new String();
		String temp_line=new String();
		int i;
		int count=0;
		int positon=-1;
		
		try {
			fr=new FileReader(file);
			while((i=fr.read())!=-1){		//EOF까지
				if(i==35){					//= #
					if(count==0){
						if(temp_st.equals(start)){
//							System.out.println("찾음"+temp_st);
							reader=true;
						}
						else if(checker(temp_st,end)==1){//종료조건이 골때리는군.
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
						System.out.println(temp_line);		//불러온다!
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
				
				
			}	//while 끝
		}	
		catch (IOException e) {
		// TODO 자동 생성된 catch 블록
		e.printStackTrace();
		}
		finally {if(fr!=null)try{fr.close();}catch(IOException e){}	}
		
		for (int p = 0 ; p <12;p++){
			System.out.println(p+"::"+counter[p]);
		}
		
		return order_log();
		
		
	}
	private String parse_menu(String temp){		//메뉴 선택 언팩.
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
	
	public Date parse_date(String day){			//date 언팩. 이유? 검색해야지!
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
	private int checker(String A, String B){		//A&B 같으면 0  A가 크면 1 A가 작으면 -1, 요거 if 좀 많음
		Date dt1;
		Date dt2;
		
		dt1=parse_date(A);
		dt2=parse_date(B);
			if(dt1.year==dt2.year){		//1. 연도 같을때
				
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
			else if (dt1.year>dt2.year){		//1-2. A가 연도가 더 클때.
				return 1;
			}
			else if (dt1.year<dt2.year){		//1-3. A가 연도가 더 낮을때.
				return -1;
			}
		
		
		
		return 0;
	}
	
	
	
	/*자료 해석용 메소드*/
	private int match_menu(String menu){			//input 메뉴를 정해진 상수로 변환
		if (menu.equals("야채김밥"))
			return 1;
		else if (menu.equals("참치김밥"))
			return 2;
		else if (menu.equals("소고기김밥"))
			return 3;
		else if (menu.equals("떡볶이"))
			return 4;
		else if (menu.equals("치즈떡볶이"))
			return 5;
		else if (menu.equals("순대"))
			return 6;
		else if (menu.equals("수제돈까스"))
			return 7;
		else if (menu.equals("치즈돈까스"))
			return 8;
		else if (menu.equals("물만두"))
			return 9;
		else if (menu.equals("탕수육"))
			return 10;
		else if (menu.equals("김치피자탕수육"))
			return 11;
		else
			return 0;		
	}
	private String menu_return(int i){			//상수로 변환된 값을 다시 메뉴로 변환
		switch(i){
			case 1 : return"야채김밥";			
			case 2 : return"참치김밥";
			case 3 : return"소고기김밥";			
			case 4 : return"떡볶이";
			case 5 : return"치즈떡볶이";			
			case 6 : return"순대";
			case 7 : return"수제돈까스";			
			case 8 : return"치즈돈까스";
			case 9 : return"물만두";			
			case 10 : return"탕수육";
			case 11 : return"김치피자탕수육";			
		}
		return null;
	}
	
	private int cost_menu(int code){				//메뉴 코드에 맞는 가격 리턴.
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
	
	private String order_log(){						//로그 버퍼 생성기
		String buffer = new String();
		StringBuffer strBuf = new StringBuffer();
//		System.out.println("출력 준비");
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
	/*검색용*/
	int year=0;
	int month=0;
	int day=0;
}
