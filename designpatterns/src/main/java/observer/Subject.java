package observer;


public interface Subject {

    // 添加观察者
    void registerObserver(Observer observer);

    // 删除观察者
    void removeObserver(Observer observer);

    // 通知
    void notifyObservers();

}
