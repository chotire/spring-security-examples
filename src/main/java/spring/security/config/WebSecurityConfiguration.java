package spring.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import spring.security.access.intercept.JdbcFilterInvocationSecurityMetadataSource;
import spring.security.repository.MenuRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //@Autowired
    //private DataSource dataSource;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        //spring schema를 이용한 로그인
        //auth.jdbcAuthentication().dataSource(dataSource);
        
        //test용 in-memory용 인증
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2 config...
        http
            .authorizeRequests()
                .antMatchers("/h2/console/**").permitAll()
                .and()
            .headers().frameOptions().disable();
        
        http
            .csrf().disable() // csrf를 enable하게되면 로그아웃을 반드시 post로 해야함.
            .formLogin()
                .loginPage("/login").permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .addFilterAfter(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
//            .authorizeRequests()
//              .antMatchers("/", "/index").permitAll()
//  //            .antMatchers("/flot", "/morris").hasAnyRole("USER", "ADMIN")
//  //            .antMatchers("/tables").hasAnyRole("ADMIN")
//              .antMatchers("/bower_components/**", "/dist/**", "/js/**", "/favicon.ico").permitAll()
//              .anyRequest().authenticated()
//              .and();

        //default 설정
//        http
//            .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/bower_components/**", "/dist/**").permitAll()
//                .antMatchers("/h2/console/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .csrf().disable() // csrf를 enable하게되면 로그아웃을 반드시 post로 해야함.
//            .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//            .logout().permitAll();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true).ignoring().antMatchers("/", "/index", "/bower_components/**", "/dist/**", "/js/**", "/resources/**", "/static/**", "/favicon.ico");
    }
    
    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        return filterSecurityInterceptor;
    }
    
    
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        AuthenticatedVoter authenticatedVoter = new AuthenticatedVoter();
        RoleVoter roleVoter = new RoleVoter();
        List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<>();
//        voters.add(authenticatedVoter);
        voters.add(roleVoter);
        return new AffirmativeBased(voters);
    }
    
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new JdbcFilterInvocationSecurityMetadataSource(menuRepository);
    }
}