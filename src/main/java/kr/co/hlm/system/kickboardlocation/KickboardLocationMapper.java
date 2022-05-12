package kr.co.hlm.system.kickboardlocation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KickboardLocationMapper {
    public void insert(KickboardLocation kickboardLocation);
    public KickboardLocation select(KickboardLocation kickboardLocation);
}
