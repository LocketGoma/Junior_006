import java.awt.BorderLayout;
import java.awt.Color;
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
	JMenuItem bt_join;		//이것들 전부 새창에다 띄울거임.
	JMenuItem bt_find;		//찾기	<- 어케 띄울거냐고? 걍 텍스트필드로 뿌릴건데?
	JMenuItem bt_delete;	//삭제
	
	/*주문 파트*/
		/*날짜 부분*/
	Date now = new Date();
	JTextField input_number;
	SpinnerDateModel model = new SpinnerDateModel(now,now,null,Calendar.DAY_OF_WEEK);	//현재 기준, 현재~미래
	JSpinner spin = new JSpinner(model);
	
	
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
	
		/*삭제 파트*/
	JFrame fm_delete;
	
	public UI_restaurant(){
		//this.set_join();    <- 얘가 작동함 = 액션리스너 문제임. :: 작동합니다 = 액션리스너 문제입니다.
	}
	public UI_restaurant(Data_menu master){
		this.master=master;
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
		
		bt_find = new JMenuItem("고객검색");
		bt_join = new JMenuItem("신규가입");
		bt_delete = new JMenuItem("탈퇴메뉴");
		
		bt_find.addActionListener(new Action_compound(this));
		bt_join.addActionListener(new Action_compound(this));
		bt_delete.addActionListener(new Action_compound(this));
		
		
		bt_user.add(bt_find);
		bt_user.add(bt_join);
		bt_user.add(bt_delete);		
		
		menubar.add(bt_order);
		menubar.add(bt_user);
		menubar.add(bt_join);
		setJMenuBar(menubar);
	}
	

	
	private void select_menu(){			//주문 메뉴 선택
		select_menu.addItem("김밥");
		select_menu.addItem("떡볶이");
		select_menu.addItem("탕수육");
		select_menu.setBackground(new Color(255, 255, 255));
		
		select_menu.setEditable(false);
	}
	private void set_data_spindel(){		//날짜 선택메뉴.
		JSpinner.DateEditor edit = new JSpinner.DateEditor(spin, "yyyy/MM/dd");
		JFormattedTextField ftf = edit.getTextField();
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
		
		bt_orderby.addMouseListener(new Action_mouse(this));
		bt_cancel.addMouseListener(new Action_mouse(this,master));
		
		
		pn_button.add(bt_orderby);
		pn_button.add(bt_cancel);
	}
	
	
	/*2차메뉴들*/
		/*회원가입*/
	public void set_join(){
		joinmenu = new UI_Join(master);
		System.out.println("출력테스트");
		
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
		JTextField tf_find = new JTextField(4);
		JLabel lb_find = new JLabel("고객 번호  :: ");
		fm_find.setSize(200, 70);
		fm_find.setResizable(false);
		
		pn_find.add(lb_find);
		pn_find.add(tf_find);
		fm_find.add(pn_find);		
	}
	private void win_delete(){
		fm_delete = new JFrame("삭제 화면");
		fm_delete.setLayout(new BorderLayout());
		JPanel pn_delete = new JPanel();
		JTextField tf_delete = new JTextField(4);
		JLabel lb_delete = new JLabel("고객 번호  :: ");
		JLabel lb_warning = new JLabel("경고 :: 삭제 시 되돌릴 수 없습니다.");
		lb_warning.setForeground(Color.RED);
		fm_delete.setSize(220, 100);
		fm_delete.setResizable(false);
		
		
		pn_delete.add(lb_delete);
		pn_delete.add(tf_delete);
		fm_delete.add("North",lb_warning);
		fm_delete.add("Center", pn_delete);
		
	}
	


}

class UI_Join extends JFrame{
Data_menu master;			//상위 클래스.
	//가입창 전용 class, 한번만 씁니다만.
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
	private void start(){
		this.setLayout(new BorderLayout());
		this.set_layout();
		this.setSize(400,300);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void set_layout(){
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
	private void set_code(){
		input_code = new JTextField(4);
		input_code.setHorizontalAlignment(JTextField.CENTER);
		input_code.setText("0000");	
	}
	private void set_name(){
		input_name = new JTextField(10);
		input_name.setHorizontalAlignment(JTextField.CENTER);
		input_name.setText("김핫산");	
	}

	private void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}

	@SuppressWarnings("deprecation")
	private void set_date(){			//deprecation 문제되면 걍 빼면 그만;
		Date now = new Date();
		String date;
		date = now.getYear()+"/"+now.getMonth()+"/"+now.getDate();
		input_date = new JTextField();
		input_date.setHorizontalAlignment(JTextField.CENTER);
		input_date.setText(date);	
	}

	
	
}
