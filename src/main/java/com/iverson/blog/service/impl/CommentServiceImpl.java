package com.iverson.blog.service.impl;

import com.iverson.blog.dao.CommentDao;
import com.iverson.blog.entity.Comment;
import com.iverson.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.findOne(id);
    }

    @Override
    public void removeComment(Long id) {
        commentDao.delete(id);
    }
}
