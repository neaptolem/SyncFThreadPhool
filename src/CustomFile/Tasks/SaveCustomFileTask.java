package CustomFile.Tasks;


import CustomFile.CustomFile;

public class SaveCustomFileTask extends Task implements Runnable{

    public SaveCustomFileTask(CustomFile customFile){
        this.customFile=customFile;
    }
    @Override
    public void run() {
        synchronized (customFile){
            customFile.save();
        }
        System.out.println("saveCustomFileTask");
    }
}
