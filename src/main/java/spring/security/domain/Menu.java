package spring.security.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Menu {
    @Id
    private String url;
    
    private String name;
    
    private int sortOrder;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Permission", joinColumns = {@JoinColumn(name = "menu_url", referencedColumnName = "url")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private List<Role> roles;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}