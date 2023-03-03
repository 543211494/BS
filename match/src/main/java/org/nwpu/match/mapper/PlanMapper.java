package org.nwpu.match.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.match.bean.Plan;

import java.util.List;

@Mapper
public interface PlanMapper {

    /**
     * 查询某一年的全部招生计划
     * @return
     */
    public List<Plan> searchAllPlans(@Param("year")Integer year);
}
