package org.nwpu.match.service.impl;

import org.nwpu.match.bean.Plan;
import org.nwpu.match.bean.Registration;
import org.nwpu.match.mapper.PlanMapper;
import org.nwpu.match.mapper.RegistrationMapper;
import org.nwpu.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public List<Plan> searchPlans(Integer year) {
        return planMapper.searchAllPlans(year);
    }

    @Override
    public List<Registration> searchRegistrationsByMajor(Integer year, Integer mid) {
        return registrationMapper.searchRegistrationsByMajor(year, mid);
    }

    @Override
    public boolean updateRegistration(List<Registration> registrations) {
        if(registrations==null||registrations.isEmpty()){
            return true;
        }else{
            return registrationMapper.updateRegistration(registrations);
        }
    }

    /**
     * 判断有无待处理的报名表
     *
     * @param year 报名年份
     * @return 判断结果
     */
    @Override
    public boolean hasUnprocessedRegistration(Integer year) {
        return registrationMapper.countRegistration(year,Registration.UNACCEPTED)!=0;
    }

    @Override
    public boolean insertAdmission(Integer year) {
        return registrationMapper.insertAdmission(year);
    }
}
