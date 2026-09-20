package eu.fallowdeer.worldsort;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
public final class Platform {
    private Platform() {}
    public static void open(Screen screen) { Minecraft.getInstance().setScreen(screen); }
}
