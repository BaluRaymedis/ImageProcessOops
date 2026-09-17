package org.example.imageprocessoops.FileChoose;

import javafx.stage.FileChooser;

public class FileChooseFactory {
    public static FileChooser createImageFileChooser(String title, String initialFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        if (initialFileName != null) {
            fileChooser.setInitialFileName(initialFileName);
        }
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Image", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Image", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        );
        return fileChooser;
    }
}
