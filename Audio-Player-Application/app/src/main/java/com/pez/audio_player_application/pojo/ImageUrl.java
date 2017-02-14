package com.pez.audio_player_application.pojo;

/**
 * @Auteur Baudouin
 * @Date 14/02/2017.
 */

public class ImageUrl {
    private String url;
    private String size;

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

    public ImageUrl(String url, String size) {
        this.url = url;
        this.size = size;
    }

}
