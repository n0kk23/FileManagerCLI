package org.rzsp.filemanager.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Класс валидатор пути к копируемому файлу/директории и пути назначения.
 * Имеет статические методы для проверки существования и корректности пути
 */
public class CopyingFileValidator {
    private static final Logger logger = LogManager.getLogger(CopyingFileValidator.class);

    /**
     * Преобразует строковый путь к копируемому файлу/директории в объект {@link File}.
     * Проверяет существование файла/директории и возможность чтения.
     *
     * @param pathToSourceFileOrDirectory путь к файлу или директории
     * @return копируемый файл/директория, прошедший валидацию
     * @throws IllegalArgumentException если путь null, пустой или не существует
     * @throws IOException если файл недоступен для чтения
     */
    public static File validateSourceFileOrDirectoryAndGetFile(String pathToSourceFileOrDirectory) throws IOException {
        logger.debug("Validating source path: {}", pathToSourceFileOrDirectory);

        if (pathToSourceFileOrDirectory == null || pathToSourceFileOrDirectory.isBlank()) {
            throw new IllegalArgumentException("Field \"Source file\" is null or blank");
        }

        File sourceFileOrDirectory = new File(pathToSourceFileOrDirectory);

        if (!sourceFileOrDirectory.exists()) {
            throw new IllegalArgumentException("File \""+ sourceFileOrDirectory + "\" is not found");
        }

        if (!sourceFileOrDirectory.canRead()) {
            throw new IOException("File \"" + sourceFileOrDirectory + "\" is not readable");
        }

        logger.debug("Validation for source path is successful");

        return sourceFileOrDirectory;
    }

    /**
     * Преобразует строковый путь к директории назначения в объект {@link File} и выполняет его валидацию.
     * Проверяет существование пути и является ли объект {@link File} директорией.
     * Создает директорию назначения и, в случае если необходимо, её родителей в случае если директории не существует.
     *
     * @param pathToTargetDirectory путь к директории назначения
     * @return директория назначения, прошедшая валидацию
     * @throws IllegalArgumentException если путь к директории назначения пуст или ведет не к директории
     * @throws IOException если не удается создать структуру директорий назначения
     */
    public static File validateTargetDirectoryAndGetFile(String pathToTargetDirectory) throws IOException {
        logger.debug("Validation destination path: {}", pathToTargetDirectory);

        if (pathToTargetDirectory == null || pathToTargetDirectory.isBlank()) {
            throw new IllegalArgumentException("Field \"Destination\" is null or blank");
        }

        File targetDirectory = new File(pathToTargetDirectory);

        if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
            throw new IllegalArgumentException(targetDirectory + " must be a directory");
        }

        if (!targetDirectory.exists() && !targetDirectory.mkdirs()) { // Создает директорию назначения, а так же создает родительские директории если они не существуют
            throw new IOException("Failed to create parent directories or destination directory");
        }

        logger.debug("Validation for destination path is successful");

        return targetDirectory;
    }

}
