package kr.co.hlm.system.kickboard;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Kickboard {
    @NotBlank private String no;
    @NotBlank private String model;
    @NotBlank private String ip;
    private char activation;
}
