package broker;

public class USA extends Country {

    public USA(UnitedNations mediator) {
        super(mediator);
    }

    // 声明
    public void Declare(String message) {
        mediator.Declare(message,this);
    }

    // 获得消息
    public void GetMessge(String message) {
        System.out.println("获得USA的消息");
    }
}
