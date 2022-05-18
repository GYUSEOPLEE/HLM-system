package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.helmet.Helmet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HelmetStateServiceImpl implements HelmetStateService{
    private final HelmetStateMapper helmetStateMapper;
    public static Map<String, Character> helmetWear;

    static {
        helmetWear = new HashMap<String, Character>();
    }

    @Override
    public void createHelmetState(HelmetState helmetState) {
        helmetStateMapper.insert(helmetState);
    }

    @Override
    public List<HelmetState> getHelmetStates(HelmetState helmetState) {
       List<HelmetState>  helmetStates = helmetStateMapper.selectAll(helmetState);

       return helmetStates != null
               ? helmetStates
               : new ArrayList<HelmetState>();
    }

    @Override
    public HelmetState getHelmetState(HelmetState helmetState) {
        HelmetState viewHelmetState = helmetStateMapper.select(helmetState);

        return viewHelmetState != null
                ? viewHelmetState
                : new HelmetState();
    }

    @Override
    public void editHelmetState(HelmetState helmetState) {

    }
}
