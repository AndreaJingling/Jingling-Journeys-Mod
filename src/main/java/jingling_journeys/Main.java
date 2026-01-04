/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025  Jingling Journeys Team (WIP name)<br>
 *         <br>
 *         This file part of Jingling Journeys.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU Lesser General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU Lesser General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU Lesser General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package jingling_journeys;

import com.mojang.datafixers.util.Pair;
import jingling_journeys.client.ModRecipeBookCategories;
import jingling_journeys.client.renderer.entity.AbstractSleighRenderer;
import jingling_journeys.client.renderer.entity.ElflikeRenderer;
import jingling_journeys.client.renderer.entity.ReindeerRenderer;
import jingling_journeys.loot.ModLootModifiers;
import jingling_journeys.client.gui.screens.inventory.SleighConstructionTableScreen;
import jingling_journeys.sounds.ModSoundEvents;
import jingling_journeys.stats.ModStats;
import jingling_journeys.world.entity.ModEntityTypes;
import jingling_journeys.world.entity.animal.Reindeer;
import jingling_journeys.world.entity.npc.Elf;
import jingling_journeys.world.inventory.ModMenuTypes;
import jingling_journeys.world.inventory.ModRecipeBookTypes;
import jingling_journeys.world.inventory.SleighConstructionTableMenu;
import jingling_journeys.world.item.ModCreativeTabs;
import jingling_journeys.world.item.ModItems;
import jingling_journeys.world.item.crafting.ModRecipeTypes;
import jingling_journeys.world.item.crafting.ModRecipes;
import jingling_journeys.world.level.block.ModBlocks;
import jingling_journeys.world.level.block.entity.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "jingling_journeys";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Main.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Main.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);
    public static final DeferredRegister<StatType<?>> STAT_TYPES =
            DeferredRegister.create(ForgeRegistries.STAT_TYPES, Main.MOD_ID);
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS =
            DeferredRegister.create(Registries.CUSTOM_STAT, Main.MOD_ID);


    static {
        ModItems.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModCreativeTabs.init();
        ModEntityTypes.init();
        ModMenuTypes.init();
        ModRecipes.init();
        ModRecipeTypes.init();
        ModRecipeBookTypes.init();
        ModSoundEvents.init();
        ModStats.init();
    }

    public Main (FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENUS.register(modEventBus);
        RECIPES.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        STAT_TYPES.register(modEventBus);
        CUSTOM_STATS.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register the loot modifiers for changing existing loot pools.
        ModLootModifiers.register(modEventBus);
    }

    // https://gist.github.com/TelepathicGrunt/4fdbc445ebcbcbeb43ac748f4b18f342
    /**
     * Adds the building to the targeted pool.<br>
     * We will call this in addNewVillageBuilding method further down to add to every village.<br>
     * <br>
     * Note: This is an additive operation which means multiple mods can do this and they stack with each other
     * safely.<br>
     */
    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
                                          Registry<StructureProcessorList> processorListRegistry,
                                          ResourceLocation poolRL,
                                          String nbtPieceRL) {

        // Grabs the processor list we want to use along with our piece.
        Holder<StructureProcessorList> processorList = processorListRegistry
                .getHolderOrThrow(ProcessorLists.MOSSIFY_10_PERCENT);

        // Grab the pool we want to add to
        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        // Grabs the nbt piece and creates a SinglePoolElement of it that we can add to a structure's pool.
        // Use .legacy( for villages/outposts and .single( for everything else
        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL, processorList)
                .apply(StructureTemplatePool.Projection.RIGID);

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's templates field public for us to see.
        // Weight is handled by how many times the entry appears in this list.
        // We do not need to worry about immutability as this field is created using Lists.newArrayList(); which makes a mutable list.
        for (int i = 0; i < 2; i++) {
            pool.templates.add(piece);
        }

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's rawTemplates field public for us to see.
        // This list of pairs of pieces and weights is not used by vanilla by default but another mod may need it for efficiency.
        // So lets add to this list for completeness. We need to make a copy of the array as it can be an immutable list.
        //   NOTE: This is a com.mojang.datafixers.util.Pair. It is NOT a fastUtil pair class. Use the mojang class.
        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, 2));
        pool.rawTemplates = listOfPieceEntries;
    }

    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CommonForgeEvents {
        @SubscribeEvent
        public static void onWanderingTrades(WandererTradesEvent event) {
            for (RegistryObject<Item> item : ModItems.ALL_CANDIES)
                event.getGenericTrades().add(new BasicItemListing(2, new ItemStack(item.get(), 1),
                        5, 10));
        }

        // https://gist.github.com/TelepathicGrunt/4fdbc445ebcbcbeb43ac748f4b18f342
        @SubscribeEvent
        public static void onServerAboutToStart(ServerAboutToStartEvent event) {
            Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess()
                    .registry(Registries.TEMPLATE_POOL).orElseThrow();
            Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess()
                    .registry(Registries.PROCESSOR_LIST).orElseThrow();

            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/desert/houses"),
                    "jingling_journeys:village/desert/houses/desert_leatherworkers_house_1");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/desert/houses"),
                    "jingling_journeys:village/desert/houses/desert_toysmith_house_1");

            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/plains/houses"),
                    "jingling_journeys:village/plains/houses/plains_leatherworkers_house_1");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/plains/houses"),
                    "jingling_journeys:village/plains/houses/plains_sleigh_construction");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/plains/houses"),
                    "jingling_journeys:village/plains/houses/plains_toysmith_house_1");

            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/savanna/houses"),
                    "jingling_journeys:village/savanna/houses/savanna_leatherworkers_house_1");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/savanna/houses"),
                    "jingling_journeys:village/savanna/houses/savanna_toysmith_house_1");

            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/snowy/houses"),
                    "jingling_journeys:village/snowy/houses/snowy_leatherworkers_house_1");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/snowy/houses"),
                    "jingling_journeys:village/snowy/houses/snowy_sleigh_construction");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/snowy/houses"),
                    "jingling_journeys:village/snowy/houses/snowy_toysmith_house_1");

            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/taiga/houses"),
                    "jingling_journeys:village/taiga/houses/taiga_leatherworkers_house_1");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/taiga/houses"),
                    "jingling_journeys:village/taiga/houses/taiga_sleigh_construction");
            addBuildingToPool(templatePoolRegistry, processorListRegistry,
                    ResourceLocation.tryParse("minecraft:village/taiga/houses"),
                    "jingling_journeys:village/taiga/houses/taiga_toysmith_house_1");
        }
    }

    @SuppressWarnings("unused")
    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {

    }

    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonModEvents {
        @SubscribeEvent
        public static void commonSetup(@SuppressWarnings("unused") final FMLCommonSetupEvent event) {
        }

        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.elfEntityType.get(), Elf.createAttributes().build());
            event.put(ModEntityTypes.reindeerEntityType.get(), Reindeer.createAttributes().build());
        }

        @SubscribeEvent
        public static void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
            event.registerBookCategories(ModRecipeBookTypes.SLEIGH_CONSTRUCTING,
                    List.of(ModRecipeBookCategories.SLEIGH_CONSTRUCTING_MISC));

            event.registerRecipeCategoryFinder(ModRecipeTypes.SLEIGH_CONSTRUCTION_TYPE.get(),
                    (recipe) -> ModRecipeBookCategories.SLEIGH_CONSTRUCTING_MISC);
        }

        @SubscribeEvent
        public static void onRegister(RegisterEvent event) {
            if (Registries.CUSTOM_STAT.equals(event.getRegistryKey()))
            {
                ModStats.registerCustomStat(event, ModStats.INTERACT_WITH_SLEIGH_CONSTRUCTION_TABLE, StatFormatter.DEFAULT);
                ModStats.registerCustomStat(event, ModStats.INTERACT_WITH_TOYSMITH_TABLE, StatFormatter.DEFAULT);
                ModStats.registerCustomStat(event, ModStats.INTERACT_WITH_LEATHERWORKER_TABLE, StatFormatter.DEFAULT);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU_SINGLE.get(), (SleighConstructionTableMenu pMenu,
                                                                                     Inventory pPlayerInventory,
                                                                                     Component pTitle) ->
                    new SleighConstructionTableScreen(pMenu, pPlayerInventory, pTitle, false));
            MenuScreens.register(ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU_EXTENDED.get(), (SleighConstructionTableMenu pMenu,
                                                                                            Inventory pPlayerInventory,
                                                                                            Component pTitle) ->
                    new SleighConstructionTableScreen(pMenu, pPlayerInventory, pTitle, true));
        }

        @SubscribeEvent
        public static void onEntityRegisterRenderersEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.elfEntityType.get(), ElflikeRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.genericSmallSledEntityType.get(), AbstractSleighRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.reindeerEntityType.get(), ReindeerRenderer::new);
        }
    }
}
