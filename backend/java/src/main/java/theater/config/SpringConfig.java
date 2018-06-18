package theater.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("forward:/admin/index.html");
        registry.addViewController("/admin/").setViewName("forward:/admin/index.html");
        registry.addViewController("/desktop").setViewName("forward:/desktop/index.html");
        registry.addViewController("/desktop/").setViewName("forward:/desktop/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/admin/**", "/desktop/**")
//                .addResourceLocations("/admin/", "/desktop/");
//    }

    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate5Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());

//      configureMessageConverters(converters);
    }

    @Bean
    public Hibernate5Module datatypeHibernateModule() {
        return new Hibernate5Module();
    }
}