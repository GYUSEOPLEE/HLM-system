package kr.co.hlm.system.page;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {
    private String id;
    private int StartPageNo;
    private int EndPageNo;
    private int finalPageNo;
    private int totalRowCount;
    private int pageNo;
    private int startRn;
    private int endRn;

}
