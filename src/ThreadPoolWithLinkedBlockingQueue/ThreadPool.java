package ThreadPoolWithLinkedBlockingQueue;


public class ThreadPool {
    private  IBlockingQueue<Runnable> taskQueue;

    private boolean poolShutDownInitiated=false;

    public ThreadPool(int nThreads){
        taskQueue=new LinkedBlockingQueue<Runnable>(nThreads);

        for(int i=1; i<=nThreads;i++){
            ThreadPoolsThread threadPoolsThread=new ThreadPoolsThread(taskQueue,this);
            threadPoolsThread.setName("Thread-"+i);
//            System.out.println("Thread-"+i+"created in ThreadPool");
            threadPoolsThread.start(); //start thread
        }
    }

    public synchronized  void execute(Runnable task) throws Exception{
        if(this.poolShutDownInitiated) throw  new Exception("ThreadPool has been shutDown, no further tasks can be added");
//        System.out.println("task has been added");
        this.taskQueue.put(task);
    }

    public boolean isPoolShutDownInitiated(){
        return poolShutDownInitiated;
    }

    public synchronized  void shutdown(){
        this.poolShutDownInitiated=true;
//        System.out.println("ThreadPool SHUTDOWN initiated.");
    }
}
