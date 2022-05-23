package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.helmet.Helmet;
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
    public Kickboard getKickboard(Kickboard kickboard) {
        Kickboard viewKickboard = kickboardMapper.select(kickboard);

        return viewKickboard != null
                ? viewKickboard
                : new Kickboard();
    }

    @Override
    public void editKickboard(Kickboard kickboard) {
        Kickboard viewKickboard = kickboardMapper.select(kickboard);

        if (viewKickboard.getActivation() == 'Y') {
            viewKickboard.setActivation('N');
        } else {
            viewKickboard.setActivation('Y');
        }

        kickboardMapper.update(viewKickboard);
    }
}
