package kr.kellis.springsecuritydemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                    .antMatchers("/","main", "/accessDenied").permitAll()
                    .anyRequest().access("@authorizationChecker.check(request,authentication)")
                    .and()
            .formLogin()
                    .loginPage("/login")
                    .usernameParameter("userId")
                    .successHandler(new LoginSuccessHandler("/home"))
                    .permitAll()
                    .and()
            .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                    .authenticationProvider(authProvider)
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}
