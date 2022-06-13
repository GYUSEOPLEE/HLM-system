package kr.co.hlm.system.kickboardlocation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KickboardLocationServiceImpl implements KickboardLocationService{
    private final KickboardLocationMapper kickboardLocationMapper;

    @Override
    public void createKickboardLocation(KickboardLocation kickboardLocation) {
        kickboardLocationMapper.insert(kickboardLocation);
    }

    @Override
    public KickboardLocation getKickboardLocation(KickboardLocation kickboardLocation) {
        return kickboardLocationMapper.select(kickboardLocation);
    }
}
