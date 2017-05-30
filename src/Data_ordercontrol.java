import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

public class Data_ordercontrol {
	String file_addr = "sale.txt";
	String buffer=null;					//주문 정보 싸그리 들어갈 스트링 버퍼.
	Data_menu master=null;
	File file = new File(file_addr);
	
	FileReader fr=null;
	FileWriter fw=null;
	Data_ordercontrol(){
		
	}
	Data_ordercontrol(Data_menu master){
		this.master = master;
	}
	public void write_order(){
		
	}
	
	
	/*자료 해석용 메소드*/
	private int match_menu(String menu){			//input 메뉴를 정해진 상수로 변환
		if (menu.equals("야채김밥"))
			return 1;
		else if (menu.equals("참치김밥"))
			return 2;
		else if (menu.equals("소고기김밥"))
			return 3;
		else if (menu.equals("떡볶이"))
			return 4;
		else if (menu.equals("치즈떡볶이"))
			return 5;
		else if (menu.equals("순대"))
			return 6;
		else if (menu.equals("수제돈까스"))
			return 7;
		else if (menu.equals("치즈돈까스"))
			return 8;
		else if (menu.equals("물만두"))
			return 9;
		else if (menu.equals("탕수육"))
			return 10;
		else if (menu.equals("김치피자탕수육"))
			return 11;
		else
			return 0;		
	}
	
	private int cost_menu(int code){				//메뉴 코드에 맞는 가격 리턴.
		switch (code){
		case 1 : return 1000; 
		case 2 : return 1500; 
		case 3 : return 2000;
		case 4 : return 2000;
		case 5 : return 2500; 
		case 6 : return 2500; 
		case 7 : return 2500;
		case 8 : return 3000;
		case 9 : return 3000; 
		case 10 : return 3500; 
		case 11 : return 4000;
		default : return 0;
		}		
	}
	
}
