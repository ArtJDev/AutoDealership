import java.util.List;

public class CarProducer implements Runnable {
    private final List<Car> carList;

    public CarProducer(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void run() {
        synchronized (carList) {
            try {
                createCar();
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
            } catch (InterruptedException ex) {

            }
        }
    }

    private void createCar() throws InterruptedException {
        synchronized (carList) {
            Thread.sleep(1500);
            carList.add(new Car());
            carList.notifyAll();
        }
    }
}