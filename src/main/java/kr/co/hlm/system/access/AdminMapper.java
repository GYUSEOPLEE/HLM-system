package kr.co.hlm.system.access;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public int select (Admin admin);
}
