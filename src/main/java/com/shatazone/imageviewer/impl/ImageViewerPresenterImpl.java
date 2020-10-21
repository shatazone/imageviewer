package com.shatazone.imageviewer.impl;


import com.shatazone.imageviewer.ImageStorage;
import com.shatazone.imageviewer.ImageViewerPresenter;
import com.shatazone.imageviewer.ImageViewerView;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class ImageViewerPresenterImpl implements ImageViewerPresenter {

    @Inject
    private ImageViewerView viewer;

    @Inject
    private ImageStorage imageStorage;
    
    private byte[] imageData;

    @Override
    public void display(String title, byte[] image) {
        this.imageData = image;
        viewer.loadImage(imageData);
        viewer.setTitle(title);
        viewer.display();
    }

    @Override
    public void exit() {
        viewer.dispose();
    }
    
    @Override
    public void handleSaveImageAs() {
        final File file = viewer.showSaveImageDialog(new String[]{"png"});

        if (file != null) {
            try {
                imageStorage.savePngImage(imageData, file);
            } catch (IOException e) {
                viewer.showCannotSaveFileError();
            }
        }
    }

    @Override
    public void handleShowAboutInfo() {
        viewer.showAboutDialogInfo();
    }

    @Override
    public void handleExit() {
        viewer.dispose();
    }
}