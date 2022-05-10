package kr.co.hlm.system.helmetstate;

import java.util.List;

public interface HelmetStateService {
    public void createHelmetState(HelmetState helmetState);
    public List<HelmetState> getHelmetStates(HelmetState helmetState);
    public HelmetState getHelmetState(HelmetState helmetState);
    public void editHelmetState(HelmetState helmetState);

}
