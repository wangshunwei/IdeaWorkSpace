package com.tensquare.base.service;
import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

    /**
     * 业务层
     *
     */

    @Service
    @Transactional
    public class LableService {


        @Autowired
        private LableDao lableDao;

        @Autowired
        private IdWorker idWorker;

        public List<Lable> findAll() {

            //System.out.println("11111");
            return lableDao.findAll();

        }

        public Lable findById(String id) {

            return lableDao.findById(id).get();

        }


        public void save(Lable lable) {

            // 设置id
            lable.setId(idWorker.nextId() + "");
            lableDao.save(lable);
        }

        /**
         *
         * @param lable
         */

        public void update(Lable lable) {
            lableDao.save(lable);
        }

        /**
         *
         * @param id
         */

        public void delete(String id) {

            lableDao.deleteById(id);

        }

        /**
         * 条件查询
         * @param map
         * @return
         */
        public List<Lable> findSearch(Map map) {
            // 创建条件对象
            Specification<Lable> spec = this.creatSpec(map);
            List<Lable> list = lableDao.findAll(spec);
            return list;

        }

        /**
         * 拼接查询条件
         * @param map
         * @return
         */
        private Specification<Lable> creatSpec(Map map) {
            Specification<Lable> spec = new Specification<Lable>() {
                /**
                 * 生成查询条件
                 * @param root
                 * @param criteriaQuery
                 * @param criteriaBuilder
                 * @return
                 */
                @Override
                public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                    // 定义list集合 封装查询条件
                    List<Predicate> list = new ArrayList<>();

                    // criteriaBuilder.equal(root.get("labelname").as(String.class),"haha");
                    String labelname = (String) map.get("labelname");
                    // System.out.println(labelname);
                    // 判断条件
                    if (labelname != null && !"".equals(labelname)) {
                        // 拼接查询条件
                        Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + labelname + "%");
                        list.add(predicate);
                    }
                    // 可以拼接其他的查询条件
                    String state = (String) map.get("state");
                    if (state != null && !"".equals(state)) {
                        Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), state);
                        list.add(predicate);
                    }
                    return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                }
            };
            return spec;
        }

        public Page<Lable> findPage(Map map, int page, int size) {

            Specification<Lable> spec = this.creatSpec(map);
            // 分页参数的设置
            PageRequest pageRequest = PageRequest.of(page - 1, size);
            // 调用dao方法进行分页的查询
            return lableDao.findAll(spec,pageRequest);

        }
    }
