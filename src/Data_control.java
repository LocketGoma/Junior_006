// 이 친구는 파일 IO만 담당합니다.
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
public class Data_control {		// 다시짭시다.
	static int bonus=2;					//보너스를 이전 몇번째 누적 주문때 줄것인가. (2 = 3번째 주문때 지급)
	
	String file_addr = "custom.txt";
	File file = new File(file_addr);
	Data_finder head_finder;
	
	FileReader fr=null;
	FileWriter fw=null;
	
	Object_user temp_user=null;
	
	/*값 리턴용.*/
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
	


	public void file_write(Object_user temp){		//구현 완료, 아마도?
	//oos 이외의 것으로.
		
		System.out.println(temp.getName());
		try {
			fw=new FileWriter(file,true);

			fw.write(Integer.toString(temp.getUser_num()));
			fw.write("#");//구분자
			fw.write(temp.getName());
			fw.write("#");//구분자
			fw.write(temp.getPhnum());
			fw.write("#");//구분자
			fw.write(temp.getBirth());
			fw.write("#");//구분자
			fw.write(Integer.toString(temp.getUse_count()));
			fw.write("#");//구분자
			fw.write("\r\n");
			fw.flush();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
		}
	}
	public int file_find(int code) {   //https://goo.gl/NWRSEo, 구현 완료
		System.out.println("비교할 번호"+code);
		int finder=code;//찾을 회원번호.
		String temp_st=new String();
		String temp_line=new String();
		int i;
		int count=0;
		int positon=-1;
		boolean breaker=false;
		
		try {
//			fw=new FileWriter(file);
			fr=new FileReader(file);
		while((i=fr.read())!=-1){		//EOF까지
			if(i==35){
				if(count==0){
					if(finder==Integer.parseInt(temp_st)){
						System.out.println("찾음"+temp_st);
						breaker=true;
					}
				}
				else	;
				
				count++;
			}
			else if (i==10){
				positon++;
				if(breaker){
					System.out.println(temp_line);		//불러온다!
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
			System.out.println("못찾음..");
			positon=-1;
			this.setTemp_user(null);
		}
		}	
		catch (IOException e) {
		// TODO 자동 생성된 catch 블록
		e.printStackTrace();
		}
		finally {
			if(fr!=null)try{fr.close();}catch(IOException e){}
		}
		return positon+1;
		
		
		
	}
	
	
	public void file_read(){					//1개 읽기, 쓰나?
		Object_user temp=new Object_user();
		String temp_st=new String();
		int i=0;
		int count=0; //# 몇번?
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
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			finally{
			if(fr!=null)try {fr.close();} catch (IOException e) {}
			}
			System.out.println("파싱 데이터::");
			System.out.println("이름:"+temp.name);
			System.out.println("폰번호:"+temp.phnum);
			System.out.println("가입일:"+temp.birth);
			System.out.println("번호 :: "+temp.user_num);			
	}

	public void user_read(String buffer){		//파싱 끗.
//		System.out.println("도달확인, 길이 : "+buffer.length());
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
//			System.out.println("문자열:"+temp);
		}
		setTemp_user(user);
		
//		System.out.println("저장된 데이터::");
//		System.out.println("이름:"+user.name);
//		System.out.println("폰번호:"+user.phnum);
//		System.out.println("가입일:"+user.birth);
//		System.out.println("번호 :: "+user.user_num);		
		
	}	
	
	public void file_delete(int code){	//http://mantdu.tistory.com/731 참고
		//이거 검색+삭제잖아;				//http://aroundck.tistory.com/11 요거도 참고
		System.out.println("삭제할 번호"+code);
		BufferedReader br=null;
		FileWriter fw=null;

		int postion = this.file_find(code);
		System.out.println("포지션 위치"+postion);
		int nowline=1;

		if(postion!=0){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));			
			} catch (IOException e1) {
				// TODO 자동 생성된 catch 블록
				e1.printStackTrace();
			}
		String temp="";
		String line="";

			try {
				while ((line=br.readLine())!=null){
					System.out.println(line);
					if(postion==nowline){
						System.out.println("삭제 일치");
					}
					else
						temp+=(line+"\r\n");
									
					nowline++;
				}			
				System.out.println("temp:"+temp+"temp 끝");
				fw = new FileWriter(file_addr);
				System.out.println("temp:"+temp+"temp 끝");
				fw.write(temp);
				System.out.println("temp:"+temp+"temp 끝");
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally{
				try{fw.close();}catch(IOException e){}
			}
		}
		else
			System.out.println("삭제될 데이터가 없습니다");
	}
	
	public int file_countplus(int code){	//카운트값 1 올려주는거. delete하고 유사.
		System.out.println("늘려줄 번호"+code);
		BufferedReader br=null;
		FileWriter fw=null;
		Object_user temp_user = null;
		int postion = this.file_find(code);
		System.out.println("포지션 위치"+postion);
		int nowline=1;
		
		if(postion!=0){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));			
			} catch (IOException e1) {
				// TODO 자동 생성된 catch 블록
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
						System.out.println("확인");
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
				System.out.println("temp:"+temp+"temp 끝");
				fw = new FileWriter(file_addr);
				System.out.println("temp:"+temp+"temp 끝");
				fw.write(temp);
				System.out.println("temp:"+temp+"temp 끝");
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally{
				try{fw.close();}catch(IOException e){}
			}
			return 1;
		}
		else
			System.out.println("삭제될 데이터가 없습니다");
		return 0;
	}
	
	public void file_edit(int code){		//허미 시붤...
		
	}
}
