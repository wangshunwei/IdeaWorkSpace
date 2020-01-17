package command;

public abstract class Command {

    protected Barbecuer receiver;

    /**
     * 定义啦无惨的，那么就是使用这个构造器
     * @param receiver
     */
    public Command(Barbecuer receiver) {
        this.receiver = receiver;
    }

    public Barbecuer getReceiver() {
        return receiver;
    }

    public void setReceiver(Barbecuer receiver) {
        this.receiver = receiver;
    }

    // 执行命令
    abstract public void ExcuteCommand();

}
