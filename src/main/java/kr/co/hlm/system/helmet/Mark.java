package kr.co.hlm.system.helmet;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mark {
    private String no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private char activation;
    private char loss;
    private char wear;
}
