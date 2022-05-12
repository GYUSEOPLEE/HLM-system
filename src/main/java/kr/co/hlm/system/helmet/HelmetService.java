package kr.co.hlm.system.helmet;

import java.util.List;

public interface HelmetService {
    public void createHelmet(Helmet helmet);
    public List<Helmet> getHelmets(Helmet helmet);
    public Helmet getHelmet(Helmet helmet);
    public void editHelmet(Helmet helmet);
}
