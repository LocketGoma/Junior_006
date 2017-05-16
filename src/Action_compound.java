import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//모든 액션을 다 때려박습니다.
public class Action_compound implements ActionListener, MenuListener {
	UI_restaurant rest;			//바로 불러오기.
	UI_Join join;				//Join 파트용. 얘만 다른 클래스니까.
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
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 자동 생성된 메소드 스텁
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
	UI_restaurant rest;			//바로 불러오기.
	UI_Join join;				//Join 파트용. 얘만 다른 클래스니까.
	Data_menu master;
	
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
		// TODO 자동 생성된 메소드 스텁
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
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	
}