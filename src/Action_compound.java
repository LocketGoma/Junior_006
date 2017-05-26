import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


		// TODO 자동 생성된 메소드 스텁
		
	

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
		else if(e.getSource()==rest.bt_edit){
			System.out.println("에디트 콜 1");
			rest.call_edit();
		}
		
	}
	
}

class Action_mouse implements MouseListener{
	UI_restaurant rest;			//바로 불러오기.
	UI_Join join;				//Join 파트용. 얘만 다른 클래스니까.
	Data_menu master;
	Data_finder find;
	UI_edit edit;
	int code;
	
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
	Action_mouse(UI_edit edit, Data_menu master){
		System.out.println("에디트 콜 8");
		this.edit=edit;
		this.master=master;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 자동 생성된 메소드 스텁
		System.out.println(e.getSource());
		try{
			if(e.getSource()==rest.bt_orderby){			// 주문
			System.out.println("작동확인1");	
			master.print_usertype(Integer.parseInt(rest.input_addr.getText()));
			}
		}
		catch(NullPointerException ex){
			;
		}
		catch(Exception ex){
			ex.getMessage();
		}
		try{
			if(e.getSource()==join.bt_join){			// 가입
				try{master.set_userdata(join.input_name.getText(),join.input_phnum.getText(),join.input_date.getText(),join.input_code.getText(),false);
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
			if(e.getSource()==rest.bbt_find&&rest.edit_switch==true){		//에디터 스위치가 켜젔는가.
				System.out.println("에디트 콜 5");
				code = Integer.parseInt(rest.tf_find.getText());
				find=new Data_finder(code);
		
				System.out.println(find.finder());
				//if(find.finder()!=0){
					rest.call_editer(find.giver(),code);
				//}
					rest.set_editer();
			}				
		}catch (NullPointerException ex){
			;
		}		
		
		try{
		if(e.getSource()==rest.bbt_find&&rest.edit_switch==false){				// 검색			
				System.out.println("검색 시작");
				find=new Data_finder(Integer.parseInt(rest.tf_find.getText()));
//				Thread t = new Thread(find);
				find.start();
//				System.out.println("검색 개시");				
//				t.start();
//				System.out.println("검색 완료");	
				return;
			}
		}
		catch (NullPointerException ex){
			;
		}
		try{
			if(e.getSource()==edit.bt_join){
				System.out.println("수정시작");
				if (master==null)
				System.out.println("뭔가 잘못됐는데");
				try{
					Data_finder.editer(edit.code,Integer.parseInt(edit.input_code.getText()));				//틀리면 인터럽트 퉷
					System.out.println("수정체크");					
					master.set_userdata(join.input_name.getText(),join.input_phnum.getText(),join.input_date.getText(),join.input_code.getText(),true);
				}
				catch (InterruptcodeException ex){
					ex.getMessage();
				}
				catch(Exception ex){
					ex.getMessage();
				}												//코드 받아오기가 문제.				
				
//				System.out.println("코드 확인 :"+ edit.code);	// 비교는 할수 있을거 같고.
//				if(Integer.parseInt(edit.input_code.getText())!=edit.code){		//비교 되고. 
//					System.out.println("다른데요!")
//					;
//				}
			//	
			}
		}
		catch (NullPointerException ex){
			;
		}
		try{
		if(e.getSource()==rest.bbt_delete){				// 삭제
			find=new Data_finder();
			find.deleter(Integer.parseInt(rest.tf_delete.getText()));
			}
		}
		catch (NullPointerException ex){
			;
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

class Action_key implements KeyListener{
	UI_restaurant rest;			//바로 불러오기.
	UI_Join join;				//Join 파트용. 얘만 다른 클래스니까.
	Data_menu master;
	Data_finder find;
	
	
	Action_key (UI_restaurant rest){
		this.rest=rest;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		/*
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			System.out.println("?");
			if(e.getSource()==rest.tf_find){
				System.out.println("검색 시작");
				find=new Data_finder(Integer.parseInt(rest.tf_find.getText()));
//				Thread t = new Thread(find);
				find.start();
				System.out.println("검색 개시");				
//				t.start();
				System.out.println("검색 완료");	
				return;
			}
		}
		*/
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}