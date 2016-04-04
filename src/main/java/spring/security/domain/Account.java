package spring.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique=true)
    private String username;
    
    private String password;
    
    // 이 부분은 나중에 enum 과 일대다로 빼든 지 하는 작업이 필요할 것으로 보임. 
    // 시큐리티 튜토리얼이므로 간략하게 적습니다. 
    private String role;
    
    private String nickname;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", nickname="
                + nickname + "]";
    }
}