/*구조 :::
 * [] - 사용자에게 보이는 부분
 * main-->data_menu(메인 로직클래스)->[UI_rest]
 * 			ㄴData_control(데이터클래스) ㄴAction_compound
 * */


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data_menu rest = new Data_menu();
		rest.start();

		// 
		
	}

}
