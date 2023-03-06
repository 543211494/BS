package org.nwpu.board.service.impl;

import org.nwpu.board.bean.Comment;
import org.nwpu.board.mapper.BoardMapper;
import org.nwpu.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean insertComment(Comment comment,Integer pid) {
        return boardMapper.insertComment(comment,pid);
    }

    @Override
    public boolean deleteComment(Integer bid) {
        return boardMapper.deleteComment(bid);
    }

    @Override
    public List<Comment> searchComments(Integer page, Integer number) {
        return boardMapper.searchComments((page.intValue()-1)*number,number);
    }
}
