package kr.co.hlm.system.helmetstate;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelmetStateMapper {
    void insert(HelmetState helmetState);
    List<HelmetState> selectAll(HelmetState helmetState);
    HelmetState select(HelmetState helmetState);
}
