package kr.kellis.springsecuritydemo.config.security;

import kr.kellis.springsecuritydemo.app.dto.User;
import kr.kellis.springsecuritydemo.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new UsernameNotFoundException(userId);
        }

        if(!matchPassword(password, user.getPassword()) || !user.isEnabled()){
            throw new BadCredentialsException(userId);
        }

        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        for(String authority : user.getAuthority()){
            auth.add(new SimpleGrantedAuthority(authority));
        }
        return new UsernamePasswordAuthenticationToken(user, password, auth);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    private boolean matchPassword(String loginPwd, String password) {
        return loginPwd.equals(password);
    }
}
