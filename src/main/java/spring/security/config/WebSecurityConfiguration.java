package spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
            .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/flot", "/morris").hasAnyRole("USER", "ADMIN")
                .antMatchers("/tables").hasAnyRole("ADMIN")
                .antMatchers("/bower_components/**", "/dist/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable() // csrf를 enable하게되면 로그아웃을 반드시 post로 해야함.
            .formLogin()
                .loginPage("/login").permitAll()
                .and()
            .logout().permitAll();

        
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
}