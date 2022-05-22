package kr.co.hlm.system.kickboard;

import lombok.RequiredArgsConstructor;
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
        List<Kickboard> kickboards = kickboardMapper.selectAll(kickboard);

        return kickboards != null
                ? kickboards
                : new ArrayList<Kickboard>();
    }

    @Override
    public Kickboard getKickboard(String no) {
        Kickboard resultKickboard = kickboardMapper.select(no);
        return resultKickboard;
    }

    @Override
    public void editKickboard(Kickboard kickboard) {
        Kickboard viewKickboard = kickboardMapper.select(kickboard.getNo());

        if (viewKickboard.getActivation() == 'Y') {
            viewKickboard.setActivation('N');
        } else {
            viewKickboard.setActivation('Y');
        }

        kickboardMapper.update(viewKickboard);
    }
}
