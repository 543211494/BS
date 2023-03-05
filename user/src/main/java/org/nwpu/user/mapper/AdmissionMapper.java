package org.nwpu.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.user.bean.Admission;

@Mapper
public interface AdmissionMapper {

    /**
     * 查询用户录取信息
     * @param userId 用户id
     * @param year 录取年份
     * @return
     */
    public Admission searchAdmissionByUserId(@Param("userId") Integer userId,@Param("year") Integer year);
}
