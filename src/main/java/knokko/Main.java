package knokko;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = "boat protector", name = "boat protector", version = "1.0")
public class Main {
	
	@Instance("boat protector")
	public static Main modInstance;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new knokko.EventHandler());
		registerEntity(ProtectedBoat.class, "Protected Boat");
	}
	
	public static void registerEntity(Class entityClass, String name){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, modInstance, 64, 1, true);
	}
}
