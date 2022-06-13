package kr.co.hlm.system.kickboard;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KickboardMapper {
    void insert(Kickboard kickboard);
    List<Kickboard> selectAll(Kickboard kickboard);
    Kickboard select(Kickboard kickboard);
    void update(Kickboard kickboard);
}
