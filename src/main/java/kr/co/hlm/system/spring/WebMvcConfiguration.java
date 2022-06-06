package kr.co.hlm.system.spring;

import kr.co.hlm.system.access.AccessController;
import kr.co.hlm.system.access.AccessServiceImpl;
import kr.co.hlm.system.access.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final AccessServiceImpl accessService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessService)
                .addPathPatterns("/*")
                .addPathPatterns("/helmets/*")
                .addPathPatterns("/helmetstates/*")
                .excludePathPatterns("/login")
                .excludePathPatterns("/kickboards/info")
                .excludePathPatterns("/kickboardslocations")
                .excludePathPatterns("/helmetstates/wear")
                .excludePathPatterns("/helmetstates/location")
                .excludePathPatterns("/helmets/info");
    }
}
