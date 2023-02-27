package org.nwpu.user.service;

import org.nwpu.user.bean.Major;
import org.nwpu.user.bean.Registration;

import java.util.List;

/**
 * 报名服务接口
 * @author lzy
 */
public interface SignupService {

    /**
     * 根据id查询专业
     * @param ids 专业id数组
     * @return
     */
    public List<Major> searchMajorsById(Integer[] ids);

    /**
     * 提交一份报名表
     * @param registration 报名表实例
     * @param uid 用户id
     * @return
     */
    public Registration insertRegistration(Registration registration,Integer uid);

    /**
     * 根据用户id和报名年份查询报名表
     * @param uid 用户id
     * @param year 报名年份
     * @return
     */
    public Registration searchRegistrationByUserId(Integer uid,Integer year);

    /**
     * 查询全部专业
     * @return 查询结果
     */
    public List<Major> searchAllMajors();
}
