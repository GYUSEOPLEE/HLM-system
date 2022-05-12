package kr.co.hlm.system.kickboardlocation;

import kr.co.hlm.system.kickboard.KickboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KickboardLocationServiceImpl implements KickboardLocationService{
    private final KickboardMapper kickboardMapper;
    @Override
    public void createKickboardLocation(KickboardLocation kickboardLocation) {

    }

    @Override
    public KickboardLocation getKickboardLocation(KickboardLocation kickboardLocation) {
        return null;
    }
}
