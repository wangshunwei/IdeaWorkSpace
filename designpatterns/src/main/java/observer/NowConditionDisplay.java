package observer;

public class NowConditionDisplay implements Observer {

    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public NowConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("[push] 当前温度:" + temperature + " 当前湿度: " + humidity);
    }

}
