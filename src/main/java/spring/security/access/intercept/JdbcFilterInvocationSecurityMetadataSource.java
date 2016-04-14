package spring.security.access.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import spring.security.domain.Menu;
import spring.security.domain.Role;
import spring.security.repository.MenuRepository;

public class JdbcFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private final MenuRepository menuRepository;
	private final Map<String, Menu> permissions;
	
	public JdbcFilterInvocationSecurityMetadataSource(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
		permissions = new Hashtable<>();
	}
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		final String httpMethod = request.getMethod().toUpperCase();
		final String url = request.getRequestURI();
		final String key = String.format("%s %s", httpMethod, url);
		
		final Menu menu;
        if(permissions.containsKey(key)) {
        	menu = permissions.get(key);
        } else {
        	System.out.println("##### url: " + url);
        	menu = menuRepository.findByUrl(url);
            if(menu != null) {
                permissions.put(key, menu);
            }
        }
    	
        List<ConfigAttribute> attributes = null;
        if (menu != null) {
            List<Role> roles = menu.getRoles();
            attributes = new ArrayList<ConfigAttribute>(roles.size());
    		for (Role role : roles) {
    			attributes.add(new SecurityConfig(role.getId()));
    		}
        }
		return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	public void clear() {
		permissions.clear();
	}
}