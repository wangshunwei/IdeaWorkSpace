package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    // 编写JPQL查询语句 最新问答   一张problem表
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) order by p.createtime desc ")
    Page<Problem> newlist(String labelid, Pageable pageable);

    // 编写JPQL查询语句 热门问答   一张problem表 里面有reply字段  应该用缓存redis缓存 避免重复点.文章用zset结存存储,自然按照score排序
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) order by p.reply desc ")
    Page<Problem> hotList(String labelid, Pageable pageable);


    // 编写JPQL查询语句 等待回答的记录
    @Query("select p from Problem p where p.id in (select problemid from Pl where labelid = ?1) and p.reply = 0 order by p.createtime desc ")
    Page<Problem> waitList(String labelid, Pageable pageable);


}
