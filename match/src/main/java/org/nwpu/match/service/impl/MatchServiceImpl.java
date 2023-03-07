package org.nwpu.match.service.impl;

import org.nwpu.match.bean.Plan;
import org.nwpu.match.bean.Registration;
import org.nwpu.match.mapper.PlanMapper;
import org.nwpu.match.mapper.RegistrationMapper;
import org.nwpu.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        List<Registration> registrations = registrationMapper.searchRegistrationsByMajor(year, mid);
        registrations.sort(new Comparator<Registration>(){
            @Override
            public int compare(Registration registration1, Registration registration2) {
                if(registration1.getCurrentVolunteer().getFinalGrade().doubleValue()>registration2.getCurrentVolunteer().getFinalGrade().doubleValue()){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("[");
//        for(int i = 0;i<registrations.size();i++){
//            if(i!=0){
//                buffer.append(",");
//            }
//            buffer.append(registrations.get(i).getCurrentVolunteer().getFinalGrade().doubleValue());
//        }
//        buffer.append("]");
//        System.out.println(buffer.toString());
        return registrations;
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
