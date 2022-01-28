package futuzer.threads;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

    // Очередь всех созданных чисел
    private final Queue<Integer> buffer;

    // Максимальное возможное количество чисел в очереди
    private int maxSize = 10;

    // Для генерации времени создания числа
    private final Random generator = new Random();
    private final int right;
    private final int left;

    private final long dayTime;

    public Producer(Queue<Integer> buffer, int leftBorder, int rightBorder, long dayTime) {
        this.buffer = buffer;
        left = leftBorder;
        right = rightBorder;
        this.dayTime = dayTime;
    }

    /**
     * Устанавливает максимальное количество элементов в буфере
     * @param size новое максимальное кол-во
     */
    public void setMaxBufferSize(int size) {
        maxSize = size;
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
            if (buffer.size() < maxSize) {

                // Время потраченное на создание числа
                try {
                    Thread.sleep(getRandomTime());
                } catch (InterruptedException e) {
                    synchronized (System.out) {
                        System.out.println("Producer: Эээ, я спал между прочим");
                    }
                }

                // Созданное число
                int produced = generator.nextInt(1000);
                buffer.add(produced);
                synchronized (System.out) {
                    System.out.println("Producer: Я произвел число " + produced);
                }

            } else {

                // Буфер переполнен, поэтому поток решает отдохнуть
                synchronized (System.out) {
                    System.out.println("Producer: Ой, буфер заполнен. Пойду вздремну)");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        System.out.println("Producer: Эээ, я спал между прочим");
                    }
                }

            }
        }
    }
}
