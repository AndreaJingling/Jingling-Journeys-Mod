package holiday_mod.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SleighConstructionTableType implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private SleighConstructionTableType(String pName) {
        this.name = pName;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }

    public SleighConstructionTableType getOpposite() {

        return switch (this) {
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
