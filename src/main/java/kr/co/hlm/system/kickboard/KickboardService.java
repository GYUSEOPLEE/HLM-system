package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.page.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface KickboardService {
    public void createKickboard(Kickboard kickboard);
    public List<Kickboard> getKickboards(Kickboard kickboard);
    public Kickboard getKickboard(String no);
    public void editKickboard(Kickboard kickboard);
    public List<Kickboard> getKickboardsView(Page page);
}
