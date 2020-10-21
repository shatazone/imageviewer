package com.shatazone.imageviewer.impl;

import com.shatazone.imageviewer.ImageViewerPresenter;
import com.shatazone.imageviewer.ImageViewerView;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ResourceBundle;

public class ImageViewerViewImpl implements ImageViewerView {
    private final ResourceBundle messages = ResourceBundle.getBundle("strings");
    private final JFrame frame;
    private final JLabel imageLabel;
    private final JLabel statusLabel;

    @Inject
    private ImageViewerPresenter presenter;

    public ImageViewerViewImpl() {
        this.frame = new JFrame();
        this.imageLabel = new JLabel();
        this.statusLabel = new JLabel();

        final JPanel panel = new JPanel();
        panel.add(imageLabel, BorderLayout.CENTER);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(panel);
        
        final JMenuBar menubar = new JMenuBar();

        final JMenu fileMenu = new JMenu(getString("menu_file"));
        fileMenu.setMnemonic('F');
        menubar.add(fileMenu);

        final JMenuItem saveAsMenuItem = new JMenuItem(getString("menu_save_as"));
        saveAsMenuItem.addActionListener(e -> presenter.handleSaveImageAs());
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMaskEx()));
        fileMenu.add(saveAsMenuItem);

        final JMenuItem exitMenuItem = new JMenuItem(getString("menu_exit"));
        exitMenuItem.addActionListener(e -> presenter.handleExit());
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu(getString("menu_help"));
        helpMenu.setMnemonic('H');
        menubar.add(helpMenu);

        final JMenuItem aboutMenuItem = new JMenuItem(getString("menu_about"));
        aboutMenuItem.addActionListener(e -> presenter.handleShowAboutInfo());
        helpMenu.add(aboutMenuItem);

        final JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(0, 0, 0, 0)));

        statusLabel.setBackground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusBar.add(statusLabel);

        frame.setJMenuBar(menubar);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private String getString(String menu_about) {
        return messages.getString(menu_about);
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    @Override
    public void display() {
        SwingUtilities.invokeLater(() -> {
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        });
    }

    @Override
    public void dispose() {
        SwingUtilities.invokeLater(() -> frame.dispose());
    }

    @Override
    public void showCannotSaveFileError() {
        JOptionPane.showMessageDialog(frame, getString("error_message_save_file_as"), getString("error_title_save_file_as"), JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showAboutDialogInfo() {
        JOptionPane.showMessageDialog(frame, getString("dialog_about_message"), getString("dialog_about_title"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public File showSaveImageDialog(String[] fileExtensions) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(String.format(getString("image_file_filter_title"), StringUtils.join(fileExtensions, ",")), fileExtensions));
        int sf = fileChooser.showSaveDialog(fileChooser);

        return (sf == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

    @Override
    public void loadImage(byte[] data) {
        final ImageIcon imageIcon = new ImageIcon(data);

        imageLabel.setIcon(imageIcon);
        statusLabel.setText(String.format(getString("image_resolution"), imageIcon.getIconWidth(), imageIcon.getIconHeight()));

        frame.pack();
    }
}