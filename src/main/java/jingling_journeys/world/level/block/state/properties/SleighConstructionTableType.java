package jingling_journeys.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SleighConstructionTableType implements StringRepresentable {
    MAIN("main"),
    EXTENSION("extension");

    private final String name;

    SleighConstructionTableType(String pName) {
        this.name = pName;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }

    public SleighConstructionTableType getOpposite() {

        return switch (this) {
            case MAIN -> EXTENSION;
            case EXTENSION -> MAIN;
        };
    }
}
