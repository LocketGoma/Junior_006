import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;

// UI 부분만 담당.
// 메인 클래스긴 합니다.
// 아니 스윙 가르쳐주지도않고 스윙쓰시는건 좀 아니잖아욬ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
@SuppressWarnings("serial")
public class UI_restaurant extends JFrame {
	Data_menu master;			//상위 클래스.
	
	/*컨트롤 부분*/	
	GridLayout grid = new GridLayout(4,2);		// 아니면 Setbound 노가다.
	JPanel pn_date=new JPanel();				// https://goo.gl/4T1HD9 참고
	JPanel pn_addr=new JPanel();;
	JPanel pn_menu=new JPanel();;
	JPanel pn_button=new JPanel();;
	
	/*상단 메뉴 바 관련*/
	JMenuBar menubar;
	JMenu bt_order;
	JMenu bt_user;
	JMenuItem bt_log;
	JMenuItem bt_join;		//이것들 전부 새창에다 띄울거임.
	JMenuItem bt_find;		//찾기	<- 어케 띄울거냐고? 걍 텍스트필드로 뿌릴건데?
	JMenuItem bt_edit;
	JMenuItem bt_delete;	//삭제
	
	/*주문 파트*/
		/*날짜 부분*/
	Date now = new Date();
	JTextField input_number;
	SpinnerDateModel model = new SpinnerDateModel(now,now,null,Calendar.DAY_OF_WEEK);	//현재 기준, 현재~미래
	JSpinner spin = new JSpinner(model);
	JSpinner.DateEditor edit;
	JFormattedTextField ftf;
	
		/*회원번호 부분*/
	JTextField input_addr;
	
		/*메뉴 부분*/
	@SuppressWarnings("rawtypes")
	JComboBox select_menu = new JComboBox();
	
		/*셀렉트 버튼*/
	JButton bt_orderby;
	JButton bt_cancel;
	
	/*회원관리 파트*/
	JTextField input_usercode;	//회원번호
	UI_Join joinmenu;
	// 누르면 아예 새 창이 뜨게 합니다.
	
		/*검색 파트*/
	JFrame fm_find;
	JTextField tf_find;
	JButton bbt_find;
	
		/*정보 수정 파트*/ // = 검색 + 회원등록
	JFrame fm_edit;
	JTextField tf_edit;
	JButton bbt_edit;
	UI_edit editer;
	boolean edit_switch=false;
	
		/*삭제 파트*/
	JFrame fm_delete;
	JTextField tf_delete;
	JButton bbt_delete;
	
	/*주문관리 파트*/
	UI_log orderlog;
	
	
	public UI_restaurant(){
		//this.set_join();    <- 얘가 작동함 = 액션리스너 문제임. :: 작동합니다 = 액션리스너 문제입니다.
	}
	public UI_restaurant(Data_menu master){
		this.master=master;
		
		//editer=new UI_edit(master);
	}
	
