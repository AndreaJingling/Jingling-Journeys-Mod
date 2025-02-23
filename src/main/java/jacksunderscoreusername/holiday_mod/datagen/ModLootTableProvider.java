package jacksunderscoreusername.holiday_mod.datagen;

import jacksunderscoreusername.holiday_mod.datagen.loot.HolidayBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput packOutput) {
        return new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider
                .SubProviderEntry(HolidayBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
