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

// UI �κи� ���.
// ���� Ŭ������ �մϴ�.
// �ƴ� ���� �������������ʰ� �������ô°� �� �ƴ��ݾƟD����������������������������������������
@SuppressWarnings("serial")
public class UI_restaurant extends JFrame {
	Data_menu master;			//���� Ŭ����.
	
	/*��Ʈ�� �κ�*/	
	GridLayout grid = new GridLayout(4,2);		// �ƴϸ� Setbound �밡��.
	JPanel pn_date=new JPanel();				// https://goo.gl/4T1HD9 ����
	JPanel pn_addr=new JPanel();;
	JPanel pn_menu=new JPanel();;
	JPanel pn_button=new JPanel();;
	
	/*��� �޴� �� ����*/
	JMenuBar menubar;
	JMenu bt_order;
	JMenu bt_user;
	JMenuItem bt_log;
	JMenuItem bt_join;		//�̰͵� ���� ��â���� ������.
	JMenuItem bt_find;		//ã��	<- ���� ���ųİ�? �� �ؽ�Ʈ�ʵ�� �Ѹ��ǵ�?
	JMenuItem bt_edit;
	JMenuItem bt_delete;	//����
	
	/*�ֹ� ��Ʈ*/
		/*��¥ �κ�*/
	Date now = new Date();
	JTextField input_number;
	SpinnerDateModel model = new SpinnerDateModel(now,now,null,Calendar.DAY_OF_WEEK);	//���� ����, ����~�̷�
	JSpinner spin = new JSpinner(model);
	JSpinner.DateEditor edit;
	JFormattedTextField ftf;
	
		/*ȸ����ȣ �κ�*/
	JTextField input_addr;
	
		/*�޴� �κ�*/
	@SuppressWarnings("rawtypes")
	JComboBox select_menu = new JComboBox();
	
		/*����Ʈ ��ư*/
	JButton bt_orderby;
	JButton bt_cancel;
	
	/*ȸ������ ��Ʈ*/
	JTextField input_usercode;	//ȸ����ȣ
	UI_Join joinmenu;
	// ������ �ƿ� �� â�� �߰� �մϴ�.
	
		/*�˻� ��Ʈ*/
	JFrame fm_find;
	JTextField tf_find;
	JButton bbt_find;
	
		/*���� ���� ��Ʈ*/ // = �˻� + ȸ�����
	JFrame fm_edit;
	JTextField tf_edit;
	JButton bbt_edit;
	UI_edit editer;
	boolean edit_switch=false;
	
		/*���� ��Ʈ*/
	JFrame fm_delete;
	JTextField tf_delete;
	JButton bbt_delete;
	
	/*�ֹ����� ��Ʈ*/
	UI_log orderlog;
	
	
	public UI_restaurant(){
		//this.set_join();    <- �갡 �۵��� = �׼Ǹ����� ������. :: �۵��մϴ� = �׼Ǹ����� �����Դϴ�.
	}
	public UI_restaurant(Data_menu master){
		this.master=master;
		
		//editer=new UI_edit(master);
	}
	
