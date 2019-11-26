package ImageRepo;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Blob implements Serializable {
    public String imageName;
    public int imageID;
    public byte[] imageSnap;
    public String message;
    public String currTime;

    public Blob(String file, String m){
        try {
            imageSnap = Utils.ImageToByte(file);
        } catch (Exception w) {
            System.out.println("Warning: File not able to convert to byte");
        }
        imageName = file;
        message = m;
        currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        imageID = Utils.MakeID(currTime);
    }

    public int getID(){
        return imageID;
    }
}
