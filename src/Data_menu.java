import javax.swing.JFrame;
import javax.swing.JOptionPane;

//���� ������Ʈ
public class Data_menu {
	UI_restaurant rest;			//�ٷ� �ҷ�����.
	UI_Join join;				//Join ��Ʈ��. �길 �ٸ� Ŭ�����ϱ�.
	Object_user temp_user;		//user ��ü temp
	
	private JFrame frm = new JFrame();		// �������� ��¿�.
	Data_menu(){
		rest=new UI_restaurant(this);
	}
	public void start(){
		rest.start();
	}
	
	
	public void test(){
		System.out.println("��ȣ");
	}
	public void set_userdata(String name,String phnum,String birth,String usernumber){
		//�׽�Ʈ
		int user_num=Integer.parseInt(usernumber);
		System.out.println("�Ľ� ������::");
		System.out.println("�̸�:"+name);
		System.out.println("����ȣ:"+phnum);
		System.out.println("������:"+birth);
		System.out.println("��ȣ :: "+user_num);
		
		//valid_phnum(phnum);
		
		//���� üũ��Ʈ. �� 4���� ���� üũ
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
			order_usersave(name,phnum,birth,user_num);		//�� ����ؾ� �鿩������. ��
		
		
	}
	public void order_usersave(String name,String phnum,String birth,int user_num){
		temp_user.setName(name);
		temp_user.setBirth(birth);
		temp_user.setPhnum(phnum);
		temp_user.setUser_num(user_num);
		
		//������ �̰� Data_control�� �Ѱܼ� �����Ű�� �Ǵ°�.
	}
	
	private boolean valid_phnum(String phnum){	//�� ��ȣ ��ȿ �˻�. �ٽ� �����.
		int valid_mark=0;
		for(int i =0 ; i <14 ; i ++){
			System.out.println("�˻�:"+phnum.charAt(i));
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
	
	
	
}