	public void start(){
		//�߰� â ����
		this.win_find();
		this.win_delete();
		
		this.set_layout();
		this.frame();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void set_layout(){
		this.set_date();		//��¥ �Է�
		this.set_addr();		//ȸ����ȣ �Է�
		this.set_menu();		//�޴� ����
		this.set_button();
		add(pn_date);
		add(pn_addr);
		add(pn_menu);
		add(pn_button);
	}
	private void set_menubar(){
		menubar=new JMenuBar();
		bt_order=new JMenu("�ֹ�����");
		bt_user=new JMenu("������");
		
		bt_log=new JMenuItem("��������");
		
		bt_find = new JMenuItem("���˻�");
		bt_join = new JMenuItem("�ű԰���");
		bt_edit = new JMenuItem("��������");
		bt_delete = new JMenuItem("Ż��޴�");
		
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
	private void select_menu(){			//�ֹ� �޴� ����
		select_menu.addItem("��ä���");
		select_menu.addItem("��ġ���");
		select_menu.addItem("�Ұ����");
		select_menu.addItem("������");
		select_menu.addItem("ġ�����");
		select_menu.addItem("����");
		select_menu.addItem("�������");
		select_menu.addItem("ġ��");
		select_menu.addItem("������");
		select_menu.addItem("������");
		select_menu.addItem("��ġ����������");
		select_menu.setBackground(new Color(255, 255, 255));
		
		select_menu.setEditable(false);
	}
	private void set_data_spindel(){		//��¥ ���ø޴�.
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
		JLabel label_date = new JLabel("�ֹ�����    ::    ");
		pn_date.add("West",label_date);
		pn_date.add("Center",spin);
	}
	private void set_addr(){
		pn_addr.setLayout(new BorderLayout());		
		JLabel label_addr = new JLabel("����ȣ    ::    ");
		input_addr = new JTextField(4);
		input_addr.setHorizontalAlignment(JTextField.CENTER);
		input_addr.setText("0000");						//���� ������, 0000=���¹�ȣ
		pn_addr.add("West",label_addr);
		pn_addr.add("Center",input_addr);
	}
	private void set_menu(){
		pn_menu.setLayout(new BorderLayout());
		this.select_menu();
		JLabel label_menu = new JLabel("�޴�             ::    ");
		pn_menu.add("West",label_menu);
		pn_menu.add("Center",select_menu);
	}
	private void set_button(){
		bt_orderby = new JButton("�ֹ��ϱ�");
		bt_cancel = new JButton("�ֹ����");
		
		bt_orderby.addMouseListener(new Action_mouse(this,master));
		bt_cancel.addMouseListener(new Action_mouse(this));
		
		
		pn_button.add(bt_orderby);
		pn_button.add(bt_cancel);
	}
	
	
	/*2���޴���*/
		/*ȸ������*/
	public void set_join(){
		joinmenu = new UI_Join(master);
		System.out.println("����׽�Ʈ");
		
	}
	public void call_edit(){
		System.out.println("����Ʈ �� 2");
		edit_switch=true;
		this.call_find();		
	}
	public void call_editer(Object_user temp,int code){
		System.out.println("����Ʈ �� 3");	
		editer=new UI_edit(master,temp,code);
	}
	public void set_editer(){
		System.out.println("����Ʈ �� 0");
		edit_switch=false;
	}
	public void call_find(){
		fm_find.setVisible(true);
	}
	public void call_delete(){
		fm_delete.setVisible(true);
	}
	private void win_find(){
		fm_find = new JFrame("�� �˻�â");
		JPanel pn_find = new JPanel();
		//pn_find.setLayout(null);
		tf_find = new JTextField(4);
		JLabel lb_find = new JLabel("�� ��ȣ  :: ");
		bbt_find = new JButton("�˻�");
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
		fm_delete = new JFrame("���� ȭ��");
		fm_delete.setLayout(new BorderLayout());
		JPanel pn_delete = new JPanel();
		tf_delete = new JTextField(4);
		JLabel lb_delete = new JLabel("�� ��ȣ  :: ");
		JLabel lb_warning = new JLabel("��� :: ���� �� �ǵ��� �� �����ϴ�.");
		lb_warning.setForeground(Color.RED);
		bbt_delete = new JButton("����");
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
class UI_Join extends JFrame{				//��� �ϼ��� �ڵ�ϱ� ��-��ġ
Data_menu master;			//���� Ŭ����.
	//����â ���� class, �ѹ��� ���ϴٸ�. <- ���̾� �����Ҷ��� ���ž�
JTextField input_code;
JTextField input_name;
JTextField input_phnum;
JTextField input_date;
JPanel pn_main;			//���� ȭ��.
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
		bt_join=new JButton("�ʰ����ð����ϱ�");
		bt_join.addMouseListener(new Action_mouse(this,master));
		
		this.set_code();
		this.set_name();
		this.set_phnum();
		this.set_date();
		JLabel label_code = new JLabel("����ȣ");
		JLabel label_name = new JLabel("����");
		JLabel label_phnum = new JLabel("��ȭ��ȣ");
		JLabel label_date = new JLabel("������"); // Auto.
		
		
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
		input_name.setText("���ֻ�");	
	}
	protected void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}
	@SuppressWarnings("deprecation")
	protected void set_date(){			//deprecation �����Ǹ� �� ���� �׸�;
		Date now = new Date();
		String date;
		date = now.getYear()+"/"+now.getMonth()+"/"+now.getDate();
		input_date = new JTextField();
		input_date.setHorizontalAlignment(JTextField.CENTER);
		input_date.setText(date);	
	}
}

@SuppressWarnings("serial")
//class UI_edit extends UI_Join{						//�׳� Edit ������ �̰� ����ϰ� ����� �Ǵ°� �ƴұ�
class UI_edit extends JFrame{						//�׳� Edit ������ �̰� ����ϰ� ����� �Ǵ°� �ƴұ�
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
		System.out.println("����Ʈ �� 4");
		this.master = master;
		this.code = code;
		this.temp = temp;
	//	this.set_layout();					// �̰� �Ѹ� edit.bt_join�� �νĸ��ϰ�
		this.start();						// <- �̰� ������. �̰� �Ѹ� master�� null��...
	}
	protected void start(){
		System.out.println("�۵� Ȯ����");
		this.setLayout(new BorderLayout());
		this.set_layout();
		this.setSize(400,300);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void set_layout(){				// ���⼭�� master = null
		System.out.println("����Ʈ �� 6");
		if (master!=null)					
			System.out.println("����Ʈ �� 7");
		pn_main=new JPanel();
		pn_main.setLayout(grid);
		bt_edit=new JButton("ȸ�� ���� ����");
		bt_edit.addMouseListener(new Action_mouse(this,master));		
		
		this.set_code();
		this.set_name();
		this.set_phnum();
		this.set_date();
		JLabel label_code = new JLabel("����ȣ");
		JLabel label_name = new JLabel("����");
		JLabel label_phnum = new JLabel("��ȭ��ȣ");
		JLabel label_date = new JLabel("������"); // Auto.
		
		
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
		input_name.setText("���ֻ�");	
	}
	protected void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}
	@SuppressWarnings("deprecation")
	protected void set_date(){			//deprecation �����Ǹ� �� ���� �׸�;
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
JTextArea menu_log=new JTextArea(10,25);		//�α� �ѷ��� ��
String log_buffer;			//�α� ����

JTextField date_start;
JTextField date_end;


JPanel pn_high;				//��¥ �ΰ�
JPanel pn_middle;			//�޴� �α�
JPanel pn_low;				//��ư��

JButton bt_search;				//�˻�
JButton bt_exit;				//�۵��Ϸ���. �ϴ� �ȳ���.

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
			// TODO �ڵ� ������ catch ���
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
		JLabel date=new JLabel("��¥ :: ");
		JLabel andmark=new JLabel("~");
		JLabel end = new JLabel("����");
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
		menu_log.setText("�޴�         ȸ��               ����ݾ�\n==========================\n");
		menu_log.append(buffer);
	}
	
	private void set_low(){
		pn_low = new JPanel();
		bt_search = new JButton("�˻�");
		
		bt_search.addMouseListener(new Action_mouse(this,master));
		
		
		pn_low.add(bt_search);
	}
	
	
}
