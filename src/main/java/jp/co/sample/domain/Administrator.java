package jp.co.sample.domain;


/**
 * @author ryuheisugita
 *
 *管理者情報を表すドメイン
 */
public class Administrator {

	private Integer id;
	private String name;
	private String mail_address;
	private String password;
	
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mail_address=" + mail_address + ", password="
				+ password + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
