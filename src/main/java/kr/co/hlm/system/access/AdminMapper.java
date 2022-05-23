package kr.co.hlm.system.access;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    public Admin select (Admin admin);
}
