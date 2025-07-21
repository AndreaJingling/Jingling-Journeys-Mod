package holiday_mod.world.level.block.state.properties;

import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<SleighConstructionTableType> SLEIGH_CONSTRUCTION_TABLE_TYPE
            = EnumProperty.create("type", SleighConstructionTableType.class);
}
