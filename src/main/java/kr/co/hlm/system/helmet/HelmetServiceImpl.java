package kr.co.hlm.system.helmet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelmetServiceImpl implements HelmetService{
    private HelmetMapper helmetMapper;

    @Override
    public void createHelmet(Helmet helmet) {
        helmetMapper.insert(helmet);
    }

    @Override
    public List<Helmet> getHelmets(Helmet helmet) {
        List<Helmet> helmets = helmetMapper.selectAll(helmet);

        return helmets != null
                ? helmets
                : new ArrayList<Helmet>();
    }

    @Override
    public Helmet getHelmet(Helmet helmet) {
        Helmet viewHelmet = helmetMapper.select(helmet);

        return viewHelmet != null
                ? viewHelmet
                : new Helmet();
    }

    @Override
    public void editHelmet(Helmet helmet) {
        helmetMapper.update(helmet);
    }
}
