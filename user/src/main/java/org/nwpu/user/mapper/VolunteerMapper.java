package org.nwpu.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.user.bean.Volunteer;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    /**
     * 批量插入志愿
     * @param volunteers 志愿列表
     * @param rid 志愿所属报名表id
     * @return
     */
    public boolean insertVolunteer(@Param("volunteers") List<Volunteer> volunteers, @Param("rid")Integer rid);
}
