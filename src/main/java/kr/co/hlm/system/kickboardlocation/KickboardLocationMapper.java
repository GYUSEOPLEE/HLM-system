package kr.co.hlm.system.kickboardlocation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KickboardLocationMapper {
    public void insert(KickboardLocation kickboardLocation);
    public KickboardLocation select(KickboardLocation kickboardLocation);
}
