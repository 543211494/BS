package org.nwpu.board.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.nwpu.board.bean.Comment;
import org.nwpu.board.bean.Response;
import org.nwpu.board.bean.User;
import org.nwpu.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * 留言板控制类
 * @author lzy
 */
@Controller
@ResponseBody
public class BoardController {

    @Autowired
    private CommentService commentService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 用户留言
     * @param token
     * @param content
     * @return
     */
    @RequestMapping(value = "/api/user/board-service/addComment",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addComment(@RequestParam("token")String token,@RequestParam("content")String content){
        User user = User.toObject((String) redisTemplate.opsForValue().get(token));
        if(user==null){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        Comment comment = new Comment(user.getId(), user.getFullName(),content);
        commentService.insertComment(comment,null);
        this.flushCache();
        Response response = new Response<Object>();
        response.setData(comment);
        return response.toString();
    }

    /**
     * 管理员回复留言
     * @param token
     * @param content
     * @param pid
     * @return
     */
    @RequestMapping(value = "/api/admin/board-service/replyComment",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String replyComment(@RequestParam("token")String token,@RequestParam("content")String content,
                               @RequestParam("pid")Integer pid){
        User user = User.toObject((String) redisTemplate.opsForValue().get(token));
        if(user==null){
            throw new RuntimeException(Response.TOKEN_ERROR);
        }
        Comment comment = new Comment(user.getId(), user.getFullName(),content);
        commentService.insertComment(comment,pid);
        this.flushCache();
        Response response = new Response<Object>();
        response.setData(comment);
        return response.toString();
    }

    /**
     * 管理员删除评论
     * @param bid
     * @return
     */
    @RequestMapping(value = "/api/admin/board-service/deleteComment",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String deleteComment(@RequestParam("bid")Integer bid){
        commentService.deleteComment(bid);
        this.flushCache();
        Response response = new Response<Object>();
        return response.toString();
    }

    /**
     * 按页查看评论
     * @param page 页码
     * @param number 一页的评论数
     * @return
     */
    @RequestMapping(value = "/api/user/board-service/searchComment",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String searchComment(@RequestParam("page")Integer page,@RequestParam("number")Integer number){
        if(page==null||number==null||page.intValue()<=0||number.intValue()<=0){
            throw new RuntimeException(Response.PARAMETER_ERROR);
        }
        String key = "comment-"+page+"-"+number;
        List<Comment> comments =JSON.parseArray((String)redisTemplate.opsForValue().get(key),Comment.class);
        if(comments==null){
            comments = commentService.searchComments(page,number);
            if (comments!=null){
                redisTemplate.opsForValue().set(key,JSON.toJSONString(comments, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty));
            }
        }
        Response response = new Response<Object>();
        response.setData(comments);
        return response.toString();
    }

    /**
     * 清空redis缓存
     */
    public void flushCache(){
        Set<String> keys = redisTemplate.keys("comment-*");
        redisTemplate.delete(keys);
    }
}
