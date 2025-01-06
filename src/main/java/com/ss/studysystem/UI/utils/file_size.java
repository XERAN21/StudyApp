package com.ss.studysystem.UI.utils;

import java.io.File;

public class file_size {
    public static double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }
}
