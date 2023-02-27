package org.nwpu.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.user.bean.Major;

import java.util.List;

/**
 * 专业操作mapper
 * @author lzy
 */
@Mapper
public interface MajorMapper {

    /**
     * 根据id查询专业
     * @param ids id数组
     * @return 查询结果
     */
    public List<Major> searchMajorsById(@Param("ids") Integer[] ids);

    /**
     * 查询全部专业
     * @return 查询结果
     */
    public List<Major> searchAllMajors();
}
