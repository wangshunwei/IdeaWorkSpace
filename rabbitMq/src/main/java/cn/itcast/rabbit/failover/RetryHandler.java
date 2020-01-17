package cn.itcast.rabbit.failover;

public interface RetryHandler<V extends RetryResult> {

    V doHandler(V result) throws Exception;
    V doFailed(V result) throws  Exception;
}
