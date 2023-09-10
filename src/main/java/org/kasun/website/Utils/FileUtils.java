package org.kasun.website.Utils;

import org.bukkit.plugin.Plugin;

import java.io.*;



public class FileUtils {
    private Plugin plugin;

    public void copyFileFromResources(String fileName, File destinationFolder) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
             FileOutputStream outputStream = new FileOutputStream(new File(destinationFolder, fileName))) {

            if (inputStream == null) {
                throw new IOException("Resource not found: " + fileName);
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    public static boolean renameFile(String oldFilePath, String newFileName) {
        File oldFile = new File(oldFilePath);

        if (!oldFile.exists()) {
            System.out.println("Old file does not exist.");
            return false;
        }

        String parentDirectory = oldFile.getParent();
        File newFile = new File(parentDirectory, newFileName);

        if (newFile.exists()) {
            System.out.println("A file with the new name already exists.");
            return false;
        }

        boolean renamed = oldFile.renameTo(newFile);
        return renamed;
    }

}
