import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		fileio = new Data_control(this);
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
		this.finder();
		JOptionPane.showMessageDialog(frm, "안내:검색중입니다.", "안내", JOptionPane.PLAIN_MESSAGE);		
		this.printer(fileio.getTemp_user());
	}	
	public int finder(){
		int postion=-1;				//몇번째에 저장되어있는가. <- 못 찾으면 -1리턴 = 에러
		postion=fileio.file_find(code);		
		return postion;
	}
	public Object_user giver(){
		return fileio.getTemp_user();
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
	public void deleter(int code){
		fileio.file_delete(code);
	}
	
	public static void editer(int code, int edit) throws InterruptcodeException{
		if (code!=edit)
			new InterruptcodeException(code);		
	}
	
}