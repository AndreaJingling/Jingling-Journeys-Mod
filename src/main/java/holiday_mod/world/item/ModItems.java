/**
 *         Minecraft Holiday Mod (WIP description)<br>
 *         Copyright (C) 2025  Minecraft Holiday Mod Team (WIP name)<br>
 *         <br>
 *         This file part of Minecraft Holiday Mod.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package holiday_mod.world.item;

import holiday_mod.Main;
import holiday_mod.world.entity.ModEntityTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    // This is all the "candy" items that will be added to loot pools and the wandering trader's trade pool.
    public static final ArrayList<RegistryObject<Item>> ALL_CANDIES = new ArrayList<>();

    public static final RegistryObject<Item> EXAMPLE_GUMMY_CANDY =
            registerSimpleCandy("example_gummy_candy",
                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> EXAMPLE_WRAPPED_CANDY =
            registerSimpleCandy("example_wrapped_candy",
                    () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1));
    public static final RegistryObject<Item> CANDY_CANE =
            register("candy_cane", CandyCane::new, CandyCane.getProperties(), true);
    public static final RegistryObject<Item> SHARPENED_CANDY_CANE =
            register("sharpened_candy_cane", SharpenedCandyCane::new, SharpenedCandyCane.getProperties(),
                    true);
    public static final RegistryObject<Item> GENERIC_SMALL_SLED =
            register("generic_small_sled", SleighItem::new, SleighItem.getProperties(),
                    false);
    public static final RegistryObject<Item> ELF_SPAWN_EGG =
            register("elf_spawn_egg", (Item.Properties itemProperties) ->
                    new ForgeSpawnEggItem(ModEntityTypes.elfEntityType,
                            0x095207, 0x520707, itemProperties),
                    new Item.Properties(), false);

    // This can handle any candy that only applies a buff on eating, currently only speed.
    public static RegistryObject<Item> registerSimpleCandy(String id,
                                                           Supplier<MobEffectInstance> effectSupplier) {
        return register(id, Item::new, new Item.Properties()
                .food((new FoodProperties.Builder())
                        .nutrition(4)
                        .saturationMod(1.2F)
                        .effect(effectSupplier, 1.0F)
                        .alwaysEat()
                        .build()), true);
    }

    // For registering non-candies.
    @SuppressWarnings("unused")
    public static RegistryObject<Item> register(String id,
                                                Function<Item.Properties, Item> constructor,
                                                Item.Properties properties) {
        return register(id, constructor, properties, false);
    }

    public static RegistryObject<Item> register(String id,
                                                Function<Item.Properties, Item> constructor,
                                                Item.Properties properties, boolean isCandy) {
        RegistryObject<Item> item = Main.ITEMS.register(id, () -> constructor.apply(properties));
        if (isCandy) ALL_CANDIES.add(item);
        return item;
    }

    // Used to load this class at the needed time from main.
    public static void init() {

    }
}
