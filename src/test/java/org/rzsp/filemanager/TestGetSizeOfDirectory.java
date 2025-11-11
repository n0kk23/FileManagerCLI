package org.rzsp.filemanager;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class TestGetSizeOfDirectory {

    @Test
    void pathToDirectoryIsCorrect() throws IOException {
        File tempFile = Files.createTempDirectory("testdirectory").toFile();
        tempFile.deleteOnExit();

        File result = org.rzsp.filemanager.validators.GetSizeOfDirectoryValidator.validateGetSizeDirectory(tempFile.getAbsolutePath());
        assertNotNull(result);
        assertTrue(result.exists());
        assertEquals(tempFile.getAbsoluteFile(), result.getAbsoluteFile());
    }

    @Test
    void nullStringInValidateGetSizeDirectory() {
        Exception exception = assertThrows
                (IllegalArgumentException.class,
                () -> org.rzsp.filemanager.validators.GetSizeOfDirectoryValidator.validateGetSizeDirectory(null)
                );

        assertEquals("Path to target directory must be not null or blank", exception.getMessage());
    }

    @Test
    void blankStringInValidateGetSizeDirectory() {
        String blankPath = "  ";

        Exception exception = assertThrows
                (IllegalArgumentException.class,
                        () -> org.rzsp.filemanager.validators.GetSizeOfDirectoryValidator.validateGetSizeDirectory(blankPath)
                );

        assertEquals("Path to target directory must be not null or blank", exception.getMessage());
    }

    @Test
    void pathToDirectoryIsNotEXist() {
        String path = "some_txt_patch_to_file";
        File file = new File(path);

        Exception exception = assertThrows
                (IllegalArgumentException.class,
                        () -> org.rzsp.filemanager.validators.GetSizeOfDirectoryValidator.validateGetSizeDirectory(path)
                );

        assertEquals(file.getAbsolutePath() + " is not exist", exception.getMessage());
    }

}
