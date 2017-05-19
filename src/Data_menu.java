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
	
	private JFrame frm = new JFrame();		// 에러문구 출력용.
	Data_menu(){
		rest=new UI_restaurant(this);
		fileio = new Data_control();
	}
	public void start(){
		rest.start();
	}
	
	
	public void test(){
		System.out.println("야호");
	}
	public void set_userdata(String name,String phnum,String birth,String usernumber){
		//테스트
		int user_num=Integer.parseInt(usernumber);

		
		//valid_phnum(phnum);
		
		//에러 체크파트. 총 4가지 경우로 체크
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
		order_usersave(name,phnum,birth,user_num);		//다 통과해야 들여보내줌. ㅎ
		
	}
	public void order_usersave(String name,String phnum,String birth,int user_num){
		temp_user = new Object_user();
		System.out.println("파싱 데이터::");
		System.out.println("이름:"+name);
		System.out.println("폰번호:"+phnum);
		System.out.println("가입일:"+birth);
		System.out.println("번호 :: "+user_num);
		temp_user.setName(name);		
		temp_user.setBirth(birth);
		temp_user.setPhnum(phnum);
		temp_user.setUser_num(user_num);		
		//다음에 이걸 Data_control에 넘겨서 저장시키면 되는것.
		//테스트구문
		
		fileio.file_write(temp_user);
		temp_user=null;
	}
	
	private boolean valid_phnum(String phnum){	//폰 번호 유효 검사. 다시 만들것.
		int valid_mark=0;
		for(int i =0 ; i <14 ; i ++){
			System.out.println("검사:"+phnum.charAt(i));
			if (i==3||i==8){
				if('-'==phnum.charAt(i)){
					valid_mark++;
				}
			}
			else{
				if(phnum.charAt(i)>79&&phnum.charAt(i)<90){
					valid_mark++;
				}
			}				
		}
		System.out.println("valid?::"+valid_mark);
		
		if (valid_mark!=13){
			
			return false;
		}
		return true;
		
		
	}
	
	private void valid_code(int code){
		
	}
	
	public void print_usertype(int code){
		Object_user temp=null;
		System.out.println("코드 확인"+code);
		System.out.println("작동확인2");		
		if (code!=0){
		System.out.println("작동확인3");
		fileio.file_find(code);
		temp_user=fileio.getTemp_user();
		System.out.println("작동확인4");		
		if(temp_user!=null){
			//사용자 정보 리턴
			//temp=fileio.getTemp_user();
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
		
	}
}

//일단 멀티스레드 안쓰는거로 구현하고...
//class Data_finder implements Runnable{
class Data_finder {	
	Object_user temp_user;	//임시 저장형.
	Data_control fileio;	//그래서 검색은 어떻게? <- 걍 무식하게 object 다 긁을건데!
	int code;				//찾을 코드.
	private JFrame frm = new JFrame();		// 안내문구 출력용.
	private JFrame fr_print = new JFrame();
	private JPanel pn_print;
	private JTextArea ta_print;
//	private JLabel lb_print;
//	private JButton bt_print;
	
	Data_finder(){		
	}
	Data_finder(int code){
		this.code=code;
		fileio = new Data_control(this);
	}
//	@Override
//	public void run() {
//		// TODO 자동 생성된 메소드 스텁
//		JOptionPane.showMessageDialog(frm, "안내:검색중입니다.", "안내", JOptionPane.PLAIN_MESSAGE);		
//		
//		this.finder();
//		//break;
//	}
	public void start(){
		JOptionPane.showMessageDialog(frm, "안내:검색중입니다.", "안내", JOptionPane.PLAIN_MESSAGE);		
		this.finder();
		
		this.printer(fileio.getTemp_user());
	}
	private int finder(){
		int postion=-1;				//몇번째에 저장되어있는가. <- 못 찾으면 -1리턴 = 에러
		fileio.file_find(code);
		
		return postion;
	}
	public void printer(Object_user user){
		pn_print = new JPanel();
		ta_print = new JTextArea();
		if (user!=null){
			ta_print.append("이름:"+user.getName()+'\n');
			ta_print.append("회원번호:"+user.getUser_num()+'\n');
			ta_print.append("전화번호:"+user.getPhnum()+'\n');
			ta_print.append("가입일:"+user.getBirth());			
		}
		else
			ta_print.append("알림 : 존재하지 않는\n사용자입니다.");
		fr_print.setSize(150, 120);
		pn_print.add(ta_print);
		fr_print.add(pn_print);
		fr_print.setVisible(true);
	}
	
	
}