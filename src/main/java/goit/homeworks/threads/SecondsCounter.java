package goit.homeworks.threads;

public class SecondsCounter {

    public static void main(String[] args) {
        // TASK 1
        // Створення потоків для повідомлення кожну секунду і кожні 5 секунд
        secondsCounter(5L);
    }

    public static void secondsCounter(long secondToWatch) {
        System.out.println("Second counter буде слідкувати за кожними " + secondToWatch + " секундами");

        Thread oneSecTimeThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                long time = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println(time + " сек");
                try {
                    Thread.sleep(1000); // Засипання на 1 секунду
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        });

        Thread customSecTimeThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(secondToWatch * 1000); // Засипання на 5 секунд
                    System.out.println("Минуло " + secondToWatch + " секунд");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });

        oneSecTimeThread.start();
        customSecTimeThread.start();
    }

}
