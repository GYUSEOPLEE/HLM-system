package kr.co.hlm.system.kickboardlocation;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class KickboardLocation implements Serializable {
    private int no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String kickboardNo;
}
