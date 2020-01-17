package command;

public class BakeChickenWingCommand extends Command {

    /**
     * 定义啦无惨的，那么就是使用这个构造器
     *
     * @param receiver
     */
    public BakeChickenWingCommand(Barbecuer receiver) {
        super(receiver);
    }

    @Override
    public void ExcuteCommand() {
        receiver.bakeChickenWing();
    }
}
