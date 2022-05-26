package kr.co.hlm.system.helmet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Helmet implements Serializable {
    private String no;
    private String model;
    private String ip;
    private String kickboardIp;
    private char activation;
    private char size;
}
