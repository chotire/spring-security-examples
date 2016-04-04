package spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("pages/index");
        registry.addViewController("/index").setViewName("pages/index");
        registry.addViewController("/login").setViewName("pages/login");
        registry.addViewController("/blank").setViewName("pages/blank");
        registry.addViewController("/buttons").setViewName("pages/buttons");
        registry.addViewController("/flot").setViewName("pages/flot");
        registry.addViewController("/forms").setViewName("pages/forms");
        registry.addViewController("/grid").setViewName("pages/grid");
        registry.addViewController("/icons").setViewName("pages/icons");
        registry.addViewController("/morris").setViewName("pages/morris");
        registry.addViewController("/notifications").setViewName("pages/notifications");
        registry.addViewController("/panels-wells").setViewName("pages/panels-wells");
        registry.addViewController("/tables").setViewName("pages/tables");
        registry.addViewController("/typography").setViewName("pages/typography");
    }
}