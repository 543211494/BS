package org.nwpu.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.user.bean.Registration;

/**
 * 报名表操作mapper
 * @author lzy
 */
@Mapper
public interface RegistrationMapper {
    /**
     * 插入报名表
     * @param registration 报名表实例
     * @param uid 用户id
     * @return
     */
    public boolean insertRegistration(@Param("registration") Registration registration,@Param("uid")Integer uid);

    /**
     * 查询报名表
     * @param uid 用户id
     * @param year 报名年份
     * @return
     */
    public Registration searchRegistrationByUserId(@Param("uid") Integer uid, @Param("year") Integer year);
}
