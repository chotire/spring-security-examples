package spring.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Authority implements Serializable {
	@Id
	private String username;
	
	@Id
	@Column(name = "role_id")
	private String roleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
	private Account account;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}