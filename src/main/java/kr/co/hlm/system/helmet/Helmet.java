package kr.co.hlm.system.helmet;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Helmet {
    private String no;
    private String model;
    private String ip;
    private String kickboardIp;
    private char activation;
    private char size;
}
