package com.shatazone.imageviewer.impl;


import com.shatazone.imageviewer.ImageStorage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageStorageImpl implements ImageStorage {
    private static final String IMAGE_PNG = "png";

    @Override
    public void savePngImage(byte[] imageData, File file) throws IOException {
        if (!file.getName().endsWith("." + IMAGE_PNG)) {
            file = new File(file.getAbsolutePath() + "." + IMAGE_PNG);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, IMAGE_PNG, file);
    }
}
