package WayofTime.bloodmagic.structures;

import java.util.Random;

import WayofTime.bloodmagic.api.Constants;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class BuildTestStructure
{
    public boolean placeStructureAtPosition(Random rand, Rotation baseRotation, WorldServer world, BlockPos pos, int iteration)
    {
        if (pos == null)
            return false;

        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = world.getStructureTemplateManager();

        ResourceLocation resource = new ResourceLocation(Constants.Mod.MODID, "Corridor1");
        Template template = templatemanager.getTemplate(minecraftserver, resource);

        if (template == null)
        {
            System.out.println("Invalid template for location: " + resource);
            return false;
        }

        PlacementSettings settings = new PlacementSettings();
        settings.setMirror(Mirror.NONE);

        Rotation rot;

        rot = baseRotation;
        if (rot == null)
            rot = Rotation.NONE;

        settings.setRotation(rot);
        settings.setIgnoreEntities(true);
        settings.setChunk((ChunkPos) null);
        settings.setReplacedBlock((Block) null);
        settings.setIgnoreStructureBlock(false);

//        settings.func_189946_a(MathHelper.clamp_float(schema.integrity, 0.0F, 1.0F));

        BlockPos offset = Template.transformedBlockPos(settings, new BlockPos(0, 0, 0));
        BlockPos finalPos = pos.add(offset);
        template.addBlocksToWorldChunk(world, finalPos, settings);

        return true;
    }
}
