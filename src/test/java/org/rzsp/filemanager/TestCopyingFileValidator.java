package org.rzsp.filemanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class TestCopyingFileValidator {

    /// TEST validateSourceFileAndGetFile ///
    /// ********************************* ///
    /// ********************************* ///

    @Test
    void pathToSourceFileOrDirectoryIsCorrect() throws IOException {
        File tempFile = File.createTempFile("text", ".txt");
        tempFile.deleteOnExit();

        File result = org.rzsp.filemanager.validators.CopyingFileValidator.validateSourceFileOrDirectoryAndGetFile(tempFile.getAbsolutePath());
        assertNotNull(result);
        assertTrue(result.exists());
        assertEquals(tempFile.getAbsoluteFile(), result.getAbsoluteFile());
    }

    @Test
    void pathToSourceFileOrDirectoryIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateSourceFileOrDirectoryAndGetFile(null)
        );

        assertEquals("Field \"Source file\" is null or blank", exception.getMessage());
    }

    @Test
    void pathToSourceFileOrDirectoryIsBlank() {
        String blankPath = "";

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateSourceFileOrDirectoryAndGetFile(blankPath)
        );

        assertEquals("Field \"Source file\" is null or blank", exception.getMessage());
    }

    @Test
    void pathToSourceFileOrDirectoryIsSpaces() {
        String spacePath = "    ";

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateSourceFileOrDirectoryAndGetFile(spacePath)
        );

        assertEquals("Field \"Source file\" is null or blank", exception.getMessage());
    }

    @Test
    void sourceFileIsNotExist() throws IOException {
        String pathToNotExistFile = "no_exist_file_txt";

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateSourceFileOrDirectoryAndGetFile(pathToNotExistFile)
        );

        assertEquals("File \""+ pathToNotExistFile + "\" is not found", exception.getMessage());
    }

    /// TEST validateDestinationAndGetFile ///
    /// ********************************* ///
    /// ********************************* ///

    @Test
    void pathToDestinationDirectoryIsCorrect() throws IOException {
        File tempFile = Files.createTempDirectory("dir").toFile();
        tempFile.deleteOnExit();

        File result = org.rzsp.filemanager.validators.CopyingFileValidator.validateTargetDirectoryAndGetFile(tempFile.getAbsolutePath());
        assertNotNull(result);
        assertTrue(result.exists());
        assertEquals(tempFile.getAbsoluteFile(), result.getAbsoluteFile());
    }

    @Test
    void pathToDestinationDirectoryIsNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateTargetDirectoryAndGetFile(null)
        );

        assertEquals("Field \"Destination\" is null or blank", exception.getMessage());
    }

    @Test
    void pathToDestinationDirectoryIsBlank() {
        String blankPath = "";

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateTargetDirectoryAndGetFile(blankPath)
        );

        assertEquals("Field \"Destination\" is null or blank", exception.getMessage());
    }

    @Test
    void pathToDestinationDirectoryIsSpaces() {
        String spacesPath = "   ";

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.CopyingFileValidator.validateTargetDirectoryAndGetFile(spacesPath)
        );

        assertEquals("Field \"Destination\" is null or blank", exception.getMessage());
    }

}
