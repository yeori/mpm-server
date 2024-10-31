package github.yeori.morpheme_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Slf4j
public class MorphemeServerApplication {

	public static void main(String[] args) {
		log.info("[READY]");
		SpringApplication.run(MorphemeServerApplication.class, args);
	}

}
