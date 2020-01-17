package observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    /**
     * 存储观察者
     */
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }


    /**
     * 当气象站得到更新观测值时，我们通知观察者
     */
    public void measurementsChanged() {
        notifyObservers();
    }
    /**
     * 模拟气象站更改了气象数据
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        // 更改气象数据
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        // 通知观察者
        measurementsChanged();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(0);
        if (i >= 0) {
            observers.remove(0);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }


}
