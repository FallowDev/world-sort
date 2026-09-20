package eu.fallowdeer.worldsort;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
public final class WorldSortScreen extends Screen {
    private final Screen parent;
    public WorldSortScreen(Screen parent) {
        super(Component.literal("World Sort"));
        this.parent = parent;
    }
    @Override
    protected void init() {
        int gap = 8;
        int columns = width >= 480 ? 4 : 2;
        int rows = 4 / columns;
        int cardWidth = Math.min(112, (width - 40 - gap * (columns - 1)) / columns);
        int cardHeight = Math.min(116, Math.max(54, (height - 112 - gap * (rows - 1)) / rows));
        int totalWidth = columns * cardWidth + (columns - 1) * gap;
        int top = Math.max(42, (height - (rows * cardHeight + (rows - 1) * gap + 52)) / 2);
        for (int i = 0; i < 4; i++) {
            WorldCategory category = WorldCategory.values()[i];
            addRenderableWidget(new CategoryButton((width - totalWidth) / 2 + (i % columns) * (cardWidth + gap), top + (i / columns) * (cardHeight + gap), cardWidth, cardHeight, category));
        }
        int bottom = top + rows * (cardHeight + gap);
        addRenderableWidget(Button.builder(Component.literal("All Worlds"), b -> open(WorldCategory.ALL)).bounds(width / 2 - 100, bottom, 200, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Back"), b -> onClose()).bounds(width / 2 - 100, bottom + 24, 200, 20).build());
    }
    private void open(WorldCategory category) {
        Platform.open(new SortedWorldScreen(this, category));
    }
    @Override
    public void onClose() {
        Platform.open(parent);
    }
    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.centeredText(font, title, width / 2, 16, 0xFFFFFFFF);
    }
    private final class CategoryButton extends Button {
        private final WorldCategory category;
        private final Identifier texture;
        private CategoryButton(int x, int y, int width, int height, WorldCategory category) {
            super(x, y, width, height, Component.literal(category.label), b -> open(category), DEFAULT_NARRATION);
            this.category = category;
            this.texture = Identifier.fromNamespaceAndPath("worldsort", "textures/gui/" + category.icon + ".png");
        }
        @Override
        protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
            int x = getX();
            int y = getY();
            boolean highlighted = isHoveredOrFocused();
            graphics.fill(x, y, x + width, y + height, highlighted ? category.color : 0xFF707070);
            graphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, highlighted ? 0xF03B4147 : 0xE825292E);
            int size = Math.min(36, Math.min(width - 16, height - 30));
            int iconX = x + (width - size) / 2;
            int iconY = y + (height - size - 18) / 2;
            graphics.blit(texture, iconX, iconY, iconX + size, iconY + size, 0, 1, 0, 1);
            graphics.centeredText(font, getMessage(), x + width / 2, y + height - 20, highlighted ? category.color : 0xFFFFFFFF);
        }
    }
}
