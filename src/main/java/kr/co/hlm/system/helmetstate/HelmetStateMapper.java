package kr.co.hlm.system.helmetstate;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelmetStateMapper {
    public void insert(HelmetState helmetState);
    public List<HelmetState> selectAll(HelmetState helmetState);
    public HelmetState select(HelmetState helmetState);
}
