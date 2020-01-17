package entity;

/**
 *
 * 每一个controller方法的返回值
 *
 */

public class Result {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;




    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {

        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    // 服务与服务之间的远程调用,调用方需要加无参构造
    public Result() {

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
