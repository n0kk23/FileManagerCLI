package org.rzsp.filemanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Главный метод для выполнения функции копирования файла/директории и вывода размера директории, куда копируется файл/директория.
     *
     * @param args:
     *            args[0] - путь к копируемому файлу/директории;
     *            args[1] - путь директории, куда копируется файл или файлы директории.
     */
    public static void main(String[] args) {
        logger.info("Application is started");

        try {
            FileManagerUI fileManager = new FileManagerUI();
            fileManager.start();
        } catch (Exception e) {
            logger.error("Application error: {}", e.getMessage());
        }

        logger.info("Application is ended");
    }

}