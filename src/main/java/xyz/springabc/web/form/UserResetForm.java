package xyz.springabc.web.form;

import javax.validation.constraints.Size;

public class UserResetForm {
	private String email;
	private String password;
	private String password0;
	@Size(min=6,max=18,message="密码六到十八个字符")
	private String password1;
	private String password2;
	private String username;
	private String code;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword0() {
		return password0;
	}
	public void setPassword0(String password0) {
		this.password0 = password0;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
