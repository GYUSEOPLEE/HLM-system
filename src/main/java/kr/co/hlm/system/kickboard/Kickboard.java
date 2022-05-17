package kr.co.hlm.system.kickboard;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Kickboard {
    private String no;
    private String model;
    private String ip;
    private String activation;
}
