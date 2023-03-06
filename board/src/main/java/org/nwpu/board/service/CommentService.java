package org.nwpu.board.service;

import org.nwpu.board.bean.Comment;

import java.util.List;

/**
 * 留言服务
 * @author lzy
 */
public interface CommentService {

    /**
     * 插入一条新留言
     * @param comment 评论内容
     * @param pid 父评论id
     * @return
     */
    public boolean insertComment(Comment comment,Integer pid);

    /**
     * 删除留言
     * @param bid 要删除留言的id
     * @return
     */
    public boolean deleteComment(Integer bid);

    /**
     * 按页查询评论
     * @param page 评论页码
     * @param number 一页评论数
     * @return 查询结果
     */
    public List<Comment> searchComments(Integer page,Integer number);
}