	public void start(){
		//추가 창 오픈
		this.win_find();
		this.win_delete();
		
		this.set_layout();
		this.frame();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void set_layout(){
		this.set_date();		//날짜 입력
		this.set_addr();		//회원번호 입력
		this.set_menu();		//메뉴 선택
		this.set_button();
		add(pn_date);
		add(pn_addr);
		add(pn_menu);
		add(pn_button);
	}
	private void set_menubar(){
		menubar=new JMenuBar();
		bt_order=new JMenu("주문관리");
		bt_user=new JMenu("고객관리");
		
		bt_log=new JMenuItem("내역관리");
		
		bt_find = new JMenuItem("고객검색");
		bt_join = new JMenuItem("신규가입");
		bt_edit = new JMenuItem("정보수정");
		bt_delete = new JMenuItem("탈퇴메뉴");
		
		bt_find.addActionListener(new Action_compound(this));
		bt_join.addActionListener(new Action_compound(this));
		bt_edit.addActionListener(new Action_compound(this));
		bt_delete.addActionListener(new Action_compound(this));
		
		bt_log.addActionListener(new Action_compound(this));
		
		bt_user.add(bt_find);
		bt_user.add(bt_join);
		bt_user.add(bt_edit);
		bt_user.add(bt_delete);		
		
		menubar.add(bt_order);
		menubar.add(bt_user);
		menubar.add(bt_log);
//		menubar.add(bt_join);
		setJMenuBar(menubar);
	}
	

	
	@SuppressWarnings("unchecked")
	private void select_menu(){			//주문 메뉴 선택
		select_menu.addItem("야채김밥");
		select_menu.addItem("참치김밥");
		select_menu.addItem("소고기김밥");
		select_menu.addItem("떡볶이");
		select_menu.addItem("치즈떡볶이");
		select_menu.addItem("순대");
		select_menu.addItem("수제돈까스");
		select_menu.addItem("치즈돈까스");
		select_menu.addItem("물만두");
		select_menu.addItem("탕수육");
		select_menu.addItem("김치피자탕수육");
		select_menu.setBackground(new Color(255, 255, 255));
		
		select_menu.setEditable(false);
	}
	private void set_data_spindel(){		//날짜 선택메뉴.
		edit = new JSpinner.DateEditor(spin, "yyyy-MM-dd");
		ftf = edit.getTextField();
        ftf.setEditable(true);
        ftf.setHorizontalAlignment(JTextField.CENTER);
        ftf.setBackground(new Color(255, 255, 255));
        spin.setEditor(edit);        
	}
	private void frame(){
		this.set_menubar();
		this.setLayout(grid);
		this.setSize(250,300);
		this.setResizable(false);
	}
	private void set_date(){
		pn_date.setLayout(new BorderLayout());
		this.set_data_spindel();
		JLabel label_date = new JLabel("주문일자    ::    ");
		pn_date.add("West",label_date);
		pn_date.add("Center",spin);
	}
	private void set_addr(){
		pn_addr.setLayout(new BorderLayout());		
		JLabel label_addr = new JLabel("고객번호    ::    ");
		input_addr = new JTextField(4);
		input_addr.setHorizontalAlignment(JTextField.CENTER);
		input_addr.setText("0000");						//에러 방지용, 0000=없는번호
		pn_addr.add("West",label_addr);
		pn_addr.add("Center",input_addr);
	}
	private void set_menu(){
		pn_menu.setLayout(new BorderLayout());
		this.select_menu();
		JLabel label_menu = new JLabel("메뉴             ::    ");
		pn_menu.add("West",label_menu);
		pn_menu.add("Center",select_menu);
	}
	private void set_button(){
		bt_orderby = new JButton("주문하기");
		bt_cancel = new JButton("주문취소");
		
		bt_orderby.addMouseListener(new Action_mouse(this,master));
		bt_cancel.addMouseListener(new Action_mouse(this));
		
		
		pn_button.add(bt_orderby);
		pn_button.add(bt_cancel);
	}
	
	
	/*2차메뉴들*/
		/*회원가입*/
	public void set_join(){
		joinmenu = new UI_Join(master);
		System.out.println("출력테스트");
		
	}
	public void call_edit(){
		System.out.println("에디트 콜 2");
		edit_switch=true;
		this.call_find();		
	}
	public void call_editer(Object_user temp,int code){
		System.out.println("에디트 콜 3");	
		editer=new UI_edit(master,temp,code);
	}
	public void set_editer(){
		System.out.println("에디트 콜 0");
		edit_switch=false;
	}
	public void call_find(){
		fm_find.setVisible(true);
	}
	public void call_delete(){
		fm_delete.setVisible(true);
	}
	private void win_find(){
		fm_find = new JFrame("고객 검색창");
		JPanel pn_find = new JPanel();
		//pn_find.setLayout(null);
		tf_find = new JTextField(4);
		JLabel lb_find = new JLabel("고객 번호  :: ");
		bbt_find = new JButton("검색");
		fm_find.setSize(200, 90);
		fm_find.setResizable(false);
		
		bbt_find.addMouseListener(new Action_mouse(this));
		
		//tf_find.addKeyListener(new Action_key(this));
		
		pn_find.add(lb_find);
		pn_find.add(tf_find);
		fm_find.add("Center",pn_find);
		fm_find.add("South",bbt_find);
	}
	private void win_delete(){
		fm_delete = new JFrame("삭제 화면");
		fm_delete.setLayout(new BorderLayout());
		JPanel pn_delete = new JPanel();
		tf_delete = new JTextField(4);
		JLabel lb_delete = new JLabel("고객 번호  :: ");
		JLabel lb_warning = new JLabel("경고 :: 삭제 시 되돌릴 수 없습니다.");
		lb_warning.setForeground(Color.RED);
		bbt_delete = new JButton("삭제");
		fm_delete.setSize(220, 100);
		fm_delete.setResizable(false);
		
		bbt_delete.addMouseListener(new Action_mouse(this));
		
		pn_delete.add(lb_delete);
		pn_delete.add(tf_delete);
		fm_delete.add("North",lb_warning);
		fm_delete.add("Center", pn_delete);
		fm_delete.add("South",bbt_delete);
		
	}
	
	public void call_log(){
		orderlog=new UI_log(master);
		orderlog.start();
	}

}

@SuppressWarnings("serial")
class UI_Join extends JFrame{				//얘는 완성된 코드니까 노-터치
Data_menu master;			//상위 클래스.
	//가입창 전용 class, 한번만 씁니다만. <- 안이야 수정할때도 쓸거야
JTextField input_code;
JTextField input_name;
JTextField input_phnum;
JTextField input_date;
JPanel pn_main;			//메인 화면.
JButton bt_join;
	
