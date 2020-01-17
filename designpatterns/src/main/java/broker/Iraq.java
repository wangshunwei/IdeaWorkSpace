package broker;

public class Iraq extends Country{

    public Iraq(UnitedNations mediator) {
        super(mediator);
    }

    // 声明
    public void Declare(String message) {
        mediator.Declare(message,this);
    }

    // 获得消息
    public void GetMessge(String message) {
        System.out.println("获得Iraq的消息");
    }

}
