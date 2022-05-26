package kr.co.hlm.system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class HlmSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HlmSystemApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpintExporter() {
        return new ServerEndpointExporter();
    }

}