	GridLayout grid = new GridLayout(4,2);
	public UI_Join(){
		this.start();
	}
	public UI_Join(Data_menu master){
		this.master=master;
		this.start();
	}
	protected void start(){
		this.setLayout(new BorderLayout());
		this.set_layout();
		this.setSize(400,300);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	protected void set_layout(){
		pn_main=new JPanel();
		pn_main.setLayout(grid);
		bt_join=new JButton("초고속즉시가입하기");
		bt_join.addMouseListener(new Action_mouse(this,master));
		
		this.set_code();
		this.set_name();
		this.set_phnum();
		this.set_date();
		JLabel label_code = new JLabel("고객번호");
		JLabel label_name = new JLabel("고객명");
		JLabel label_phnum = new JLabel("전화번호");
		JLabel label_date = new JLabel("가입일"); // Auto.
		
		
		pn_main.add(label_code);
		pn_main.add(input_code);
		pn_main.add(label_name);
		pn_main.add(input_name);
		pn_main.add(label_phnum);
		pn_main.add(input_phnum);
		pn_main.add(label_date);
		pn_main.add(input_date);
		
		add("Center",pn_main);
		add("South",bt_join);
		
	}
	protected void set_code(){
		input_code = new JTextField(4);
		input_code.setHorizontalAlignment(JTextField.CENTER);
		input_code.setText("0000");	
	}
	protected void set_name(){
		input_name = new JTextField(10);
		input_name.setHorizontalAlignment(JTextField.CENTER);
		input_name.setText("김핫산");	
	}
	protected void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}
	@SuppressWarnings("deprecation")
	protected void set_date(){			//deprecation 문제되면 걍 빼면 그만;
		Date now = new Date();
		String date;
		date = now.getYear()+"/"+now.getMonth()+"/"+now.getDate();
		input_date = new JTextField();
		input_date.setHorizontalAlignment(JTextField.CENTER);
		input_date.setText(date);	
	}
}

@SuppressWarnings("serial")
//class UI_edit extends UI_Join{						//그냥 Edit 누르면 이거 출력하게 만들면 되는거 아닐까
class UI_edit extends JFrame{						//그냥 Edit 누르면 이거 출력하게 만들면 되는거 아닐까
int code;
Data_menu master;
JTextField input_code;
JTextField input_name;
JTextField input_phnum;
JTextField input_date;
JPanel pn_main;	
Object_user temp;
JButton bt_edit;
GridLayout grid = new GridLayout(4,2);
	public UI_edit(Data_menu master){
		this.master=master;
	}
	public UI_edit(Data_menu master, int code){
		this.master=master;
		this.code = code;
	}
	public UI_edit(Data_menu master, Object_user temp){
		this.master=master;
		this.temp = temp;
	}
	public UI_edit(Data_menu master, Object_user temp,int code){
		System.out.println("에디트 콜 4");
		this.master = master;
		this.code = code;
		this.temp = temp;
	//	this.set_layout();					// 이걸 켜면 edit.bt_join을 인식못하고
		this.start();						// <- 이게 문제임. 이걸 켜면 master에 null이...
	}
	protected void start(){
		System.out.println("작동 확인중");
		this.setLayout(new BorderLayout());
		this.set_layout();
		this.setSize(400,300);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void set_layout(){				// 여기서도 master = null
		System.out.println("에디트 콜 6");
		if (master!=null)					
			System.out.println("에디트 콜 7");
		pn_main=new JPanel();
		pn_main.setLayout(grid);
		bt_edit=new JButton("회원 정보 수정");
		bt_edit.addMouseListener(new Action_mouse(this,master));		
		
		this.set_code();
		this.set_name();
		this.set_phnum();
		this.set_date();
		JLabel label_code = new JLabel("고객번호");
		JLabel label_name = new JLabel("고객명");
		JLabel label_phnum = new JLabel("전화번호");
		JLabel label_date = new JLabel("가입일"); // Auto.
		
		
		pn_main.add(label_code);
		pn_main.add(input_code);
		pn_main.add(label_name);
		pn_main.add(input_name);
		pn_main.add(label_phnum);
		pn_main.add(input_phnum);
		pn_main.add(label_date);
		pn_main.add(input_date);
		
		add("Center",pn_main);
		add("South",bt_edit);
	}


	protected void set_code(){
		input_code = new JTextField(4);
		input_code.setHorizontalAlignment(JTextField.CENTER);
		input_code.setText("0000");	
	}
	protected void set_name(){
		input_name = new JTextField(10);
		input_name.setHorizontalAlignment(JTextField.CENTER);
		input_name.setText("김핫산");	
	}
	protected void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}
	@SuppressWarnings("deprecation")
	protected void set_date(){			//deprecation 문제되면 걍 빼면 그만;
		Date now = new Date();
		String date;
		date = now.getYear()+"/"+now.getMonth()+"/"+now.getDate();
		input_date = new JTextField();
		input_date.setHorizontalAlignment(JTextField.CENTER);
		input_date.setText(date);	
	}
}

@SuppressWarnings("serial")
class UI_log extends JFrame{
Data_menu master;
JTextArea menu_log=new JTextArea(10,25);		//로그 뿌려질 곳
String log_buffer;			//로그 버퍼

JTextField date_start;
JTextField date_end;


JPanel pn_high;				//날짜 두개
JPanel pn_middle;			//메뉴 로그
JPanel pn_low;				//버튼들

JButton bt_search;				//검색
JButton bt_exit;				//작동하려나. 일단 안넣음.

