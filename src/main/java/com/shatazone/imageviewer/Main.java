package com.shatazone.imageviewer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        byte[] imageData = Files.readAllBytes(Paths.get(Main.class.getClassLoader().getResource("Mickey_Mouse.png").toURI()));
        ScreenshotViewerFactory.displayImageViewer("Mickey Mouse", imageData);
    }
}
