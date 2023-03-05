package org.nwpu.user.service;

import org.nwpu.user.bean.Admission;

/**
 * 录取服务
 * @author lzy
 */
public interface AdmissionService {

    /**
     * 查询用户录取信息
     * @param userId 用户id
     * @param year 录取年份
     * @return
     */
    public Admission searchAdmissionByUserId(Integer userId,Integer year);
}
