package kr.co.hlm.system.kickboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KickboardMapper {
    public void insert(Kickboard kickboard);
    public List<Kickboard> selectAll(Kickboard kickboard);
    public Kickboard select(Kickboard kickboard);
    public void update(Kickboard kickboard);
}
