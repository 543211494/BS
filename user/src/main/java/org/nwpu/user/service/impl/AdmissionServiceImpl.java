package org.nwpu.user.service.impl;

import org.nwpu.user.bean.Admission;
import org.nwpu.user.mapper.AdmissionMapper;
import org.nwpu.user.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 录取服务接口
 * @author lzy
 */
@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private AdmissionMapper admissionMapper;

    @Override
    public Admission searchAdmissionByUserId(Integer userId, Integer year) {
        return admissionMapper.searchAdmissionByUserId(userId, year);
    }
}
