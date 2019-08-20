package com.iverson.blog.service;

import com.iverson.blog.entity.Comment;
import sun.net.ftp.FtpDirEntry;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface CommentService {

    /**
     * 据ID获取Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * 据ID删除评论
     * @param id
     */
    void removeComment(Long id);
}
