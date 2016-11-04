package lumaceon.mods.clockworkphase2;

import lumaceon.mods.clockworkphase2.api.AchievementTiering;
import lumaceon.mods.clockworkphase2.api.MainspringMetalRegistry;
import lumaceon.mods.clockworkphase2.api.block.CustomProperties;
import lumaceon.mods.clockworkphase2.config.ConfigurationHandler;
import lumaceon.mods.clockworkphase2.creativetab.CreativeTabClockworkPhase2;
import lumaceon.mods.clockworkphase2.handler.*;
import lumaceon.mods.clockworkphase2.init.*;
import lumaceon.mods.clockworkphase2.lib.Reference;
import lumaceon.mods.clockworkphase2.network.PacketHandler;
import lumaceon.mods.clockworkphase2.proxy.IProxy;
import lumaceon.mods.clockworkphase2.recipe.ExperimentalAlloyRecipes;
import lumaceon.mods.clockworkphase2.recipe.Recipes;
import lumaceon.mods.clockworkphase2.util.Logger;
import lumaceon.mods.clockworkphase2.world.gen.WorldGeneratorOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Random;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ClockworkPhase2
{
    public static Random random = new Random(System.nanoTime());
    public static File sourceFile;

    @Mod.Instance(Reference.MOD_ID)
    public static ClockworkPhase2 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;

    public final CreativeTabs CREATIVE_TAB = new CreativeTabClockworkPhase2("ClockworkPhase2");

    public WorldGeneratorOres oreGenerator = new WorldGeneratorOres();

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        Reference.MOD_DIRECTORY = event.getSourceFile();
        ConfigurationHandler.init(event.getModConfigurationDirectory());
        ConfigurationHandler.initBlacklist(event.getModConfigurationDirectory());

        ModCapabilities.init();
        CustomProperties.init();

        ModFluids.init();

        ModItems.init();
        ModBlocks.init();
        ModBlocks.initTE();
        proxy.registerCustomModels();

        GameRegistry.registerWorldGenerator(oreGenerator, 0);

        ModFluids.bindBlocks();

        ModEntities.init();

        ExperimentalAlloyRecipes.preInit();

        proxy.registerFluidModels();
        proxy.registerKeybindings();
        sourceFile = event.getSourceFile();
        proxy.preInit();

    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModItems.initModels();
        ModBlocks.initModels();

        proxy.registerTESR();

        Recipes.init();

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new EntityHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());
        MinecraftForge.EVENT_BUS.register(new WorldHandler());
        MinecraftForge.EVENT_BUS.register(new AchievementHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityAttachmentHandler());
        proxy.initSideHandlers();

        PacketHandler.init();

        ExperimentalAlloyRecipes.postInit();
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        MainspringMetalRegistry.INTERNAL.initDefaults();
        Recipes.initAlloyRecipes();

        long value;
        Logger.info("||Detecting Achievements||");
        for(Object achievement : AchievementList.ACHIEVEMENTS)
        {
            if(achievement != null && achievement instanceof Achievement)
            {
                value = AchievementTiering.INTERNAL.registerAchievement((Achievement) achievement);
                if(value <= 0)
                    Logger.info("{SKIPPED achievement '" + ((Achievement) achievement).statId + "'}");
                else if(((Achievement) achievement).getSpecial())
                    Logger.info("{Registered achievement \'" + ((Achievement) achievement).statId + "\' with a value of " + value + "} [Special]");
                else
                    Logger.info("{Registered achievement \'" + ((Achievement) achievement).statId + "\' with a value of " + value + "}");
            }
        }
        Logger.info("Max Achievement Points: " + AchievementTiering.INTERNAL.maxPoints);

        ModArticles.initRecipes();
        if(event.getSide().isClient()) //The guidebook is client only.
            ModArticles.init(sourceFile);
    }
}