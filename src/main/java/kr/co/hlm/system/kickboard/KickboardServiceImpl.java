package kr.co.hlm.system.kickboard;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class KickboardServiceImpl implements KickboardService{

    @Autowired
    KickboardMapper kickboardMapper;

    @Override
    public void createKickboard(Kickboard kickboard) {

    }

    @Override
    public List<Kickboard> getKickboards(Kickboard kickboard) {
        return null;
    }

    @Override
    public Kickboard getKickboard(Kickboard kickboard) {
        return null;
    }

    @Override
    public void editKickboard(Kickboard kickboard) {

    }
}
