package goit.homeworks.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private final int n;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private Integer current = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void fizz() throws InterruptedException {
        if (current % 3 == 0 && current % 5 != 0) {
            queue.put("fizz");
        }
    }

    private void buzz() throws InterruptedException {
        if (current % 5 == 0 && current % 3 != 0) {
            queue.put("buzz");
        }
    }

    private void fizzbuzz() throws InterruptedException {
        if (current % 15 == 0) {
            queue.put("fizzbuzz");
        }
    }

    private void number() throws InterruptedException {
        if (current % 3 != 0 && current % 5 != 0) {
            queue.put(Integer.toString(current));
        }
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Створення потоків для обробки чисел
        for (int i = 1; i <= n; i++) {
            executor.execute(() -> {
                try {
                    synchronized (this) {
                        if (current <= n) {
                            fizz();
                            buzz();
                            fizzbuzz();
                            number();
                            current++;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Потік для виведення результатів
        executor.execute(() -> {
            try {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    result.append(queue.take()).append(", ");
                }
                System.out.println(result.length() > 0 ? result.substring(0, result.length() - 2) : result.toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.shutdown();
    }
}
