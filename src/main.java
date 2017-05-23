/*구조 :::
 * [] - 사용자에게 보이는 부분
 * main-->data_menu(메인 로직클래스)->[UI_rest]
 * 			ㄴData_control(데이터클래스) ㄴAction_compound
 * */
//남은것 : 사용자 수정 / 쓰레딩 / 예외처리 수정 (몇개 안남음)

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data_menu rest = new Data_menu();
		rest.start();
//		Data_control test = new Data_control();
//		test.file_find(11);
//		test.file_delete(111);// 삭제 테스트
//		test.file_countplus(3);
		
		
		
	}

}
