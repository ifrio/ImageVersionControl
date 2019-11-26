package ImageRepo;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Blob implements Serializable {
    public String imageName;
    public String imageID;
    public byte[] imageSnap;
    public String message;
    public String currTime;

    public Blob(String file){
        try {
            imageSnap = Utils.ImageToByte(file);
        } catch (Exception w) {
            System.out.println("Warning: File not able to convert to byte");
        }
        imageName = file;
        currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        imageID = Utils.MakeID(imageName);
    }

    public String getID(){
        return imageID;
    }
}
