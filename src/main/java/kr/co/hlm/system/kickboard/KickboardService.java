package kr.co.hlm.system.kickboard;

import java.util.List;

public interface KickboardService {
    void createKickboard(Kickboard kickboard);
    List<Kickboard> getKickboards(Kickboard kickboard);
    Kickboard getKickboard(Kickboard kickboard);
    void editKickboard(Kickboard kickboard);
}
