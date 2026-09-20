package eu.fallowdeer.worldsort;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
public final class SortedWorldScreen extends SelectWorldScreen {
    public final WorldCategory category;
    public SortedWorldScreen(Screen parent, WorldCategory category) {
        super(parent);
        this.category = category;
    }
}
