package ImageRepo;

import java.nio.file.Paths;
import java.io.File;

public class Command {
    public static String CURR_DIR = Paths.get(".").toAbsolutePath().normalize().toString();
    private static String PATH = CURR_DIR + "/Repo/happy.txt";
    private static TrackRecord curr_trackRecord;

    public static void run(String[] args){

        String key = args[0];
        int length = args.length;

        if (key.equals( "init")){
            init(args[1]);
            return;
        }

        curr_trackRecord = (TrackRecord)Utils.deserialize(PATH);

        switch (key) {
            case "add":
                if (length >= 2) {
                    curr_trackRecord.add(args);
                } else {
                    System.out.println("Please add the Image Name");
                }
                break;
            case "pull":
                if (length >= 2) {
                    curr_trackRecord.pull(args);
                } else {
                    System.out.println("Please add the Image Name or pull id (See log)");
                }
                break;
            case "log":
                curr_trackRecord.log();
                break;
            default:
                System.out.println(key + " is not a command");
        }
        Utils.serialize(PATH, curr_trackRecord);
    }

    public  static void init(String filePATH){
        File imageRepo = new File(CURR_DIR + "/Repo");
        if (imageRepo.exists()) {
            System.out.println("A Image Repo already exists in the current directory.");
        } else {
            curr_trackRecord = new TrackRecord(filePATH);
            Utils.serialize(PATH, curr_trackRecord);
            System.out.println("Initialization was successful!");
        }
    }
}