package ImageRepo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Set;

public class TrackRecord implements Serializable {
    private static String CURR_DIR = Command.CURR_DIR;
    public static final String DIR = "/Repo/Blob/";
    private LinkedHashMap<Integer,Blob> Record;

    public TrackRecord(String[] filePath){
        new File(CURR_DIR + "/Repo").mkdirs();
        new File(CURR_DIR  + "/Repo/Blob").mkdirs();

        Blob blobby = new Blob(filePath[1], "First add");

        Record = new LinkedHashMap<>();
        Record.put(blobby.getID(),blobby);

        Utils.serialize(CURR_DIR  + DIR + blobby.getID()+".blob",blobby);
    }

    public void add(String[] args){
        String temp = new String();
        for (int i = 2; i < args.length; i++){
            temp = temp + args[i] + " ";
        }

        Blob blobby = new Blob(args[1], temp);
        Record.put(blobby.getID(),blobby);
        Utils.serialize(CURR_DIR  + DIR + blobby.getID()+".blob",blobby);
        System.out.println(args[1]+" was added to repo!");
    }
    public void pull(String[] args){
        Blob reInstate = Record.get(Integer.parseInt(args[1]));
        try {
            Utils.ByteToImage(CURR_DIR +"\\" + reInstate.imageName, reInstate.imageSnap);
            System.out.println(args[1]+" was successfully reinstated!");
        } catch(Exception w){
            System.out.println("File does not exist");
        }
    }
    public void log(){
        Set<Integer> keys = Record.keySet();
        System.out.println("Date:      Time:    ID:        Message:    ");
        for(Integer key:keys){
            Blob index = Record.get(key);
            System.out.println(index.currTime+" "+index.imageID+" '"+index.message+"' ");
        }
    }
}
