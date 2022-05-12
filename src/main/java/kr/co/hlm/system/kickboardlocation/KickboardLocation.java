package kr.co.hlm.system.kickboardlocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KickboardLocation implements Serializable {
    private int no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String kickboardNo;
}
