import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();

        new Thread(new CarBuyer(carList), "Покупатель 1").start();
        new Thread(new CarBuyer(carList), "Покупатель 2").start();
        new Thread(new CarBuyer(carList), "Покупатель 3").start();

        for (int i = 0; i < 5; i++) {
            new Thread(new CarProducer(carList), "Производитель BMW").start();
        }
    }
}
