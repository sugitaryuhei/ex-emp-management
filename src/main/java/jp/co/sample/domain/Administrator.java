package jp.co.sample.domain;


/**
 * 管理者情報を表すドメイン.
 * 
 * @author ryuheisugita
 *
 */
public class Administrator {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mail_address=" + mailAddress + ", password="
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
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
