package lumaceon.mods.clockworkphase2.init;

import cpw.mods.fml.common.registry.GameRegistry;
import lumaceon.mods.clockworkphase2.item.components.ItemClockworkCore;
import lumaceon.mods.clockworkphase2.item.components.ItemMainspring;
import lumaceon.mods.clockworkphase2.item.components.clockwork.ItemGear;
import lumaceon.mods.clockworkphase2.item.components.clockwork.ItemMemoryComponent;
import lumaceon.mods.clockworkphase2.item.construct.ItemClockworkAxe;
import lumaceon.mods.clockworkphase2.item.construct.ItemClockworkPickaxe;
import lumaceon.mods.clockworkphase2.item.construct.ItemClockworkShovel;
import lumaceon.mods.clockworkphase2.item.construct.ItemClockworkTool;
import lumaceon.mods.clockworkphase2.lib.Names;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems
{
    public static void init()
    {
        initComponents();
        initConstructs();
        initBuckets();
    }

    public static ItemMainspring mainspring;
    public static Item clockworkCore;
    public static Item gearWood;
    public static Item gearStone;
    public static Item gearIron;
    public static Item gearGold;
    public static Item gearDiamond;
    public static Item gearEmerald;
    public static Item gearQuartz;
    public static Item preciousCharm;
    public static void initComponents()
    {
        mainspring = new ItemMainspring(1, 20, Names.ITEM.MAINSPRING);
        clockworkCore = new ItemClockworkCore(1, 20, Names.ITEM.CLOCKWORK_CORE);
        gearWood = new ItemGear(Names.ITEM.GEAR_WOOD, 10, 15, 0);
        gearStone = new ItemGear(Names.ITEM.GEAR_STONE, 15, 10, 0);
        gearIron = new ItemGear(Names.ITEM.GEAR_IRON, 25, 25, 0);
        gearGold = new ItemGear(Names.ITEM.GEAR_GOLD, 10, 70, 0);
        gearDiamond = new ItemGear(Names.ITEM.GEAR_DIAMOND, 40, 50, 0);
        gearEmerald = new ItemGear(Names.ITEM.GEAR_EMERALD, 50, 40, 0);
        gearQuartz = new ItemGear(Names.ITEM.GEAR_QUARTZ, 70, 10, 0);
        preciousCharm = new ItemMemoryComponent(Names.ITEM.PRECIOUS_CHARM, 0, 0, 20);

        GameRegistry.registerItem(mainspring, Names.ITEM.MAINSPRING);
        GameRegistry.registerItem(clockworkCore, Names.ITEM.CLOCKWORK_CORE);
        GameRegistry.registerItem(gearWood, Names.ITEM.GEAR_WOOD);
        GameRegistry.registerItem(gearStone, Names.ITEM.GEAR_STONE);
        GameRegistry.registerItem(gearIron, Names.ITEM.GEAR_IRON);
        GameRegistry.registerItem(gearGold, Names.ITEM.GEAR_GOLD);
        GameRegistry.registerItem(gearDiamond, Names.ITEM.GEAR_DIAMOND);
        GameRegistry.registerItem(gearEmerald, Names.ITEM.GEAR_EMERALD);
        GameRegistry.registerItem(gearQuartz, Names.ITEM.GEAR_QUARTZ);
        GameRegistry.registerItem(preciousCharm, Names.ITEM.PRECIOUS_CHARM);
    }

    public static Item.ToolMaterial clockworkMaterial = EnumHelper.addToolMaterial("CLOCKWORK", 3, 100, 0, 0, 0);
    public static Item clockworkPickaxe;
    public static Item clockworkAxe;
    public static Item clockworkShovel;
    public static Item clockworkTool;
    public static void initConstructs()
    {
        clockworkPickaxe = new ItemClockworkPickaxe(clockworkMaterial, Names.ITEM.CLOCKWORK_PICKAXE);
        clockworkAxe = new ItemClockworkAxe(clockworkMaterial, Names.ITEM.CLOCKWORK_AXE);
        clockworkShovel = new ItemClockworkShovel(clockworkMaterial, Names.ITEM.CLOCKWORK_SHOVEL);
        clockworkTool = new ItemClockworkTool(0, Item.ToolMaterial.IRON, ItemPickaxe.itemRegistry.getKeys(), Names.ITEM.CLOCKWORK_TOOL);

        GameRegistry.registerItem(clockworkPickaxe, Names.ITEM.CLOCKWORK_PICKAXE);
        GameRegistry.registerItem(clockworkAxe, Names.ITEM.CLOCKWORK_AXE);
        GameRegistry.registerItem(clockworkShovel, Names.ITEM.CLOCKWORK_SHOVEL);
        GameRegistry.registerItem(clockworkTool, Names.ITEM.CLOCKWORK_TOOL);
    }

    public static Item bucketTimeSand;
    public static void initBuckets()
    {

    }
}