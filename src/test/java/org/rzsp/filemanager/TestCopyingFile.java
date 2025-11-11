package org.rzsp.filemanager;

import org.junit.jupiter.api.Test;
import org.rzsp.filemanager.functions.CopyingFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


public class TestCopyingFile {

    @Test
    void testCopyingFileConstructorWithSourceAndDestinationDirectoryEquals() throws IOException {
        File tempFile = Files.createTempDirectory("tempDir").toFile();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CopyingFile(tempFile.getAbsolutePath(), tempFile.getAbsolutePath())
        );

        assertEquals("Source directory and destination directory can not be the same", exception.getMessage());
    }

}
