package org.rzsp.filemanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rzsp.filemanager.functions.CopyingFile;
import org.rzsp.filemanager.functions.GetSizeOfDirectory;

import java.util.Scanner;

/**
 * Класс интерфейс для взаимодействия пользователя с программой
 */
public class FileManagerUI {
    private static final Logger logger = LogManager.getLogger(FileManagerUI.class);

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Запускает меню взаимодействия
     */
    public void start() {
        boolean running = true;

        while (running) {

            System.out.print("""
                    File Manager
                    1) Copying file/directory in target directory
                    2) Get size of directory
                    3) Exit
                    Choice:\s""");

            String userChoice = scanner.nextLine();

            switch (userChoice) {

                case "1" -> {
                    logger.info("User choice: 1");

                    try {
                        System.out.print("Enter path to file/directory that you want to copy: ");
                        String pathToCopyFileOrDirectory = scanner.nextLine();

                        System.out.print("Enter path to target directory: ");
                        String pathToTargetDirectory = scanner.nextLine();


                        CopyingFile copyingFile = new CopyingFile(pathToCopyFileOrDirectory, pathToTargetDirectory);
                        copyingFile.copy();

                        System.out.println("File/directory is copied!");

                    } catch (Exception e) {
                        logger.error("Error copying: {}", e.getMessage());
                    }

                }

                case "2" -> {
                    logger.info("User choice: 2");

                    try {
                        System.out.print("Enter path to directory that you want to enter size: ");
                        String pathToDirectory = scanner.nextLine();

                        System.out.println();

                        GetSizeOfDirectory getSizeOfDirectory = new GetSizeOfDirectory(pathToDirectory);
                        getSizeOfDirectory.printNameAndByteSizeOfAllFilesInDirectory();

                        System.out.println();

                    } catch (Exception e) {
                        logger.error("Error getting size: {}", e.getMessage());
                    }

                }

                case "3" -> {
                    logger.info("User exiting application");

                    running = false;

                }

                default -> {
                    logger.info("User input was wrong");

                    System.out.println("You must write from 1 to 3!");

                }

            }

        }

    }

}
