package eu.fallowdeer.worldsort.mixin;
import eu.fallowdeer.worldsort.SortedWorldScreen;
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(value = WorldSelectionList.class, remap = false)
public abstract class WorldListMixin {
    @Shadow @Final private Screen screen;
    @Shadow private List<LevelSummary> currentlyDisplayedLevels;
    @Shadow protected abstract void clearEntries();
    @Inject(method = "filterAccepts", at = @At("HEAD"), cancellable = true)
    private void worldsort$filter(String search, LevelSummary world, CallbackInfoReturnable<Boolean> ci) {
        if (screen instanceof SortedWorldScreen sorted && !sorted.category.accepts(world)) ci.setReturnValue(false);
    }
    @Inject(method = "handleNewLevels", at = @At("HEAD"), cancellable = true)
    private void worldsort$empty(List<LevelSummary> levels, CallbackInfo ci) {
        if (screen instanceof SortedWorldScreen && levels != null && levels.isEmpty()) {
            clearEntries();
            currentlyDisplayedLevels = levels;
            ci.cancel();
        }
    }
}
