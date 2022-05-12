package kr.co.hlm.system.kickboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Kickboard implements Serializable {
    private String no;
    private String model;
    private String ip;
    private char activation;
}
