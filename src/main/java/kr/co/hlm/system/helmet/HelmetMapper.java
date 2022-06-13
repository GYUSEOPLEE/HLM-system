package kr.co.hlm.system.helmet;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelmetMapper {
    void insert(Helmet helmet);
    List<Helmet> selectAll(Helmet helmet);
    Helmet select(Helmet helmet);
    void update(Helmet helmet);
    void updateByIp(Helmet helmet);
}
