package CustomFile.Tasks;

import CustomFile.CustomFile;

public class PrintCustomFileTask extends Task implements Runnable {

    public PrintCustomFileTask(CustomFile customFile) {
        this.customFile = customFile;
    }

    @Override
    public void run() {
        synchronized (customFile) {
            System.out.println(customFile.toPrint());
        }
    }
}
