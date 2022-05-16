package kr.co.hlm.system.spring;

import kr.co.hlm.system.access.AccessController;
import kr.co.hlm.system.access.AccessServiceImpl;
import kr.co.hlm.system.access.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessServiceImpl())
                .addPathPatterns("/*")
                .excludePathPatterns("/login")
                .excludePathPatterns("/kickboards/info")
                .excludePathPatterns("/kickboards/location")
                .excludePathPatterns("/helmetstates/wear")
                .excludePathPatterns("/helmetstates/location")
                .excludePathPatterns("/helmets/info");
    }
}
