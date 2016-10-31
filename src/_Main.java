import CustomFile.*;
import CustomFile.Tasks.ChangeCustomFileTask;
import CustomFile.Tasks.LoadCustomFileTask;
import CustomFile.Tasks.PrintCustomFileTask;
import CustomFile.Tasks.SaveCustomFileTask;
import ThreadPoolWithLinkedBlockingQueue.ThreadPool;

import java.util.ArrayList;
import java.util.Date;

public class _Main {

    static public void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(1); //create 2 threads in ThreadPool
        CustomFile customFile = new CustomFile();
        ArrayList<FileItem> fileItemArrayList = generateCustomFile("Andrian");
        Runnable loadCustomFileTask = new LoadCustomFileTask(customFile);
        Runnable printCustomFileTask = new PrintCustomFileTask(customFile);
        Runnable changeCustomFileTask = new ChangeCustomFileTask(customFile, fileItemArrayList);
        Runnable saveCustomFileTask = new SaveCustomFileTask(customFile);
        threadPool.execute(loadCustomFileTask);
        threadPool.execute(printCustomFileTask);
        boolean test[] = new boolean[20];

        for (int i = 0; i < 20; i++) {
            threadPool.execute(loadCustomFileTask);
            fileItemArrayList = generateCustomFile("Test" + i);
            changeCustomFileTask = new ChangeCustomFileTask(customFile, fileItemArrayList);
            threadPool.execute(changeCustomFileTask);
            threadPool.execute(printCustomFileTask);
            threadPool.execute(saveCustomFileTask);
            test[i] = customFile.eq(fileItemArrayList);
            Thread.sleep(1000);
        }


        Thread.sleep(2000);
        int t = 0;
        int f = 0;
        for (int i = 0; i < 20; i++) {
            if (test[i]) {
                t++;
            } else {
                f++;
            }
        }
        System.out.println("true:" + t + "\nfalse:" + f);
    }

    public static ArrayList<FileItem> generateCustomFile(String s) {
        ArrayList<FileItem> fileItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                fileItems.add(new FileItem(s + " " + i + " " + j, new Date()));
            }
        }
        return fileItems;
    }
}
