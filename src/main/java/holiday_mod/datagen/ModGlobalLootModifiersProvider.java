package holiday_mod.datagen;

import holiday_mod.loot.AddItemModifier;
import holiday_mod.Main;
import holiday_mod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.RegistryObject;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Main.MOD_ID);
    }

    @Override
    protected void start() {
        for (String path : new String[]{
                "village_armorer",
                "village_butcher",
                "village_cartographer",
                "village_desert_house",
                "village_fisher",
                "village_fletcher",
                "village_mason",
                "village_plains_house",
                "village_savanna_house",
                "village_shepherd",
                "village_snowy_house",
                "village_tannery",
                "village_taiga_house",
                "village_temple",
                "village_toolsmith",
                "village_weaponsmith",
                "abandoned_mineshaft",
                "shipwreck_map",
                "shipwreck_supply",
                "shipwreck_treasure",
                "underwater_ruins_big",
                "underwater_ruins_small",
                "nether_bridge"
        }) {
            ResourceLocation location =
                    ResourceLocation.tryParse("chests/" + (path.contains("village") ? "village/" : "") + path);
            assert location != null;
            for (RegistryObject<Item> item : ModItems.ALL_CANDIES) {
                assert item.getId() != null;
                add(path + "_" + item.getId().getPath(), new AddItemModifier(new LootItemCondition[]{
                        LootItemRandomChanceCondition.randomChance((float) (0.5 / (float) ModItems.ALL_CANDIES.size()))
                                .build(),
                        new LootTableIdCondition.Builder(location).build()
                }, item.get(), 1, 5));
            }
        }
    }
}
