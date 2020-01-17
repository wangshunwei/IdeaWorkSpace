package observer;

public class Test {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        NowConditionDisplay nowConditionDisplay = new NowConditionDisplay(weatherData);

        weatherData.setMeasurements(20, 10, 29.2f);
    }

}
