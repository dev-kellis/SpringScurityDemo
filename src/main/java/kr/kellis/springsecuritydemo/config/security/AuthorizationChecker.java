package kr.kellis.springsecuritydemo.config.security;

import kr.kellis.springsecuritydemo.app.dto.SecurityUrlMatcher;
import kr.kellis.springsecuritydemo.app.dto.User;
import kr.kellis.springsecuritydemo.app.repository.SecurityUrlMatcherRepository;
import kr.kellis.springsecuritydemo.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class AuthorizationChecker {
    @Autowired
    private SecurityUrlMatcherRepository securityUrlMatcherRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean check(HttpServletRequest request, Authentication authentication){
        Object principalObj = authentication.getPrincipal();

        if(!(principalObj instanceof User)){
            return false;
        }

        String authority = null;
        for(SecurityUrlMatcher matcher : securityUrlMatcherRepository.findAll()){
            if(new AntPathMatcher().match(matcher.getUrl(), request.getRequestURI())){
                authority = matcher.getAuthority();
                break;
            }
        }

        String userId = ((User)authentication.getPrincipal()).getUserId();
        User loggedUser = userRepository.findByUserId(userId);

        List<String> authorities = loggedUser.getAuthority();

        return authority != null && authorities.contains(authority);
    }
}
