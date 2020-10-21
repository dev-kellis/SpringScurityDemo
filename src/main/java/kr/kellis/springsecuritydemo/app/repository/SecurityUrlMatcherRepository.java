package kr.kellis.springsecuritydemo.app.repository;

import kr.kellis.springsecuritydemo.app.dto.SecurityUrlMatcher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecurityUrlMatcherRepository extends CrudRepository<SecurityUrlMatcher, String> {
    List<SecurityUrlMatcher> findAll();
    SecurityUrlMatcher findByUrl(String url);
}
