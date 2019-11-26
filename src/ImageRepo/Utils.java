package ImageRepo;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Utils {
    public static byte[] ImageToByte(String PATH) throws Exception{
        BufferedImage bImage = ImageIO.read(new File(PATH));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        return bos.toByteArray();
    }

    public static void ByteToImage(String PATH, byte[] bites) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(bites);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File(PATH) );
        System.out.println("image created");
    }

    public static String MakeID(String name){
        return name;
    }

    public static Object deserialize(String PATH){
        Object toReturn;
        File inFile = new File (PATH);
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(inFile));
            toReturn = inp.readObject();
            inp.close();
        } catch (IOException e) {
            toReturn = null;
        } catch (ClassNotFoundException e) {
            toReturn = null;
        }
        return toReturn;
    }

    public static void serialize(String PATH, Object record){
        File outFile = new File(PATH);
        try {
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(record);
            out.close();
        } catch (IOException e) {
            throw new Error("help.");
        } catch (NullPointerException e) {
            throw new Error("NullPointerException.");
        }
    }
}
