package ru.aberezhnoy.dataStructures;

public class Test {

    private static class Queue {
        private int maxSize; // СЂР°Р·РјРµСЂ
        private int[] queue; // РјРµСЃС‚Рѕ С…СЂР°РЅРµРЅРёСЏ
        private int head;    // РѕС‚СЃСЋРґР° СѓС…РѕРґСЏС‚
        private int tail;    // СЃСЋРґР° РїСЂРёС…РѕРґСЏС‚
        private int items;   // С‚РµРєСѓС‰РµРµ РєРѕР»РёС‡РµСЃС‚РІРѕ

        public Queue(int s) {
            maxSize = s;
            queue = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        public void insert(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(queue, 0, tmpArr, 0, queue.length);
                } else {
                    System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(queue, head, tmpArr,
                            maxSize - (queue.length - head), queue.length - head);
                    head = maxSize - head - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            queue[++tail] = i;
            ++items;
        }

        public int remove() {
            int temp =  queue[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int peek(){
            return queue[head];
        }
    }

    public static void main(String[] args) {

        Queue queue = new Queue(10);

        queue.insert(0);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.insert(7);
        queue.insert(8);
        queue.insert(9);

        while( !queue.isEmpty() ) {
            int n = queue.remove();
            System.out.println(n);
        }
    }
}
