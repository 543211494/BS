package org.nwpu.board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.board.bean.Response;
import org.nwpu.board.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作拦截器，对需要一定权限才能执行的操作进行拦截
 * @author lzy
 */
@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String url = request.getRequestURI();
        String token = request.getParameter("token");
        if(token==null||token.isEmpty()){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        User user = null;
        /* 防止错误token引发异常 */
        try{
            user = User.toObject((String) redisTemplate.opsForValue().get(token));
        }catch(Exception e){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        /* 判断token是否有效 */
        if(user==null){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        /* 鉴权 */
        if(url.startsWith("/api/teacher/")){
            if(!user.getPower().equals(User.TEACHER)){
                throw new RuntimeException(Response.NO_POWER_ERROR);
            }
        }else if (url.startsWith("/api/admin/")){
            if(!user.getPower().equals(User.ADMIN)&&!user.getPower().equals(User.SUPER)){
                throw new RuntimeException(Response.NO_POWER_ERROR);
            }
        }else if(url.startsWith("/api/super/")){
            if(!user.getPower().equals(User.SUPER)){
                throw new RuntimeException(Response.NO_POWER_ERROR);
            }
        }
        return true;
    }
}
