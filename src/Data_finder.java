import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//�ϴ� ��Ƽ������ �Ⱦ��°ŷ� �����ϰ�...
//class Data_finder implements Runnable{
class Data_finder {	
	Object_user temp_user;	//�ӽ� ������.
	Data_control fileio;	//�׷��� �˻��� ���? <- �� �����ϰ� object �� �����ǵ�!
	int code;				//ã�� �ڵ�.
	private JFrame frm = new JFrame();		// �ȳ����� ��¿�.
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
//		// TODO �ڵ� ������ �޼ҵ� ����
//		JOptionPane.showMessageDialog(frm, "�ȳ�:�˻����Դϴ�.", "�ȳ�", JOptionPane.PLAIN_MESSAGE);		
//		
//		this.finder();
//		//break;
//	}
	public void start(){
		this.finder();
		JOptionPane.showMessageDialog(frm, "�ȳ�:�˻����Դϴ�.", "�ȳ�", JOptionPane.PLAIN_MESSAGE);		
		this.printer(fileio.getTemp_user());
	}	
	public int finder(){
		int postion=-1;				//���°�� ����Ǿ��ִ°�. <- �� ã���� -1���� = ����
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
			ta_print.append("�̸�:"+user.getName()+'\n');
			ta_print.append("ȸ����ȣ:"+user.getUser_num()+'\n');
			ta_print.append("��ȭ��ȣ:"+user.getPhnum()+'\n');
			ta_print.append("������:"+user.getBirth());			
		}
		else
			ta_print.append("�˸� : �������� �ʴ�\n������Դϴ�.");
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