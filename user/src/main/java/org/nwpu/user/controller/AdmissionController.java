package org.nwpu.user.controller;

import org.nwpu.user.bean.Response;
import org.nwpu.user.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 录取控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class AdmissionController {

    /**
     * 匹配锁的key值
     */
    public static final String MATCH_YEAR = "match-year";

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 查询某人录取情况
     * @param token
     * @param year
     * @return
     */
    @RequestMapping(value = "/api/user/user-service/getAdmission",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAdmission(@RequestParam("token")String token,@RequestParam(value = "year",required = false)Integer year){
        if(year==null){
            year = Integer.valueOf((String) redisTemplate.opsForValue().get(MATCH_YEAR));
        }
        Integer userId = Integer.valueOf(token.split("-")[1]);
        Response response = new Response<Object>();
        response.setData(admissionService.searchAdmissionByUserId(userId,year));
        return response.toString();
    }
}
