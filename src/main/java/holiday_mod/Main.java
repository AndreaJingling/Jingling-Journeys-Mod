package holiday_mod;

import holiday_mod.client.renderer.entity.ElflikeRenderer;
import holiday_mod.loot.ModLootModifiers;
import holiday_mod.client.gui.screens.inventory.SleighConstructionTableScreen;
import holiday_mod.sounds.ModSoundEvents;
import holiday_mod.world.entity.ModEntityTypes;
import holiday_mod.world.entity.npc.ElfEntity;
import holiday_mod.world.entity.npc.KringleElfEntity;
import holiday_mod.world.entity.npc.RankinBassElfEntity;
import holiday_mod.world.inventory.ModMenuTypes;
import holiday_mod.world.item.ModCreativeTabs;
import holiday_mod.world.item.ModItems;
import holiday_mod.world.item.crafting.ModRecipeTypes;
import holiday_mod.world.item.crafting.ModRecipes;
import holiday_mod.world.level.block.ModBlocks;
import holiday_mod.world.level.block.entity.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "holiday_mod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Create a Deferred Register to hold Blocks which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);
    // Create a Deferred Register to hold EntityTypes which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MOD_ID);
    // Create a Deferred Register to hold BlockEntities (BlockEntityType) which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Main.MOD_ID);
    // Create a Deferred Register to hold Menus (MenuTypes) which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MOD_ID);
    // Create a Deferred Register to hold Recipes (RecipeSerializer) which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MOD_ID);
    // Create a Deferred Register to hold Recipes Types which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Main.MOD_ID);
    // Create a Deferred Register to hold Sound Events which will all be registered under the "holiday_mod" namespace
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    static {
        ModItems.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModCreativeTabs.init();
        ModEntityTypes.init();
        ModMenuTypes.init();
        ModRecipes.init();
        ModRecipeTypes.init();
        ModSoundEvents.init();
    }

    public Main (FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so entity types get registered
        ENTITY_TYPES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so block entities get registered
        BLOCK_ENTITIES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so menu types get registered
        MENUS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so recipes get registered
        RECIPES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so recipe types get registered
        RECIPE_TYPES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so sound events get registered
        SOUND_EVENTS.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register the loot modifiers for changing existing loot pools.
        ModLootModifiers.register(modEventBus);
    }

    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CommonForgeEvents {
        @SubscribeEvent
        public static void onWanderingTrades(WandererTradesEvent event) {
            LOGGER.info("WandererTradesEvent");
            for (RegistryObject<Item> item : ModItems.ALL_CANDIES)
                event.getGenericTrades().add(new BasicItemListing(2, new ItemStack(item.get(), 1),
                        5, 10));
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
            LOGGER.info("{}{}", Config.magicNumberIntroduction, Config.magicNumber);
        }

        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            LOGGER.info("EntityAttributeCreationEvent");
            event.put(ModEntityTypes.elfEntityType.get(), ElfEntity.createAttributes().build());
            event.put(ModEntityTypes.kringleElfEntityType.get(), KringleElfEntity.createAttributes().build());
            event.put(ModEntityTypes.rankinBassElfEntityType.get(), RankinBassElfEntity.createAttributes().build());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.SLEIGH_CONSTRUCTION_TABLE_MENU.get(), SleighConstructionTableScreen::new);
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void onEntityRegisterRenderersEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.elfEntityType.get(), ElflikeRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.kringleElfEntityType.get(), ElflikeRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.rankinBassElfEntityType.get(), ElflikeRenderer::new);
        }
    }
}
