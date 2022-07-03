package at.zykem;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static File getDesktop() {

        String desktopPath = String.valueOf(FileSystemView.getFileSystemView().getHomeDirectory());
        File desktopDir = new File(desktopPath);
        return desktopDir;

    }

    public static void main(String[] args) {

        // Main Menu
        Scanner userInput = new Scanner(System.in);
        System.out.println("CleanerDesktop >> Do you want to clean your Desktop? (Y/N)");
        String input = userInput.next();
        if(input.equalsIgnoreCase("y")) {
            // Cleaning Desktop Logic
            System.out.println("Cleaning desktop...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            File[] desktopFiles = getDesktop().listFiles();
            HashMap<String, String> whitelistedFiles = new HashMap<String, String>();
            HashMap<String, String> files = new HashMap<String, String >();
            System.out.println("Fetched Desktop files. Please enter the file name that you want to keep on desktop, available files:");
            for(File file : desktopFiles) {

                System.out.println(file.getName());
                files.put(file.getName(), file.getName());

            }

            // whitelist system

            while(true) {

                System.out.print("!!! If you want to stop whitelisting please type whitelist_stop !!!\nEnter File to whitelist: ");
                String input2 = userInput.next();

                if(input2.equalsIgnoreCase("whitelist_stop")) {

                    break;

                } else {

                    for(String name : files.values()) {
                        //System.out.println(name);
                        if(input2.toLowerCase().contains(name)){

                            whitelistedFiles.put(input2, input2);
                            System.out.println("Added " + input2 + " to whitelist!");
                            //break whitelisting;
                            // continue whitelisting;
                            continue ;

                        }
                    }

                }


            }

            // deleting system

            System.out.println("CleanerDesktop >> Do you want to delete the Files? (Y/N)");
            String deleteyn = userInput.next();
            if(deleteyn.equalsIgnoreCase("y")) {

                for(File file : desktopFiles) {

                    if(file.getName().equalsIgnoreCase(whitelistedFiles.get(file.getName()))) {

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Skipped " + file.getName() + " because it is on whitelist!");

                    } else {

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Deleted " + file.getName());
                        file.delete();

                    }

                }

            }

        }



    }

}
