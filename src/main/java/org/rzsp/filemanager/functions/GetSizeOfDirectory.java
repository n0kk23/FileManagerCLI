package org.rzsp.filemanager.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;

import static org.rzsp.filemanager.validators.GetSizeOfDirectoryValidator.*;

/**
 * Класс выполняющий функцию вывода размера всех файлов в выбранной директории.
 */
public class GetSizeOfDirectory {
    private static final Logger logger = LogManager.getLogger(GetSizeOfDirectory.class);

    private final File targetDirectory;

    /**
     * Конструктор класса GetSizeOfDirectory.
     * Валидирует строковый путь к директории, получая на выходе объект {@link File}.
     *
     * @param pathToDirectory строковый путь к директории, размер файлов которой хотим вывести
     */
    public GetSizeOfDirectory(String pathToDirectory) {
        logger.debug("Initializing get directory constructor");

        this.targetDirectory = validateGetSizeDirectory(pathToDirectory);

        logger.debug("Get directory constructor is successfully initializing");
    }

    /**
     * Выводит на экран все названия файлов/директорией и их размеры, которые находятся в выбранной директории.
     * Работает с полем targetDirectory. Создает массив объекта {@link File} из всех файлов и директорией, которые находятся в {@link File} targetDirectory.
     * через цикл ForEach работает с массивом.
     * Если объект {@link File} является файлом, то выводит его название и размер в байтах.
     * Если объект {@link File} является директорией, то выводит его название и размер в байтах, полученный через вызов {@link #getDirectorySize(File)}.
     */
    public void printNameAndByteSizeOfAllFilesInDirectory() {
        logger.debug("Starting getting size of all files in directory");

        File[] filesAndDirectoryInDirectory = targetDirectory.listFiles();
        if (filesAndDirectoryInDirectory == null) {
            throw new IllegalArgumentException("Cannot read directory");
        }

        for (File fileOrDirectory : filesAndDirectoryInDirectory) {
            if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.getName() + ": " + fileOrDirectory.length() + " bytes");
            } else if (fileOrDirectory.isDirectory()) {
                System.out.println(fileOrDirectory.getName() + ": " + getDirectorySize(fileOrDirectory) + " bytes");
            } else {
                System.out.println(fileOrDirectory.getName() + " is not readable");
            }
        }

        logger.debug("Getting size of all files in directory is ended successfully");
    }

    /**
     * Метод, возвращающий размер переданной директории.
     * Создает массив объекта {@link File} из всех файлов и директорией, которые находятся в заданной директории.
     * Для возвращения размера используется потоковый интерфейс, который суммирует размеры всех файлов и рекурсивно вычисляет размер поддиректорией.
     *
     * @param directory директория, размер которой хотим вернуть
     * @return размер директории
     */
    private Long getDirectorySize(File directory) {
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return 0L;
        }

        return Arrays.stream(files)
                .mapToLong(file -> {
                    if (file.isDirectory()) {
                        return getDirectorySize(file);
                    } else if (file.isFile()) {
                        return file.length();
                    } else {
                        return 0;
                    }
                })
                .sum();
    }

}
