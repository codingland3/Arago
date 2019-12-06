package com.example.arago.Model;

public class Event {

    private int images;
    private String title;
    private String desc;
    private String more;

    public Event(int images, String title, String desc, String more) {
        this.images = images;
        this.title = title;
        this.desc = desc;
        this.more = more;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
