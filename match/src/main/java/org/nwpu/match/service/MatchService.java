package org.nwpu.match.service;

import org.nwpu.match.bean.Plan;
import org.nwpu.match.bean.Registration;

import java.util.List;

public interface MatchService {

    /**
     * 查询某一年的全部招生计划
     * @param year
     * @return
     */
    public List<Plan> searchPlans(Integer year);

    /**
     * 查询某一年指定专业正在处理的志愿列表
     * @param year 年份
     * @param mid 专业id
     * @return 查询结果
     */
    public List<Registration> searchRegistrationsByMajor(Integer year,Integer mid);

    /**
     * 批量更新报名志愿表
     * @param registrations 要更新的数据
     * @return 操作结果
     */
    public boolean updateRegistration(List<Registration> registrations);

    /**
     * 判断有无待处理的报名表
     * @param year 报名年份
     * @return 判断结果
     */
    public boolean hasUnprocessedRegistration(Integer year);

    /**
     * 产生指定年份的录取信息
     * @param year 年份
     * @return 操作结果
     */
    public boolean insertAdmission(Integer year);
}
