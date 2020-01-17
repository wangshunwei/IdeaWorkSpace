package com.tensquare_user.controller;
import com.tensquare_user.pojo.User;
import com.tensquare_user.service.UserService;
import com.tensquare_user.util.RandomCode;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
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
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RandomCode randomCode;

	/**
	 * 给消息队列发送消息
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/sendsms/{mobile}",method= RequestMethod.POST)
	public Result sendsms(@PathVariable String mobile){
		// 先生成手机验证码
		String code = randomCode.genCode();
		// 在控制台输出验证码
		System.out.println("生成的手机验证码："+code);

		// 给消息队列发送消息，使用rabbitMQ，导入jar包
		Map<String,String> map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("code",code);

		// 直接模式 分裂模式 主题模式
		// rabbitTemplate.convertAndSend("","smsqueue",map);

		// 把验证码保存到redis缓存中，导入jar包
		redisTemplate.opsForValue().set("ee48_"+mobile,code,5, TimeUnit.MINUTES);

		return new Result(true,StatusCode.OK,"发送成功");
	}

	/**
	 * 用户注册
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/register/{code}",method= RequestMethod.POST)
	public Result register(@RequestBody User user,@PathVariable String code){
		// 调用service，用户注册逻辑
		userService.regist(user,code);
		return new Result(true,StatusCode.OK,"注册成功");
	}

	/**
	 * 登录功能
	 * @return
	 */
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public Result login(@RequestBody User user){
		User u = userService.login(user);
		if(u == null){
			// 说明，不正确
			return new Result(true,StatusCode.ERROR,"用户名或者密码错误");
		}
		return new Result(true,StatusCode.OK,"登录成功");
	}



}
