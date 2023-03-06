package org.nwpu.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.board.bean.Comment;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 插入一条新留言
     * @param comment 评论内容
     * @param pid 父评论id
     * @return
     */
    public boolean insertComment(@Param("comment") Comment comment,@Param("pid") Integer pid);

    /**
     * 删除留言
     * @param bid 要删除留言的id
     * @return
     */
    public boolean deleteComment(@Param("bid") Integer bid);

    /**
     * 按页查询评论
     * @param start 其实下标(不包含)
     * @param number 一页的评论数
     * @return 查询结果
     */
    public List<Comment> searchComments(@Param("start") Integer start, @Param("number") Integer number);
}
