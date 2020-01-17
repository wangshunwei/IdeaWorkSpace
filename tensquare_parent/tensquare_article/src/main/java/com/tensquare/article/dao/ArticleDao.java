package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 文章点赞功能
     * 编写JPQL的修改语句
     * 修改、删除必须添加 @Modifying
     * UPDATE tb_article SET thumbup = thumbup + 1 WHERE id = 1
     * @param id
     */
    @Modifying
    @Query("update Article set thumbup = thumbup + 1 where id = ?1")
    void thumbup(String id);

}
