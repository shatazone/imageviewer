package com.shatazone.imageviewer;

public interface ImageViewerPresenter {
    void display(String title, byte[] image);

    void exit();
    
    void handleSaveImageAs();
    
    void handleShowAboutInfo();

    void handleExit();
}