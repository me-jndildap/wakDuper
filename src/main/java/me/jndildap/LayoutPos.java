package me.jndildap;

import net.minecraft.client.util.Window;
import net.minecraft.text.Text;

public class LayoutPos {
    private static final Window win = WakDuper.mc.getWindow();
    public static int baseY() {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> 190;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> win.getScaledHeight() + 6;
            case NONE -> 9999;
        };
    }
    public static int signBaseY() {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> 54;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> win.getScaledHeight() - 28;
            case NONE -> 9999;
        };
    }
    public static int xValue(int width) {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, BOTTOM_LEFT -> 4;
            case TOP_RIGHT, BOTTOM_RIGHT -> win.getScaledWidth() - width - 4;
            case NONE -> 9999;
        };
    }
    public static int sendChatYPos() {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> 220;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> win.getScaledHeight() - 204;
            case NONE -> 9999;
        };
    }
    public static int getNameYPos() {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> 190;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> win.getScaledHeight() - 174;
            case NONE -> 9999;
        };
    }
    public static int windowIdY(boolean syncId) {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> syncId ? 5 : 20;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> LayoutPos.baseY() - (syncId ? 79 : 89);
            case NONE -> 9999;
        };
    }
    public static int windowIdX(Text text) {
        final int topRight = WakDuper.mc.getWindow().getScaledWidth() - WakDuper.mc.textRenderer.getWidth(text) - 4,
                bottomRight = (WakDuper.mc.getWindow().getScaledWidth() - 82) - (WakDuper.mc.textRenderer.getWidth(text) + 4);
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT -> 4;
            case TOP_RIGHT -> topRight;
            case BOTTOM_LEFT -> 88;
            case BOTTOM_RIGHT -> bottomRight;
            case NONE -> 9999;
        };
    }
    public static int signPosY() {
        return switch(WakDuper.config.getLayoutMode()) {
            case TOP_LEFT, TOP_RIGHT -> 78;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> LayoutPos.signBaseY() - 64;
            case NONE -> 9999;
        };
    }
}