import java.util.List;

public class CarBuyer implements Runnable {
    private final List<Car> carList;

    public CarBuyer(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void run() {
        synchronized (carList) {
            while (true) {
                buyCar();
                carList.notifyAll();
            }
        }
    }

    private void buyCar() {
        synchronized (carList) {
            try {
                System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
                Thread.sleep(1000);
                if (carList.isEmpty()) {
                    System.out.println("Машин нет");
                    carList.wait(); //после возобновления потока покупатель пытается уехать в строке 32 и выходит ошибка
                } else {
                    carList.remove(0); //тут поток проснулся, а машин нет
                    System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
//ToDo покупатель и производитель вовремя не уведомляют, что они завершили 1 цикл
//после notify() нужно попробовать поставить задержку