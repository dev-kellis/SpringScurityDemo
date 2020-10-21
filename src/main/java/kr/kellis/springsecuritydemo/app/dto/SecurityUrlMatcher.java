package kr.kellis.springsecuritydemo.app.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SecurityUrlMatcher")
@Data
public class SecurityUrlMatcher {
    private String url;
    private String authority;
}
