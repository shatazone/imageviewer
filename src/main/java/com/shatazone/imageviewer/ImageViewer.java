package com.shatazone.imageviewer;

import javax.inject.Inject;

public class ImageViewer {
    @Inject
    private ImageViewerPresenter presenter;

    ImageViewer() {
        
    }
    
    public void display(String title, byte[] image) {
        presenter.display(title, image);
    }
    
    public void exit() {
        presenter.exit();
    }
}
