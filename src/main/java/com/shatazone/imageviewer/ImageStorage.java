package com.shatazone.imageviewer;

import java.io.File;
import java.io.IOException;

public interface ImageStorage {
    void savePngImage(byte[] imageData, File file) throws IOException;
}
