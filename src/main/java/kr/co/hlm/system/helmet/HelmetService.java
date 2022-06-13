package kr.co.hlm.system.helmet;

import java.util.List;

public interface HelmetService {
    void createHelmet(Helmet helmet);
    List<Mark> getMarks(Helmet helmet);
    List<Helmet> getHelmets(Helmet helmet);
    Helmet getHelmet(Helmet helmet);
    void editHelmet(Helmet helmet);
}
