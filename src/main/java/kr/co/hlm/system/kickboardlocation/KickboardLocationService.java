package kr.co.hlm.system.kickboardlocation;

import org.springframework.stereotype.Service;

@Service
public interface KickboardLocationService {
    public void createKickboardLocation(KickboardLocation kickboardLocation);
    public KickboardLocation getKickboardLocation(KickboardLocation kickboardLocation);

}
