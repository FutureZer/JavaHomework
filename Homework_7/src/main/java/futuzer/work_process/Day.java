package futuzer.work_process;

import futuzer.threads.Consumer;
import futuzer.threads.Producer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс олицетворяет день, т.е. связывает производителя и потребителя
 * Процесс, который длится определенное время. В начале запускает производителя и
 * потребителя, а в конце останавливает два потока.
 */
public class Day {

    // Длительность дня
    private long workingTime = 20000;

    // Буфер из задачи
    private final Queue<Integer> buffer;

    // Границы по умолчанию для генерации времени, потраченного на производство или потребление
    private int consumerLeft = 2500;
    private int producerLeft = 2500;
    private int consumerRight = 5000;
    private int producerRight = 5000;

    /**
     * Конструктор дня (без указания границ по времени)
     * @param buffer буфер, в который производитель кладет, а потребитель забирает
     */
    public Day(ConcurrentLinkedQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void setProducerBorder(int left, int right) {
        producerLeft = left;
        producerRight = right;
    }

    public void setConsumerBorder(int left, int right) {
        consumerLeft = left;
        consumerRight = right;
    }

    public void setWorkingTime(long newTime) {
        workingTime = newTime;
    }

    /**
     * Конструктор дня (c указанием границ по времени)
     * @param buffer буфер, в который производитель кладет, а потребитель забирает
     * @param leftC левая граница для случайной генерации времени, затраченного на производство
     * @param leftP левая граница для случайной генерации времени, затраченного на потребление
     * @param rightC правая граница для случайной генерации времени, затраченного на производство
     * @param rightP правая граница для случайной генерации времени, затраченного на потребление
     */
    public Day(ConcurrentLinkedQueue<Integer> buffer, int leftC, int leftP, int rightC, int rightP) {
        this.buffer = buffer;
        consumerLeft = leftC;
        producerLeft = leftP;
        consumerRight = rightC;
        producerRight = rightP;
    }

    /**
     * Запускает день (работу Producer и Consumer)
     */
    public void startDay() {
        Thread consumer = new Thread(new Consumer(buffer, consumerLeft, consumerRight, workingTime));
        Thread producer = new Thread(new Producer(buffer, producerLeft, producerRight, workingTime));

        Thread day = new Thread(() -> {

            // Запускаем потоки Producer и Consumer
            producer.start();
            consumer.start();

            // Ждем пока они закончат работать
            try {
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        // Сам запуск потока дня
        day.start();
        try {
            day.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
