package holiday_mod.world.level.block.entity;

import holiday_mod.world.level.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import static holiday_mod.Main.BLOCK_ENTITIES;

public class ModBlockEntities {
    public static final RegistryObject<BlockEntityType<SleighConstructionTableBlockEntity>> SLEIGH_CONSTRUCTION_TABLE_BE
            = BLOCK_ENTITIES.register("sleigh_construction_table_block_entity",
            () -> BlockEntityType.Builder.of(SleighConstructionTableBlockEntity::new,
                    ModBlocks.SLEIGH_CONSTRUCTION_TABLE.get())
                    .build(null));

    public static void init() {

    }
}
