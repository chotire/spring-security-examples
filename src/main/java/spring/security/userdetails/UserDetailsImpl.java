package spring.security.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import spring.security.domain.Account;
import spring.security.domain.Authority;

@SuppressWarnings("serial")
public class UserDetailsImpl extends User {
    private String nickname;
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public UserDetailsImpl(Account account) {
        super(account.getUsername(), account.getPassword(), authorities(account));
        this.nickname = account.getNickname(); 
    }

    private static Collection<? extends GrantedAuthority> authorities(Account account) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<Authority> authorities = account.getAuthorities();
        
        for (Authority authority : authorities) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRoleId()));
        }
        return grantedAuthorities;
    }
}