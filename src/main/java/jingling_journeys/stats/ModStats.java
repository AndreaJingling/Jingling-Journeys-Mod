package jingling_journeys.stats;

import jingling_journeys.Main;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStats {
    public static final ResourceLocation INTERACT_WITH_SLEIGH_CONSTRUCTION_TABLE
            = makeCustomStat("interact_with_sleigh_constructioon_table");
    public static final ResourceLocation INTERACT_WITH_TOYSMITH_TABLE
            = makeCustomStat("interact_with_toysmith_table");
    public static final ResourceLocation INTERACT_WITH_LEATHERWORKER_TABLE
            = makeCustomStat("interact_with_leatherworker_table");

    private static RegistryObject<StatType<?>> registerRegistryType(String id, Registry<?> registry) {
        return Main.STAT_TYPES.register(id, () -> new StatType<>(registry));
    }


    @SuppressWarnings("SameParameterValue")
    public static void registerCustomStat(RegisterEvent event, ResourceLocation value, StatFormatter pFormatter) {
        event.register(Registries.CUSTOM_STAT, value, () -> value);
        Stats.CUSTOM.get(value, pFormatter);
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation makeCustomStat(String pKey) {
        return ResourceLocation.tryBuild(Main.MOD_ID, pKey);
    }

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
