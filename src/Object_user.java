import java.io.Serializable;

// �ܼ��� ����� object ������ ���常 ���. ����� ó��, ���� ��Ʈ���� �ٸ�������!
public class Object_user implements Serializable {		// ���� Ŭ����.
	/**
	 * 
	 */
	private static final long serialVersionUID = 8665071146922045683L;
	private String name="";			//ȸ���̸�
	private String phnum="";		//ȸ������
	private String birth="";		//ȸ������
	private int user_num=0;			//ȸ���ڵ�

	private int use_count=0;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;		
	}
	public String getPhnum() {
		return phnum;
	}
	public void setPhnum(String phnum) {
		this.phnum = phnum;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getUse_count() {
		return use_count;
	}	
	public void setUse_count() {
		use_count++;
	}	
}
