package ThreadPoolWithLinkedBlockingQueue;


import java.util.LinkedList;
import java.util.List;

public class LinkedBlockingQueue<E> implements IBlockingQueue<E> {
    private List<E> queue;
    private int maxSize;

    public LinkedBlockingQueue(int maxSize){
        this.maxSize=maxSize;
        queue=new LinkedList<E>();
    }

    @Override
    public synchronized void put(E item) throws InterruptedException {
        if(queue.size()==maxSize){
            this.wait();
        }
        queue.add(item);
        this.notifyAll();
    }

    @Override
    public synchronized E take() throws InterruptedException {
        if (queue.size() == 0) {
            this.wait();
        }
        this.notifyAll();

        return queue.remove(0);
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }
}
