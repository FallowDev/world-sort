package eu.fallowdeer.worldsort;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.storage.LevelSummary;
public enum WorldCategory {
    SURVIVAL("Survival", "survival", GameType.SURVIVAL, 0xFF75BF65),
    HARDCORE("Hardcore", "hardcore", GameType.SURVIVAL, 0xFFE36A65),
    ADVENTURE("Adventure", "adventure", GameType.ADVENTURE, 0xFFE4C26A),
    CREATIVE("Creative", "creative", GameType.CREATIVE, 0xFF79B9E5),
    ALL("All Worlds", "", null, 0xFFFFFFFF);
    public final String label;
    public final String icon;
    public final int color;
    private final GameType gameType;
    WorldCategory(String label, String icon, GameType gameType, int color) {
        this.label = label;
        this.icon = icon;
        this.gameType = gameType;
        this.color = color;
    }
    public boolean accepts(LevelSummary world) {
        if (this == ALL) return true;
        if (world.getSettings() == null) return false;
        if (this == HARDCORE) return world.isHardcore();
        return !world.isHardcore() && world.getGameMode() == gameType;
    }
}
