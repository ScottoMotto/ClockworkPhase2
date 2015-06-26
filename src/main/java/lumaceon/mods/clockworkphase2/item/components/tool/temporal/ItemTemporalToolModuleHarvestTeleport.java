package lumaceon.mods.clockworkphase2.item.components.tool.temporal;

import lumaceon.mods.clockworkphase2.api.item.ITemporalToolModule;
import lumaceon.mods.clockworkphase2.api.util.internal.Colors;
import lumaceon.mods.clockworkphase2.api.util.internal.NBTHelper;
import lumaceon.mods.clockworkphase2.api.util.internal.NBTTags;
import lumaceon.mods.clockworkphase2.item.ItemClockworkPhase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemTemporalToolModuleHarvestTeleport extends ItemClockworkPhase implements ITemporalToolModule
{
    public ItemTemporalToolModuleHarvestTeleport(int maxStack, int maxDamage, String unlocalizedName) {
        super(maxStack, maxDamage, unlocalizedName);
    }

    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te != null && te instanceof IInventory)
        {
            NBTHelper.INT.set(is, "cp_x", x);
            NBTHelper.INT.set(is, "cp_y", y);
            NBTHelper.INT.set(is, "cp_z", z);
            NBTHelper.INT.set(is, "cp_side", meta);
            if(!world.isRemote)
                player.addChatComponentMessage(new ChatComponentText(Colors.AQUA + "Inventory location saved"));
            return true;
        }
        return false;
    }

    @Override
    public boolean isEnabled(ItemStack item) {
        return NBTHelper.BOOLEAN.get(item, NBTTags.ACTIVE);
    }

    @Override
    public float getQualityMultiplier(ItemStack item) {
        return 1F;
    }

    @Override
    public float getSpeedMultiplier(ItemStack item) {
        return 1F;
    }

    @Override
    public float getMemoryMultiplier(ItemStack item) {
        return 1.5F;
    }

    @Override
    public int getColorRed(ItemStack item) {
        return 210;
    }

    @Override
    public int getColorGreen(ItemStack item) {
        return 0;
    }

    @Override
    public int getColorBlue(ItemStack item) {
        return 230;
    }
}
