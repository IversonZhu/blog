package com.iverson.blog.service;

import com.iverson.blog.entity.Vote;
import sun.net.ftp.FtpDirEntry;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface VoteService {
    Vote getVoteById(Long id);

    void removeVote(Long id);
}
