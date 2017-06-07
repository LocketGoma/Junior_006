import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


//내부 로직파트
public class Data_menu {
	UI_restaurant rest;			//바로 불러오기.
	UI_Join join;				//Join 파트용. 얘만 다른 클래스니까.
	Object_user temp_user=null;		//user 객체 temp
	Data_control fileio;
	Data_ordercontrol orderio;
	
	private JFrame frm = new JFrame();		// 에러문구 출력용.
	Data_menu(){
		rest=new UI_restaurant(this);
		fileio = new Data_control();
		orderio = new Data_ordercontrol();
	}
	public void start(){
		rest.start();
	}
	

	public void set_userdata(String name,String phnum,String birth,String usernumber,boolean edit){
		//테스트
		
		//valid_phnum(phnum);
		
		//에러 체크파트. 총 4가지 경우로 체크
		/*
		if (name.length()>10){
			 JOptionPane.showMessageDialog(frm, "에러:이름이 너무 깁니다.", "입력에러", JOptionPane.ERROR_MESSAGE);		        
		}
		else if(phnum.length()!=13){
			 JOptionPane.showMessageDialog(frm, "에러:전화번호 길이가 잘못되었습니다.", "입력에러", JOptionPane.ERROR_MESSAGE);
		}
		else if(birth.length()!=10){
			JOptionPane.showMessageDialog(frm, "에러:날짜입력 길이가 잘못되었습니다.", "입력에러", JOptionPane.ERROR_MESSAGE);
		}
		else if(user_num>9999||user_num<1){
			JOptionPane.showMessageDialog(frm, "에러:회원번호가 잘못되었습니다.", "입력에러", JOptionPane.ERROR_MESSAGE);
		}
		else
		*/
		System.out.println("들어가니?");
		try{
			valid_code(usernumber);
			valid_phnum(phnum);			
			valid_name(name);
			valid_birth(birth);
			
			order_usersave(name,phnum,birth,Integer.parseInt(usernumber),edit);		//다 통과해야 들여보내줌. ㅎ
		}	catch (FormatenotcorrectException e){		//1,3 동시에 캐치됨.
			JOptionPane.showMessageDialog(frm, "에러:잘못된 입력입니다.", "입력에러", JOptionPane.ERROR_MESSAGE);
		} 	catch (OverSizeException e) {					
			// TODO 자동 생성된 catch 블록
			JOptionPane.showMessageDialog(frm, "에러:입력길이가 초과되었습니다.", "입력에러", JOptionPane.ERROR_MESSAGE);
		} 
		
		
//		
		
	}
	public void order_usersave(String name,String phnum,String birth,int user_num,boolean edit){
		//edit = 에디터인지 아닌지
		temp_user = new Object_user();		
		System.out.println("파싱 데이터::");
		System.out.println("이름:"+name);
		System.out.println("폰번호:"+phnum);
		System.out.println("가입일:"+birth);
		System.out.println("번호 :: "+user_num);
		
		try{
			if (edit!=true)check_code(user_num);		
			temp_user.setName(name);		
			temp_user.setBirth(birth);
			temp_user.setPhnum(phnum);
			temp_user.setUser_num(user_num);		
		//다음에 이걸 Data_control에 넘겨서 저장시키면 되는것.
		//테스트구문
			if (edit==true){
				System.out.println("에디트 콜 13");		
			fileio.file_edit(user_num, temp_user);}	
			else if(edit==false)
			fileio.file_write(temp_user);
		}catch (InterruptcodeException e){		//예외처리
		JOptionPane.showMessageDialog(frm, "에러:이미 존재하는 사용자입니다", "입력에러", JOptionPane.ERROR_MESSAGE);			
		}
		temp_user=null;		
	}
	public void edit_userdata(){		//시발... 이게 숨어있을줄이야

	}
	
	public void order_write(String date, String menu){
		System.out.println("파싱 테스트");
		System.out.println("Date:"+date+"  menu:"+menu);
		orderio.write_order(date, menu);
	}
	public String order_read(String start,String end){
		return orderio.read_order(start, end);
	}
	
	public void print_usertype(int code){
		System.out.println("코드 확인"+code);
		System.out.println("작동확인2");		
		if (code!=0){
		System.out.println("작동확인3");
		fileio.file_find(code);
		temp_user=fileio.getTemp_user();
		System.out.println("작동확인4");
			if(temp_user!=null){
				//사용자 정보 리턴
				System.out.println("코드 확인"+code);
				System.out.println("이용 회수:"+temp_user.getUse_count());
	
				if(temp_user.getUse_count()==Data_control.bonus){
					JOptionPane.showMessageDialog(frm, code+"번 고객님 주문이 정상적으로 처리되었습니다.\n"+code+"번 고객님 쿠폰이 발송되었습니다.", "주문안내", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frm, code+"번 고객님 주문이 정상적으로 처리되었습니다.", "주문안내", JOptionPane.PLAIN_MESSAGE);
				fileio.file_countplus(code);
				
			}
			else
				JOptionPane.showMessageDialog(frm, "에러:존재하지않는 사용자입니다.", "검색실패", JOptionPane.ERROR_MESSAGE);
		}
		else if(code==0){
			JOptionPane.showMessageDialog(frm, "고객님 주문이 정상적으로 처리되었습니다.", "주문안내", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	//예외 검사 파트
	static void valid_phnum(String phnum)throws PhoneFormatnotcorrentException{	//폰 번호 유효 검사. 다시 만들것.
		for(int i =0 ; i <phnum.length() ; i ++){
			if(phnum.charAt(i)==45){
					//valid_mark++;
				;
			}
			else if((int)phnum.charAt(i)>47&&(int)phnum.charAt(i)<58){
					//valid_mark++;
				;
			}
			else
				throw new PhoneFormatnotcorrentException(phnum.charAt(i));
		}	
	}
	static void valid_birth(String birth)throws DateFormatnotcorrectException{	//가입일 번호 유효 검사. 다시 만들것.
		if (birth.length()!=10)
			throw new DateFormatnotcorrectException(birth.charAt(5));
		for(int i =0 ; i <birth.length() ; i ++){
			System.out.println("검사:"+birth.charAt(i)+"(int):"+(int)birth.charAt(i));
			if(birth.charAt(i)==47){
					//valid_mark++;
				;
			}
			else if((int)birth.charAt(i)>47&&(int)birth.charAt(i)<58){
					//valid_mark++;
				;
			}
			else
				throw new DateFormatnotcorrectException(birth.charAt(i));
		}
	//	System.out.println("valid?::"+valid_mark);		
	}
	static void valid_code(String code) throws FormatenotcorrectException, OverSizeException{	//사이즈 초과도 넣을까		
		if (code.length()>4)
			throw new OverSizeException();
		for(int i=0;i<code.length();i++){
			if ((int)code.charAt(i)>47&&(int)code.charAt(i)<58){
			;	
			}
			else
				throw new FormatenotcorrectException(code.charAt(i));
		}
	}
	static void valid_name(String name) throws OverSizeException{
		if(name.length()>10)
			throw new OverSizeException();
	}
	void check_code(int code) throws InterruptcodeException{		//종속적임.
		if (fileio.file_find(code)!=0){
			throw new InterruptcodeException(code);
		}
	}
	
	
	
}
