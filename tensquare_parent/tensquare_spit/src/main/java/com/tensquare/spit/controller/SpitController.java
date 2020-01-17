package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {


    @Autowired
    private SpitService spitService;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询全部数据
     * @return
     */
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findOne(@PathVariable String id) {
        return  new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    /**
     * 增加
     * @param spit
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     *  删除
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        spitService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
    @RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId,@PathVariable int page,
                                 @PathVariable int size) {
        Page<Spit> pageList = spitService.findByParentid(parentId,page,
                size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 点赞存到Mongo中
     * @param id
     */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
    public void updateThumbup(@PathVariable  String id) {
        spitService.updateThumbup(id);
        return; new Result(true,StatusCode.OK,"点赞成功");

    }






}
