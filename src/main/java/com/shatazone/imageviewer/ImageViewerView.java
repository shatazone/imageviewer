package com.shatazone.imageviewer;

import java.io.File;

public interface ImageViewerView {
    void setTitle(String title);

    void display();

    void dispose();

    void showCannotSaveFileError();
    
    void showAboutDialogInfo();
    
    File showSaveImageDialog(String[] fileExtensions);

    void loadImage(byte[] data);
}