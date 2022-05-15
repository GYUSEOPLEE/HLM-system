package kr.co.hlm.system.kickboard;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Kickboard implements Serializable {
    private String no;
    private String model;
    private String ip;
    private char activation;
}
