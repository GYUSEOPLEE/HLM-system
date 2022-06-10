package kr.co.hlm.system.helmet;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelmetMapper {
    public void insert(Helmet helmet);
    public List<Helmet> selectAll(Helmet helmet);
    public Helmet select(Helmet helmet);
    public void update(Helmet helmet);
    public void updateByIp(Helmet helmet);
}
