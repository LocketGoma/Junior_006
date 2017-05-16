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

// UI �κи� ���.
// ���� Ŭ������ �մϴ�.
// �ƴ� ���� �������������ʰ� �������ô°� �� �ƴ��ݾƟD����������������������������������������
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
	JMenuItem bt_join;		//�̰͵� ���� ��â���� ������.
	JMenuItem bt_find;		//ã��	<- ���� ���ųİ�? �� �ؽ�Ʈ�ʵ�� �Ѹ��ǵ�?
	JMenuItem bt_delete;	//����
	
	/*�ֹ� ��Ʈ*/
		/*��¥ �κ�*/
	Date now = new Date();
	JTextField input_number;
	SpinnerDateModel model = new SpinnerDateModel(now,now,null,Calendar.DAY_OF_WEEK);	//���� ����, ����~�̷�
	JSpinner spin = new JSpinner(model);
	
	
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
	
		/*���� ��Ʈ*/
	JFrame fm_delete;
	
	public UI_restaurant(){
		//this.set_join();    <- �갡 �۵��� = �׼Ǹ����� ������. :: �۵��մϴ� = �׼Ǹ����� �����Դϴ�.
	}
	public UI_restaurant(Data_menu master){
		this.master=master;
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
		
		bt_find = new JMenuItem("���˻�");
		bt_join = new JMenuItem("�ű԰���");
		bt_delete = new JMenuItem("Ż��޴�");
		
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
	

	
	private void select_menu(){			//�ֹ� �޴� ����
		select_menu.addItem("���");
		select_menu.addItem("������");
		select_menu.addItem("������");
		select_menu.setBackground(new Color(255, 255, 255));
		
		select_menu.setEditable(false);
	}
	private void set_data_spindel(){		//��¥ ���ø޴�.
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
		
		bt_orderby.addMouseListener(new Action_mouse(this));
		bt_cancel.addMouseListener(new Action_mouse(this,master));
		
		
		pn_button.add(bt_orderby);
		pn_button.add(bt_cancel);
	}
	
	
	/*2���޴���*/
		/*ȸ������*/
	public void set_join(){
		joinmenu = new UI_Join(master);
		System.out.println("����׽�Ʈ");
		
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
		JTextField tf_find = new JTextField(4);
		JLabel lb_find = new JLabel("�� ��ȣ  :: ");
		fm_find.setSize(200, 70);
		fm_find.setResizable(false);
		
		pn_find.add(lb_find);
		pn_find.add(tf_find);
		fm_find.add(pn_find);		
	}
	private void win_delete(){
		fm_delete = new JFrame("���� ȭ��");
		fm_delete.setLayout(new BorderLayout());
		JPanel pn_delete = new JPanel();
		JTextField tf_delete = new JTextField(4);
		JLabel lb_delete = new JLabel("�� ��ȣ  :: ");
		JLabel lb_warning = new JLabel("��� :: ���� �� �ǵ��� �� �����ϴ�.");
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
Data_menu master;			//���� Ŭ����.
	//����â ���� class, �ѹ��� ���ϴٸ�.
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
	private void set_code(){
		input_code = new JTextField(4);
		input_code.setHorizontalAlignment(JTextField.CENTER);
		input_code.setText("0000");	
	}
	private void set_name(){
		input_name = new JTextField(10);
		input_name.setHorizontalAlignment(JTextField.CENTER);
		input_name.setText("���ֻ�");	
	}

	private void set_phnum(){
		input_phnum = new JTextField(13);
		input_phnum.setHorizontalAlignment(JTextField.CENTER);
		input_phnum.setText("000-0000-0000");	
	}

	@SuppressWarnings("deprecation")
	private void set_date(){			//deprecation �����Ǹ� �� ���� �׸�;
		Date now = new Date();
		String date;
		date = now.getYear()+"/"+now.getMonth()+"/"+now.getDate();
		input_date = new JTextField();
		input_date.setHorizontalAlignment(JTextField.CENTER);
		input_date.setText(date);	
	}

	
	
}
