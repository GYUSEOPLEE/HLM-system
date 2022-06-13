package kr.co.hlm.system.helmetstate;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HelmetState {
    private int no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String helmetNo;
    private char loss;
    private char wear;
}
