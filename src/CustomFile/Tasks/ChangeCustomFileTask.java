package CustomFile.Tasks;

import CustomFile.CustomFile;
import CustomFile.FileItem;

import java.util.ArrayList;

public class ChangeCustomFileTask extends Task implements Runnable {
    private ArrayList<FileItem> fileItemArrayList;

    public ChangeCustomFileTask(CustomFile customFile, ArrayList<FileItem> fileItemArrayList){
        this.customFile=customFile;
        this.fileItemArrayList=fileItemArrayList;
    }
    @Override
    public void run() {
        synchronized (customFile) {
            customFile.setData(fileItemArrayList);
            System.out.println("ChangeCustomFile");
        }
    }
}
