package futuzer.threads;

import java.util.Queue;
import java.util.Random;

public class Consumer implements Runnable {

    // Очередь всех созданных чисел
    private final Queue<Integer> buffer;

    // Для генерации времени потребления числа
    private final Random generator = new Random();
    private final int right;
    private final int left;

    private final long dayTime;

    public Consumer(Queue<Integer> buffer, int leftBorder, int rightBorder, long dayTime) {
        this.buffer = buffer;
        right = rightBorder;
        left = leftBorder;
        this.dayTime = dayTime;
    }

    /**
     * Генерация времени, потраченного потоком на создание очередного числа
     * @return Случайное число в заданном диапазоне
     */
    private int getRandomTime() {
        return generator.nextInt(right - left) + left;
    }

    @Override
    public void run() {

        long current = System.currentTimeMillis();

        while (System.currentTimeMillis() - current < dayTime) {
            if (!buffer.isEmpty()) {

                // Время потраченное на поедание числа
                try {
                    Thread.sleep(getRandomTime());
                } catch (InterruptedException e) {
                    synchronized (System.out) {
                        System.out.println("Consumer: Эээ, я спал между прочим");
                    }
                }

                // Съеденное число
                int eaten = buffer.remove();
                synchronized (System.out) {
                    System.out.println("Consumer: Я съел число " + eaten);
                }

            } else {

                // Буфер пустой, поэтому поток решает отдохнуть
                synchronized (System.out) {
                    System.out.println("Consumer: Ой, буфер пуст. Пойду вздремну)");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        System.out.println("Consumer: Эээ, я спал между прочим");
                    }
                }

            }
        }
    }
}
