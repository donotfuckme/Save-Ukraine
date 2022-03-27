package global.saveukraine.volunteer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("index");
    registry.addViewController("/").setViewName("index");
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    String[] resourceHandlers = new String[]{
      "/img/**",
      "/css/**",
      "/fnt/**",
      "/js/**"
    };

    String[] resourceLocations = new String[]{
      "classpath:/static/vendor/bootstrap/",
      "classpath:/static/img/",
      "classpath:/static/css/",
      "classpath:/static/fnt/",
      "classpath:/static/js/"
    };

    registry.addResourceHandler(resourceHandlers)
      .addResourceLocations(resourceLocations);
  }
}
