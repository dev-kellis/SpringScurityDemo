package kr.kellis.springsecuritydemo.app.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2816260204291910731L;

    private String userId;
    private String password;
    private List<String> authority;
    private boolean enabled;
    private String name;

}
