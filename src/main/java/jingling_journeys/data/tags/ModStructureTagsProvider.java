package jingling_journeys.data.tags;

import jingling_journeys.Main;
import jingling_journeys.tags.ModStructureTags;
import jingling_journeys.world.level.levelgen.structure.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagsProvider extends StructureTagsProvider {
    public ModStructureTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,
                                    @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ModStructureTags.PEN).add(ModStructures.PEN_PLAINS, ModStructures.PEN_SNOWY, ModStructures.PEN_TAIGA);
    }
}
