package spring.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@SuppressWarnings("serial")
public class Permission implements Serializable {
	@Id
	@Column(name = "menu_url")
	private String menuUrl;
	
	@Id
	@Column(name = "role_id")
	private String roleId;

	private Boolean create = Boolean.FALSE;
	private Boolean read = Boolean.FALSE;
	private Boolean update = Boolean.FALSE;
	private Boolean delete = Boolean.FALSE;
	
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Boolean getCreate() {
		return create;
	}

	public void setCreate(Boolean create) {
		this.create = create;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
}