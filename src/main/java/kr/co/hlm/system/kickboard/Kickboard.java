package kr.co.hlm.system.kickboard;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Kickboard {
    private String no;
    private String model;
    private String ip;
    private char activation;
}
