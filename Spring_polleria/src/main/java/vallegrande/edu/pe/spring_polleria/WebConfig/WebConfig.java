package vallegrande.edu.pe.spring_polleria.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las rutas
        
                .allowedOrigins("*") // Permite todos los orígenes (puedes especificar uno si es necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