	public UI_log(){
		
	}
	public UI_log(Data_menu master){
		this.master=master;
	}
	public void start(){
		this.set_layout();
		
		try {
			Thread.sleep(2000);
			this.set_midtext("hi");
		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
	}
	private void set_layout(){
		this.setLayout(new BorderLayout());
		this.set_high();
		this.set_middle();
		this.set_low();
		
		add("North",pn_high);
		add("Center",pn_middle);
		add("South",pn_low);
		
		this.setSize(400, 300);
		
		this.setVisible(true);
	}
	private void set_high(){
		JLabel date=new JLabel("날짜 :: ");
		JLabel andmark=new JLabel("~");
		JLabel end = new JLabel("까지");
		date_start=new JTextField("0000-00-00");
		date_end = new JTextField("0000-00-00");		
		pn_high=new JPanel();
		pn_high.setLayout(new FlowLayout());
		
		pn_high.add(date);
		pn_high.add(date_start);
		pn_high.add(andmark);
		pn_high.add(date_end);
		pn_high.add(end);
	
	}
	private void set_middle(){
		pn_middle = new JPanel();
		
		
		pn_middle.add(menu_log);
		
	}
	public void set_midtext(String buffer){
		menu_log.setText("메뉴         회수               매출금액\n==========================\n");
		menu_log.append(buffer);
	}
	
	private void set_low(){
		pn_low = new JPanel();
		bt_search = new JButton("검색");
		
		bt_search.addMouseListener(new Action_mouse(this,master));
		
		
		pn_low.add(bt_search);
	}
	
	
}
