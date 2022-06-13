package kr.co.hlm.system.helmetstate;

import java.util.List;

public interface HelmetStateService {
    void createHelmetState(HelmetState helmetState);
    List<HelmetState> getHelmetStates(HelmetState helmetState);
//    HelmetState getHelmetState(HelmetState helmetState);
    void editHelmetState(HelmetState helmetState);
}
