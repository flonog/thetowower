package com.floleproto.thetower.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Title {
    private String title = "";
    private String subtitle = "";
    private int fadeIn = 20;
    private int duration = 80;
    private int fadeOut = 20;

    public Title(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }

    public Title(String title, String subtitle, int fadeIn, int duration, int fadeOut){
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
    }

    public void sendToPlayer(Player p){
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer subtitlePacket = getSubtitlePacket();
        try {
            manager.sendServerPacket(p, subtitlePacket);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        PacketContainer titlePacket = getTitlePacket();
        try {
            manager.sendServerPacket(p, titlePacket);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(){
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer subtitlePacket = getSubtitlePacket();
        manager.broadcastServerPacket(subtitlePacket);

        PacketContainer titlePacket = getTitlePacket();
        manager.broadcastServerPacket(titlePacket);
    }

    private PacketContainer getSubtitlePacket(){
        PacketContainer subtitlePacket = new PacketContainer(PacketType.Play.Server.TITLE);
        subtitlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.SUBTITLE);
        subtitlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(subtitle));
        return  subtitlePacket;
    }

    private PacketContainer getTitlePacket() {
        PacketContainer titlePacket = new PacketContainer(PacketType.Play.Server.TITLE);
        titlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.TITLE);
        titlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(title));
        titlePacket.getIntegers().write(0, fadeIn).write(1, duration).write(2, fadeOut);
        return titlePacket;
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
