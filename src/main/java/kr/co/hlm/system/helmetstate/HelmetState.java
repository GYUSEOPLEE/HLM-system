package kr.co.hlm.system.helmetstate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HelmetState implements Serializable {
    private int no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String helmetNo;
    private char loss;
    private char wear;
}
