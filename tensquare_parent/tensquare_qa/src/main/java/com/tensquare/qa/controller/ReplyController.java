package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.service.ReplyService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	private HashMap<Integer,Integer> map = new HashMap<>();


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",replyService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",replyService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Reply> pageList = replyService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Reply>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",replyService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param reply
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Reply reply  ){
		replyService.add(reply);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param reply
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Reply reply, @PathVariable String id ){
		reply.setId(id);
		replyService.update(reply);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		replyService.deleteById(id);
		// 啦啦啦啦
		return new Result(true,StatusCode.OK,"删除成功");
	}
	/**
	 *
	 * 专门测试监测工具的接口，模拟处理线上问题 排查线上问题的过程
	 * hashMap死循环的的例子
	 *
	 *
	 */
	@RequestMapping(value="/hashMapTest")
	public void HashMapTest(){
//		Thread t1 = new Thread() {
//			public void run() {
//				for (int i = 0; i < 50000; i++) {
//					map.put(new Integer(i), Integer.valueOf(i));
//				}
//			}
//		};
//		Thread t2 = new Thread() {
//			public void run() {
//				for (int i = 0; i < 50000; i++) {
//					map.put(new Integer(i),Integer.valueOf(i));
//				}
//			}
//		};
		// 呦呦
//		t1.start();
//		t2.start();
		//我加啦注释
		// 我爱java
        while (true) {
         int a = 10;
        // 测试git
			// 第二次测试

		//第二次测试

			String c = "abcdefg";

		String d = "1111111";
            String abc = "git测试";
            System.out.println("gbgcb");
            System.out.println("gbgcb");
            System.out.println("gbgcb");
            // 明天又上班啦！！！！！

            // 高深吗？
		// 嘟嘟
			// dudu
        }
	}
}
