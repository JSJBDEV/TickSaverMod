package uk.co.conclipsegames.ticksave.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import uk.co.conclipsegames.ticksave.ticksave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 20/08/2017.
 */
public class ItemBase extends Item {
    protected String name;
    Boolean startRec = false;
    Boolean startPlay = false;
    private static int i;
    private static List<BlockPos> loc = new ArrayList<BlockPos>();
    private static List<Float> pitch = new ArrayList<Float>();
    private static List<Float> yaw = new ArrayList<Float>();


    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void registerItemModel() {
        ticksave.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack p_onItemRightClick_1_, World p_onItemRightClick_2_, EntityPlayer player, EnumHand hand) {

        if (player.getHeldItem(hand).getItem() == ModItems.record)
        {

            if (player.isSneaking() && this.startRec) {
                this.startRec = false;
                player.addChatMessage(new TextComponentString("recording stopped"));
                String ticks = String.valueOf(loc.size() + " ticks long");
                player.addChatMessage(new TextComponentString(ticks));
            } else
            {
                if(!player.isSneaking())
                {
                this.startRec = true;
                player.addChatMessage(new TextComponentString("recording started"));
                }

            }
        }
        if(player.getHeldItem(hand).getItem() == ModItems.replay)
        {
            if(player.isSneaking())
            {
                loc.clear();
                yaw.clear();
                pitch.clear();
                player.addChatMessage(new TextComponentString("recording deleted"));
            }
            else
            {
                player.addChatMessage(new TextComponentString("replay started"));
                this.startPlay = true;
                i = 0;

            }
        }
        return super.onItemRightClick(p_onItemRightClick_1_, p_onItemRightClick_2_, player, hand);
    }

    @Override
    public void onUpdate(ItemStack p_onUpdate_1_, World p_onUpdate_2_, Entity player, int p_onUpdate_4_, boolean p_onUpdate_5_) {
        i++;
        if(this.startRec)
        {
            loc.add(player.getPosition());
            pitch.add(player.rotationPitch);
            yaw.add(player.rotationYaw);
        }
        if(this.startPlay)
        {
            if(i <= loc.size())
            {
                player.setNoGravity(true);
                System.out.println(i);
                //player.setPositionAndRotation(loc.get(i-1).getX(),loc.get(i-1).getY(),loc.get(i-1).getZ(),yaw.get(i-1),pitch.get(i-1));
                player.moveToBlockPosAndAngles(loc.get(i-1),yaw.get(i-1),pitch.get(i-1));
                //player.setPositionAndUpdate(loc.get(i).getX(),loc.get(i).getY(),loc.get(i).getZ());
            }
            else
            {
            this.startPlay = false;
            player.setNoGravity(false);
            }
        }

        super.onUpdate(p_onUpdate_1_, p_onUpdate_2_, player, p_onUpdate_4_, p_onUpdate_5_);
    }
}
