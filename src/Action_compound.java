import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//��� �׼��� �� �����ڽ��ϴ�.
public class Action_compound implements ActionListener, MenuListener {
	UI_restaurant rest;			//�ٷ� �ҷ�����.
	UI_Join join;				//Join ��Ʈ��. �길 �ٸ� Ŭ�����ϱ�.
	Data_menu master;
	
	Action_compound() throws Exception{
		throw new Exception();
	}
	Action_compound(UI_restaurant rest){
		this.rest=rest;
	}
	Action_compound(UI_restaurant rest, Data_menu master){
		this.rest=rest;
		this.master=master;
	}
/*	Action_compound(UI_Join join){
		this.join=join;
	}
*/
	Action_compound(UI_restaurant rest, UI_Join join){
		this.rest=rest;
		this.join=join;
	}
	
	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}


		// TODO �ڵ� ������ �޼ҵ� ����
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		System.out.println(e.getSource());
		if (e.getSource()==rest.bt_join)
			rest.set_join();
		else if(e.getSource()==rest.bt_find){
			rest.call_find();		
		}
		else if(e.getSource()==rest.bt_delete){
			rest.call_delete();
		}
		
	}
	
}

class Action_mouse implements MouseListener{
	UI_restaurant rest;			//�ٷ� �ҷ�����.
	UI_Join join;				//Join ��Ʈ��. �길 �ٸ� Ŭ�����ϱ�.
	Data_menu master;
	Data_finder find;
	
	Action_mouse(UI_restaurant rest){
		this.rest=rest;
	}
	Action_mouse(UI_restaurant rest, Data_menu master){
		this.rest=rest;
		this.master=master;
	}
	Action_mouse(UI_Join join, Data_menu master){
		this.join=join;
		this.master=master;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		System.out.println(e.getSource());
		try{
			if(e.getSource()==rest.bt_orderby){
			
			}
		}
		catch(NullPointerException ex){
			;
		}
		catch(Exception ex){
			ex.getMessage();
		}
		try{
			if(e.getSource()==join.bt_join){
				try{master.set_userdata(join.input_name.getText(),join.input_phnum.getText(),join.input_date.getText(),join.input_code.getText());
				}
				catch(Exception ex){
					ex.getMessage();
				}
			}
		}
		catch(NullPointerException ex){
			;
		}
		catch(Exception ex){
			ex.getMessage();
		}
		
		try{
		if(e.getSource()==rest.bbt_find){
				System.out.println("�˻� ����");
				find=new Data_finder(Integer.parseInt(rest.tf_find.getText()));
//				Thread t = new Thread(find);
				find.start();
				System.out.println("�˻� ����");				
//				t.start();
				System.out.println("�˻� �Ϸ�");	
				return;
			}
		}
		catch (NullPointerException ex){
			;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
	
}

class Action_key implements KeyListener{
	UI_restaurant rest;			//�ٷ� �ҷ�����.
	UI_Join join;				//Join ��Ʈ��. �길 �ٸ� Ŭ�����ϱ�.
	Data_menu master;
	Data_finder find;
	
	
	Action_key (UI_restaurant rest){
		this.rest=rest;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		/*
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			System.out.println("?");
			if(e.getSource()==rest.tf_find){
				System.out.println("�˻� ����");
				find=new Data_finder(Integer.parseInt(rest.tf_find.getText()));
//				Thread t = new Thread(find);
				find.start();
				System.out.println("�˻� ����");				
//				t.start();
				System.out.println("�˻� �Ϸ�");	
				return;
			}
		}
		*/
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}