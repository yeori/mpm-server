package github.yeori.morpheme_server.config;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableAsync
public class AppConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(@NonNull CorsRegistry reg) {
    var methods = Arrays
        .asList(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE,
            HttpMethod.PATCH)
        .stream().map(method -> method.name()).collect(Collectors.toList())
        .toArray(len -> new String[len]);
    reg.addMapping("/api/**").allowedOrigins("http://localhost:8080").allowCredentials(true)
        .allowedMethods(methods);
    log.info("[CORS MAPPING METHOD]" + Arrays.toString(methods));
  }

}
