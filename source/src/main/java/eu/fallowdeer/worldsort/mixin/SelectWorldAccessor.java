package eu.fallowdeer.worldsort.mixin;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(value = SelectWorldScreen.class, remap = false)
public interface SelectWorldAccessor {
    @Accessor("lastScreen")
    Screen worldsort$getParent();
}
