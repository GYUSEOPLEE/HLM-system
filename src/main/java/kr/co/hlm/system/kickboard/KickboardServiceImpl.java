package kr.co.hlm.system.kickboard;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Kickboard getKickboard(Kickboard kickboard) {
        Kickboard resultKickboard = kickboardMapper.select(kickboard);
        return resultKickboard;
    }

    @Override
    public void editKickboard(Kickboard kickboard) {
        Kickboard resultKickboard = kickboardMapper.select(kickboard);
        if(!resultKickboard.getNo().equals("null")) {
            kickboardMapper.update(kickboard);
        }
    }
}
