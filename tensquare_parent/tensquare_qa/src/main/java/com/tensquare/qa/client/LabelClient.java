package com.tensquare.qa.client;
import com.tensquare.qa.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 问答微服务中编写接口,要访问基础微服务中的方法-->远程调用
 *
 */
@FeignClient(value = "tensquare-base",fallback = LabelClientImpl.class)  // 注册微服务的名称 名称不能出现下划线
public interface LabelClient {
    // {lableId}与String id 一样pathvariable中也得写 才不会报错
    @RequestMapping(value = "/lable/{lableId}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "lableId") String id);
}
