
package command;

public class Waiter {

    private Command command;

    // 设置订单
    public void setOrder(Command command) {
        this.command = command;
    }
    // 通知执行
    public void notifyCommand() {
        command.ExcuteCommand();
    }

}
