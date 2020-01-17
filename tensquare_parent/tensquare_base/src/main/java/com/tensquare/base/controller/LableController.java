package com.tensquare.base.controller;

import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lable")
public class LableController {


    @Autowired
    private LableService lableService;
    @Autowired
    RestTemplate restTemplate;



    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Lable> list = lableService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    /**
     * 增加标签
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Lable lable) {
        lableService.save(lable);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    /**
     *
     * 修改
     *
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Lable lable,@PathVariable String id) {

        lable.setId(id);
        lableService.update(lable);
        return new Result(true,StatusCode.OK,"修改成功");

    }




    /**
     * 删除
     *
     */
    @RequestMapping(value = "/{lableId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "lableId") String id) {
        lableService.delete(id);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    /**
     *
     * 通过主键查询
     *
     */

    @RequestMapping(value = "/{lableId}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "lableId") String id) {
        System.out.println("1111");
        return new Result(true,StatusCode.OK,"查询成功",lableService.findById(id));
    }

    /**
     *
     * 条件查询
     *
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map map) {

        List<Lable> list = lableService.findSearch(map);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findPage(@RequestBody Map map,@PathVariable int page,@PathVariable int size) {

        // 调用service分页的方法
        Page<Lable> p = lableService.findPage(map,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Lable>(p.getTotalElements(),p.getContent()));

    }

}
