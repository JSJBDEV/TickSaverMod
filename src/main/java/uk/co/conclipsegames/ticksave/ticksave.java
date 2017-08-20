package uk.co.conclipsegames.ticksave;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.conclipsegames.ticksave.Items.ModItems;
import uk.co.conclipsegames.ticksave.Proxy.CommonProxy;

/**
 * Created by James on 20/08/2017.
 */
@Mod(modid = ticksave.modId, name = ticksave.name, version = ticksave.version, acceptedMinecraftVersions = "[1.10.2]")
public class ticksave {
    public static final String modId = "ticksave";
    public static final String name = "TickSave";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static ticksave instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModItems.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "uk.co.conclipsegames.ticksave.Proxy.CommonProxy", clientSide = "uk.co.conclipsegames.ticksave.Proxy.ClientProxy")
    public static CommonProxy proxy;

}
