package org.nwpu.user.service.impl;

import org.nwpu.user.bean.Major;
import org.nwpu.user.bean.Registration;
import org.nwpu.user.bean.Volunteer;
import org.nwpu.user.mapper.MajorMapper;
import org.nwpu.user.mapper.RegistrationMapper;
import org.nwpu.user.mapper.VolunteerMapper;
import org.nwpu.user.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报名服务类
 * @author lzy
 */
@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public List<Major> searchMajorsById(Integer[] ids) {
        return majorMapper.searchMajorsById(ids);
    }

    @Override
    public Registration insertRegistration(Registration registration,Integer uid) {
        registrationMapper.insertRegistration(registration,uid);
        volunteerMapper.insertVolunteer(registration.getVolunteers(),registration.getId());
        return registration;
    }

    @Override
    public Registration searchRegistrationByUserId(Integer uid, Integer year) {
        return registrationMapper.searchRegistrationByUserId(uid, year);
    }

    @Override
    public List<Major> searchAllMajors() {
        return majorMapper.searchAllMajors();
    }

    @Override
    public List<Major> searchAllMajors(Integer year) {
        return majorMapper.searchAllMajorsByYear(year);
    }
}
