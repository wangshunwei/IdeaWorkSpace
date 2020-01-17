package com.tensquare.qa.impl;

import com.tensquare.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 *
 * 远程调用的熔断器,出现问题进行调用本地这个方法进行容错的处理
 *
 */
@Component
public class LabelClientImpl implements LabelClient{

    @Override
    public Result findById(String id) {
        System.out.println("熔断器生效");
        return new Result(true, StatusCode.OK,"熔断器生效啦");
    }
}
