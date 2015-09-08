package lumaceon.mods.clockworkphase2.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import lumaceon.mods.clockworkphase2.extendeddata.ExtendedMapData;
import lumaceon.mods.clockworkphase2.lib.Defaults;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGeneratorRuins implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int dimID = world.provider.dimensionId;
        if(dimID == 0 || dimID == Defaults.DIM_ID.FIRST_AGE || dimID == Defaults.DIM_ID.SECOND_AGE || dimID == Defaults.DIM_ID.THIRD_AGE || dimID == Defaults.DIM_ID.FORTH_AGE)
        {
            ExtendedMapData worldData = ExtendedMapData.get(world);
            if(worldData != null && worldData.isRuinMapGenerated())
                worldData.generateRuins(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }
}