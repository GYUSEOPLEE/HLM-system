package kr.co.hlm.system.kickboard;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Kickboard implements Serializable {
    private String no;
    private String model;
    private String ip;
    private char activation;
}
