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
	String buffer=null;					//�ֹ� ���� �α׸� �� ��Ʈ�� ����.
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
	
	
	/*�ڷ� �ؼ��� �޼ҵ�*/
	private int match_menu(String menu){			//input �޴��� ������ ����� ��ȯ
		if (menu.equals("��ä���"))
			return 1;
		else if (menu.equals("��ġ���"))
			return 2;
		else if (menu.equals("�Ұ����"))
			return 3;
		else if (menu.equals("������"))
			return 4;
		else if (menu.equals("ġ�����"))
			return 5;
		else if (menu.equals("����"))
			return 6;
		else if (menu.equals("�������"))
			return 7;
		else if (menu.equals("ġ��"))
			return 8;
		else if (menu.equals("������"))
			return 9;
		else if (menu.equals("������"))
			return 10;
		else if (menu.equals("��ġ����������"))
			return 11;
		else
			return 0;		
	}
	
	private int cost_menu(int code){				//�޴� �ڵ忡 �´� ���� ����.
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
