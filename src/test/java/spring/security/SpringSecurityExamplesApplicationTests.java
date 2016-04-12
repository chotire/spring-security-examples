package spring.security;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.security.domain.Account;
import spring.security.domain.Authority;
import spring.security.repository.AccountRepository;
import spring.security.repository.MenuRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SpringSecurityExamplesApplicationTests {
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void contextLoads() {
		Account account = accountRepository.findByUsername("admin");
		List<Authority> authorities = account.getAuthorities();
		System.out.println("ss");
//		Menu menu = menuRepository.findByUrl("/flot");
//		List<Role> roles = menu.getRoles();
//		for (Role role : roles) {
//			String s = "%s, %s";
//			System.out.println(String.format(s, role.getName(), role.getRemark()));
//		}
//		System.out.println(menu);
	}
}