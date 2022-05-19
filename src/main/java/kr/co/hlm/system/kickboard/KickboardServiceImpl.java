package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KickboardServiceImpl implements KickboardService{
    private final KickboardMapper kickboardMapper;

    @Override
    public void createKickboard(Kickboard kickboard) {
        kickboardMapper.insert(kickboard);
    }

    @Override
    public List<Kickboard> getKickboards(Kickboard kickboard) {
        return kickboardMapper.selectAll(kickboard);
    }

    @Override
    public Kickboard getKickboard(String no) {
        Kickboard resultKickboard = kickboardMapper.select(no);
        return resultKickboard;
    }

    @Override
    public void editKickboard(Kickboard kickboard) {
        Kickboard resultKickboard = kickboardMapper.select(kickboard.getNo());
        if(!resultKickboard.getNo().equals(null)) {
            if(resultKickboard.getActivation() == 'Y'){
                resultKickboard.setActivation('N');
            } else if(resultKickboard.getActivation() == 'N'){
                resultKickboard.setActivation('Y');
            }
            kickboardMapper.update(resultKickboard);
        }
    }

    @Override
    public List<Kickboard> getKickboardsView(Page page) {
        List<Kickboard> kickboardList = kickboardMapper.selectAllCount(page);
        return kickboardList != null ?
                kickboardList :
                new ArrayList<Kickboard>();
    }


}
