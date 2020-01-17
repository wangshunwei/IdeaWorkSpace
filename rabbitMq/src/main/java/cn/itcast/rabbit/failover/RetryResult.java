package cn.itcast.rabbit.failover;

public interface RetryResult {

    boolean isSuccess();

    void setSuccess(boolean success);

}
