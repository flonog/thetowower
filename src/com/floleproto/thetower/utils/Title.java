package com.floleproto.thetower.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title {
    private String title = "";
    private String subtitle = "";
    private int fadeIn = 20;
    private int duration = 80;
    private int fadeOut = 20;

    public Title(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Title(String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
    }

    public void sendToPlayer(Player p) {

        p.sendTitle(title, subtitle, fadeIn, duration, fadeOut);
    }

    public void broadcast() {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(title, subtitle, fadeIn, duration, fadeOut));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }
}
