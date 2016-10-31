package ThreadPoolWithLinkedBlockingQueue;

public class ThreadPoolsThread extends Thread {
    private IBlockingQueue<Runnable> taskQueue;

    private ThreadPool threadPool;

    public ThreadPoolsThread(IBlockingQueue<Runnable> queue, ThreadPool threadPool){
        taskQueue=queue;
        this.threadPool=threadPool;
    }

    public void run(){
        try {
            while (true){
//                System.out.println(Thread.currentThread().getName()+" is READY to execute task.");
                Runnable runnable = taskQueue.take();
//                System.out.println(Thread.currentThread().getName()+" has taken task.");
                runnable.run();
//                System.out.println(Thread.currentThread().getName()+" has EXECUTED task.");
                if(this.threadPool.isPoolShutDownInitiated()
                        &&  this.taskQueue.size()==0){
                    this.interrupt();
                    Thread.sleep(1);
                }
            }
        } catch (Exception e){
            System.out.println(Thread.currentThread().getName()+" has been STOPPED.");
        }
    }
}
