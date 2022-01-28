package futuzer.utils;

import java.util.Queue;
import java.util.Random;

public class QueueUtils {

    public static void printAllElements(Queue<Integer> queue) {
        System.out.print("Элементы в очереди: ");
        for (Integer el : queue) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    public static void addThreeElemsIfEmpty(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            Random rand = new Random();
            queue.add(rand.nextInt(1000));
            queue.add(rand.nextInt(1000));
            queue.add(rand.nextInt(1000));
            System.out.println("Элементы успешно добавлены");
        } else {
            System.out.println("Очередь не пуста. Элементы могут добавляться только в пустую очередь");
        }
    }
}
