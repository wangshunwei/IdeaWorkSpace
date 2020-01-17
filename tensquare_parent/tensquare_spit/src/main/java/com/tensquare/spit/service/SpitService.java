package com.tensquare.spit.service;

        import com.tensquare.spit.dao.SpitDao;
        import com.tensquare.spit.pojo.Spit;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.mongodb.core.MongoTemplate;
        import org.springframework.data.mongodb.core.query.Criteria;
        import org.springframework.data.mongodb.core.query.Query;
        import org.springframework.data.mongodb.core.query.Update;
        import org.springframework.stereotype.Service;
        import utils.IdWorker;

        import java.util.Date;
        import java.util.List;

@Service
public class SpitService {


    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     *查询全部记录
     * @return
     */
    public List<Spit> findAll() {
        return  spitDao.findAll();
    }

    /**
     *
     *增加  修改为发布吐槽
     * @param spit
     */
    public  void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setUserid("1");//当前登陆人的ID (后期我们会对此处进行修改)
        spit.setNickname("");//当前登陆人的昵称 (后期我们会对此处进行修改)
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            // 如果存在上级ID,评论
            Spit parentSpit =
                    spitDao.findById(spit.getParentid()).get();//被评论的吐槽
            parentSpit.setComment(parentSpit.getComment()+1);// 评论数+1
            spitDao.save(parentSpit);
        }
        spitDao.save(spit);
    }

    /**
     *
     * 根据主键查询实体
     * @param id
     * @return
     */
    public Spit findById(String id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        spitDao.deleteById(id);
    }

    /**
     * 根据上级ID查询吐槽列表
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageRequest);
    }

    /**
     * 点赞
     * @param id
     */
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }


}
