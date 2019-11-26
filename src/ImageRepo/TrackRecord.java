package ImageRepo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Set;

public class TrackRecord implements Serializable {
    private static String CURR_DIR = Command.CURR_DIR;
    public static final String DIR = "/Repo/Blob/";
    private String mostRecent;
    private LinkedHashMap<String,Blob> Record;

    public TrackRecord(String filePath){
        new File(CURR_DIR + "/Repo").mkdirs();
        new File(CURR_DIR  + "/Repo/Blob").mkdirs();

        String parentID = null;
        Blob blobby = new Blob(filePath);

        mostRecent = blobby.getID();
        Record = new LinkedHashMap<>();
        Record.put(blobby.getID(),blobby);

        Utils.serialize(CURR_DIR  + DIR + blobby.getID(),blobby);
    }

    public void add(String[] args){
        Blob blobby = new Blob(args[1]);
        mostRecent = blobby.getID();
        Record.put(blobby.getID(),blobby);
        Utils.serialize(CURR_DIR  + DIR + blobby.getID(),blobby);
        System.out.println(args[1]+"was added to repo");
    }
    public void pull(String[] args){
        Blob reInstate = Record.get(args[1]);
        try {
            Utils.ByteToImage(CURR_DIR, reInstate.imageSnap);
        } catch(Exception w){
            System.out.println("File does not exist");
        }
    }
    public void log(){
        Set<String> keys = Record.keySet();
        for(String key:keys){
            System.out.println(key);
        }
    }
}
