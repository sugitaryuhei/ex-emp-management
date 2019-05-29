package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ryuheisugita
 *ログイン時に使用するフォーム
 */
public class LoginForm {
	
	@NotBlank(message="メールアドレスは必須です")
	private String mailAddress;
	@Size(min=5,max=10,message="パスワードは五文字以上十文字以下です")
	private String password;
	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	public String getMailAddress() {
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
