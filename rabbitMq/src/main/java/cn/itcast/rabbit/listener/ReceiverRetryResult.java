package cn.itcast.rabbit.listener;

import cn.itcast.rabbit.failover.RetryResult;

public class ReceiverRetryResult implements RetryResult{
    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public void setSuccess(boolean success) {

    }
}
