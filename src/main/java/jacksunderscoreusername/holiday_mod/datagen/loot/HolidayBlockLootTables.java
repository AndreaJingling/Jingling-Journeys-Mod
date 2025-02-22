package jacksunderscoreusername.holiday_mod.datagen.loot;

import jacksunderscoreusername.holiday_mod.Main;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class HolidayBlockLootTables extends BlockLootSubProvider {
    public HolidayBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Main.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    // HELPER METHODS
    private void drop(RegistryObject<Block> block) {
        this.dropSelf(block.get());
    }
}
