package org.rzsp.filemanager.validators;

import java.io.File;

/**
 * Класс валидатор пути к директории, размер которой хотим вывести.
 * Имеет статический метод для валидации строкового пути.
 */
public class GetSizeOfDirectoryValidator {

    /**
     * Преобразует строковый путь в объект {@link File}.
     * Проверяет корректность строкового пути, существование объекта по заданному пути и является ли он директорией.
     *
     * @param pathToTargetDirectory путь к директории, размер которой хотим узнать
     * @return директория, прошедшая валидацию
     */
    public static File validateGetSizeDirectory(String pathToTargetDirectory) {
        if (pathToTargetDirectory == null || pathToTargetDirectory.isBlank()) {
            throw new IllegalArgumentException("Path to target directory must be not null or blank");
        }

        File directory = new File(pathToTargetDirectory);

        if (!directory.exists()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not exist");
        }

        if (directory.exists() && !directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " must be a directory");
        }

        return directory;

    }

}
