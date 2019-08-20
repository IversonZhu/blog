package com.iverson.blog.service;

import com.iverson.blog.entity.User;
import com.iverson.blog.entity.es.EsBlog;
import com.iverson.blog.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/20
 */
public interface EsBlogService {
    /**
     * 更新EsBlog
     * @param esBlog
     */
    EsBlog updateEsBlog(EsBlog esBlog);

    /**
     * 据ID获取EsBlog
     * @param id
     * @return
     */
    EsBlog getEsBlogById(Long id);

    /**
     * 据ID删除EsBlog
     * @param id
     */
    void removeEsBlog(String id);

    /**
     * 最热Blog列表(分页)
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listHottestEsBlogs(String keyword, Pageable pageable);

    /**
     * 最新Blog列表(分页)
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);

    /**
     * Blog列表(分页)
     * @param pageable
     * @return
     */
    Page<EsBlog> listEsBlogs(Pageable pageable);

    /**
     * 最新前五
     * @return
     */
    List<EsBlog> listTop5NewestEsBlogs();

    /**
     * 最热前五
     * @return
     */
    List<EsBlog> listTop5HottestEsBlogs();

    /**
     * 最热前三十标签
     * @return
     */
    List<TagVO> listTop30Tags();

    /**
     * 最热用户前十二
     * @return
     */
    List<User> listTop12Users();
}
