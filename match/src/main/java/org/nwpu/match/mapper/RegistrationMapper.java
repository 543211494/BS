package org.nwpu.match.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.match.bean.Registration;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    /**
     * 查询某一年指定专业正在处理的志愿列表
     * @param year 年份
     * @param mid 专业id
     * @return 查询结果
     */
    public List<Registration> searchRegistrationsByMajor(@Param("year") Integer year,@Param("mid") Integer mid);

    /**
     * 批量更新报名志愿表
     * @param registrations 要更新的数据
     * @return 操作结果
     */
    public boolean updateRegistration(@Param("registrations") List<Registration> registrations);

    /**
     * 查询报名表数量
     * @param year 报名年份
     * @param status 报名表数量
     * @return 符合要求的报名表数量
     */
    public Integer countRegistration(@Param("year")Integer year,@Param("status")Integer status);

    /**
     * 产生指定年份的录取信息
     * @param year 年份
     * @return 操作结果
     */
    public boolean insertAdmission(@Param("year")Integer year);
}
