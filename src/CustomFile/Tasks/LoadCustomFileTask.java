package CustomFile.Tasks;

import CustomFile.CustomFile;

public class LoadCustomFileTask extends Task implements Runnable{

    public LoadCustomFileTask(CustomFile customFile){
        this.customFile=customFile;
    };

    @Override
    public void run() {
        synchronized (customFile) {
            customFile.load();
            System.out.println("Load customFile");
        }
    }
}
