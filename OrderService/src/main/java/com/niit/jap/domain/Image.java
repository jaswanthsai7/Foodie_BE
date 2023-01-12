package com.niit.jap.domain;

public class Image {
    private byte[] image;
    private String fileName;

    public Image() {
    }

    public Image(byte[] image, String fileName) {
        this.image = image;
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
