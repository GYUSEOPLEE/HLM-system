package kr.co.hlm.system.kickboardlocation;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class KickboardLocation {
    private int no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String kickboardNo;
}
