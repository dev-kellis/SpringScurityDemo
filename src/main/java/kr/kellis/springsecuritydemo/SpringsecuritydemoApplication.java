package kr.kellis.springsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringsecuritydemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritydemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringsecuritydemoApplication.class);
    }

    //	@Bean
//	CommandLineRunner init(MongoOperations operations) {
//		return args -> {
////			operations.dropCollection(User.class);
//
//			User commonUser = new User();
//			commonUser.setUserId("sujin");
//			commonUser.setPassword("1234");
//			commonUser.setName("leesujin");
//			commonUser.setAuthority("USER");
//			commonUser.setEnabled(true);
//
//			User adminUser = new User();
//			adminUser.setUserId("admin");
//			adminUser.setPassword("1234");
//			adminUser.setName("admin");
//			adminUser.setAuthority("ADMIN");
//			adminUser.setEnabled(true);
//
//			operations.insert(commonUser);
//			operations.insert(adminUser);
//
//			operations.findAll(User.class).forEach(user -> {
//				System.out.println(user.toString());
//			});
//		};
//	}
}
