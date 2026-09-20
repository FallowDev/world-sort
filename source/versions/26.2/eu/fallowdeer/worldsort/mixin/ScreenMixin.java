package eu.fallowdeer.worldsort.mixin;
import eu.fallowdeer.worldsort.WorldSortScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
@Mixin(value = Gui.class, remap = false)
public abstract class ScreenMixin {
    @ModifyVariable(method = "setScreen", at = @At("HEAD"), argsOnly = true)
    private Screen worldsort$choose(Screen screen) {
        if (screen != null && screen.getClass() == SelectWorldScreen.class) {
            Screen parent = ((SelectWorldAccessor) screen).worldsort$getParent();
            if (parent instanceof TitleScreen) return new WorldSortScreen(parent);
        }
        return screen;
    }
}
