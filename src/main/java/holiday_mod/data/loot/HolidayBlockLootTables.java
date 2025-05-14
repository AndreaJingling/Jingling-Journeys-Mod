package holiday_mod.data.loot;

import holiday_mod.Main;
import holiday_mod.world.level.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class HolidayBlockLootTables extends BlockLootSubProvider {
    public HolidayBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        drop(ModBlocks.SLEIGH_CONSTRUCTION_TABLE);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Main.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    // HELPER METHODS
    @SuppressWarnings("SameParameterValue")
    private void drop(RegistryObject<Block> block) {
        this.dropSelf(block.get());
    }
}
