package com.shatazone.imageviewer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.shatazone.imageviewer.impl.ImageStorageImpl;
import com.shatazone.imageviewer.impl.ImageViewerPresenterImpl;
import com.shatazone.imageviewer.impl.ImageViewerViewImpl;

public class ScreenshotViewerFactory {
    private static final Injector injector = Guice.createInjector((binder) -> {
        binder.bind(ImageViewerView.class).to(ImageViewerViewImpl.class);
        binder.bind(ImageViewerPresenter.class).to(ImageViewerPresenterImpl.class);
        binder.bind(ImageStorage.class).toInstance(new ImageStorageImpl());
    });

    public static ImageViewer createImageViewer() {
        final ImageViewer imageViewer = new ImageViewer();
        injector.injectMembers(imageViewer);
        return imageViewer;
    }

    public static ImageViewer displayImageViewer(String title, byte[] imageData) {
        final ImageViewer imageViewer = createImageViewer();
        imageViewer.display(title, imageData);
        return imageViewer;
    }
}
