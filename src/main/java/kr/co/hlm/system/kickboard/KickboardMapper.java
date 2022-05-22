package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KickboardMapper {
    public void insert(Kickboard kickboard);
    public List<Kickboard> selectAll(Kickboard kickboard);
    public Kickboard select(String no);
    public void update(Kickboard kickboard);
}
