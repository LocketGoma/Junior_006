import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


//���� ������Ʈ
public class Data_menu {
	UI_restaurant rest;			//�ٷ� �ҷ�����.
	UI_Join join;				//Join ��Ʈ��. �길 �ٸ� Ŭ�����ϱ�.
	Object_user temp_user=null;		//user ��ü temp
	Data_control fileio;
	Data_ordercontrol orderio;
	
	private JFrame frm = new JFrame();		// �������� ��¿�.
	Data_menu(){
		rest=new UI_restaurant(this);
		fileio = new Data_control();
		orderio = new Data_ordercontrol();
	}
	public void start(){
		rest.start();
	}
	

	public void set_userdata(String name,String phnum,String birth,String usernumber,boolean edit){
		//�׽�Ʈ
		
		//valid_phnum(phnum);
		
		//���� üũ��Ʈ. �� 4���� ���� üũ
		/*
		if (name.length()>10){
			 JOptionPane.showMessageDialog(frm, "����:�̸��� �ʹ� ��ϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);		        
		}
		else if(phnum.length()!=13){
			 JOptionPane.showMessageDialog(frm, "����:��ȭ��ȣ ���̰� �߸��Ǿ����ϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
		}
		else if(birth.length()!=10){
			JOptionPane.showMessageDialog(frm, "����:��¥�Է� ���̰� �߸��Ǿ����ϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
		}
		else if(user_num>9999||user_num<1){
			JOptionPane.showMessageDialog(frm, "����:ȸ����ȣ�� �߸��Ǿ����ϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
		}
		else
		*/
		System.out.println("����?");
		try{
			valid_code(usernumber);
			valid_phnum(phnum);			
			valid_name(name);
			valid_birth(birth);
			
			order_usersave(name,phnum,birth,Integer.parseInt(usernumber),edit);		//�� ����ؾ� �鿩������. ��
		}	catch (FormatenotcorrectException e){		//1,3 ���ÿ� ĳġ��.
			JOptionPane.showMessageDialog(frm, "����:�߸��� �Է��Դϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
		} 	catch (OverSizeException e) {					
			// TODO �ڵ� ������ catch ���
			JOptionPane.showMessageDialog(frm, "����:�Է±��̰� �ʰ��Ǿ����ϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
		} 
		
		
//		
		
	}
	public void order_usersave(String name,String phnum,String birth,int user_num,boolean edit){
		//edit = ���������� �ƴ���
		temp_user = new Object_user();		
		System.out.println("�Ľ� ������::");
		System.out.println("�̸�:"+name);
		System.out.println("����ȣ:"+phnum);
		System.out.println("������:"+birth);
		System.out.println("��ȣ :: "+user_num);
		
		try{
			if (edit!=true)check_code(user_num);		
			temp_user.setName(name);		
			temp_user.setBirth(birth);
			temp_user.setPhnum(phnum);
			temp_user.setUser_num(user_num);		
		//������ �̰� Data_control�� �Ѱܼ� �����Ű�� �Ǵ°�.
		//�׽�Ʈ����
			if (edit==true){
				System.out.println("����Ʈ �� 13");		
			fileio.file_edit(user_num, temp_user);}	
			else if(edit==false)
			fileio.file_write(temp_user);
		}catch (InterruptcodeException e){		//����ó��
		JOptionPane.showMessageDialog(frm, "����:�̹� �����ϴ� ������Դϴ�", "�Է¿���", JOptionPane.ERROR_MESSAGE);			
		}
		temp_user=null;		
	}
	public void edit_userdata(){		//�ù�... �̰� �����������̾�

	}
	
	public void order_write(String date, String menu){
		System.out.println("�Ľ� �׽�Ʈ");
		System.out.println("Date:"+date+"  menu:"+menu);
		orderio.write_order(date, menu);
	}
	public String order_read(String start,String end){
		return orderio.read_order(start, end);
	}
	
	public void print_usertype(int code){
		System.out.println("�ڵ� Ȯ��"+code);
		System.out.println("�۵�Ȯ��2");		
		if (code!=0){
		System.out.println("�۵�Ȯ��3");
		fileio.file_find(code);
		temp_user=fileio.getTemp_user();
		System.out.println("�۵�Ȯ��4");
			if(temp_user!=null){
				//����� ���� ����
				System.out.println("�ڵ� Ȯ��"+code);
				System.out.println("�̿� ȸ��:"+temp_user.getUse_count());
	
				if(temp_user.getUse_count()==Data_control.bonus){
					JOptionPane.showMessageDialog(frm, code+"�� ���� �ֹ��� ���������� ó���Ǿ����ϴ�.\n"+code+"�� ���� ������ �߼۵Ǿ����ϴ�.", "�ֹ��ȳ�", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(frm, code+"�� ���� �ֹ��� ���������� ó���Ǿ����ϴ�.", "�ֹ��ȳ�", JOptionPane.PLAIN_MESSAGE);
				fileio.file_countplus(code);
				
			}
			else
				JOptionPane.showMessageDialog(frm, "����:���������ʴ� ������Դϴ�.", "�˻�����", JOptionPane.ERROR_MESSAGE);
		}
		else if(code==0){
			JOptionPane.showMessageDialog(frm, "���� �ֹ��� ���������� ó���Ǿ����ϴ�.", "�ֹ��ȳ�", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	//���� �˻� ��Ʈ
	static void valid_phnum(String phnum)throws PhoneFormatnotcorrentException{	//�� ��ȣ ��ȿ �˻�. �ٽ� �����.
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
	static void valid_birth(String birth)throws DateFormatnotcorrectException{	//������ ��ȣ ��ȿ �˻�. �ٽ� �����.
		if (birth.length()!=10)
			throw new DateFormatnotcorrectException(birth.charAt(5));
		for(int i =0 ; i <birth.length() ; i ++){
			System.out.println("�˻�:"+birth.charAt(i)+"(int):"+(int)birth.charAt(i));
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
	static void valid_code(String code) throws FormatenotcorrectException, OverSizeException{	//������ �ʰ��� ������		
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
	void check_code(int code) throws InterruptcodeException{		//��������.
		if (fileio.file_find(code)!=0){
			throw new InterruptcodeException(code);
		}
	}
	
	
	
}
