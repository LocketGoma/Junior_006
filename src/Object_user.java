import java.io.Serializable;

// 단순히 사용자 object 데이터 저장만 담당. 연산및 처리, 에러 컨트롤은 다른곳에서!
public class Object_user implements Serializable {		// 유저 클래스.
	/**
	 * 
	 */
	private static final long serialVersionUID = 8665071146922045683L;
	private String name="";			//회원이름
	private String phnum="";		//회원전번
	private String birth="";		//회원생일
	private int user_num=0;			//회원코드

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
