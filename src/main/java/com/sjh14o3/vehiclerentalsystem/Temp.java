package com.sjh14o3.vehiclerentalsystem;

import java.io.File;

public class Temp {
    private static String[] getAllImagesPath(String folderPath) {
        System.out.println("path is: " + folderPath);
        String[] paths = null;
        File folder = new File(folderPath);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get the list of files and directories in the folder
            File[] files = folder.listFiles();
            if (files != null && files.length > 0) {
                paths = new String[files.length];
                System.out.println("Files in the folder:");
                for (int i = 0; i < files.length; i++) {
                    paths[i] = files[i].getName();
                    System.out.println(paths[i]);
                }
            } else {
                System.out.println("The folder is empty.");
            }
        } else {
            System.out.println("The specified path is not a valid folder.");
        }
        return paths;
    }

    public static void main(String[] args) {
        String path = "src/main/resources/images";
        String[] paths = getAllImagesPath(path);
        System.out.println(paths.length);
    }
}
