package com.iverson.blog.service.impl;

import com.iverson.blog.dao.VoteDao;
import com.iverson.blog.entity.Vote;
import com.iverson.blog.service.VoteService;
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
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao voteDao;

    @Override
    public Vote getVoteById(Long id) {
        return voteDao.findOne(id);
    }

    @Override
    public void removeVote(Long id) {
        voteDao.delete(id);
    }
}
