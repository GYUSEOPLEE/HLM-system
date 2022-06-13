package kr.co.hlm.system.helmet;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class Helmet {
    @NotBlank private String no;
    @NotBlank private String model;
    @NotBlank private String ip;
    @NotBlank private String kickboardIp;
    private char size;
    private char activation;
}
