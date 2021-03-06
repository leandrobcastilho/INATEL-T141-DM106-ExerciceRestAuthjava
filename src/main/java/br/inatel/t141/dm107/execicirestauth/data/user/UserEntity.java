package br.inatel.t141.dm107.execicirestauth.data.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserEntity {

	public UserEntity() {
	}

	private Long id;
	private String login;
	private String password;

	public UserEntity(Long id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
