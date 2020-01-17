package cn.itcast.rabbit.listener;

import cn.itcast.rabbit.failover.RetryHandler;
import cn.itcast.rabbit.failover.RetryResult;

public class ReceiverRetryHandler implements RetryHandler{
    @Override
    public RetryResult doHandler(RetryResult result) throws Exception {
        return null;
    }

    @Override
    public RetryResult doFailed(RetryResult result) throws Exception {
        return null;
    }

    // 重复做之前的业务
}
