package knokko;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityBoat && !(event.entity instanceof ProtectedBoat)){
			EntityBoat boat = (EntityBoat)event.entity;
			event.setCanceled(true);
			if(event.world.isRemote)
				return;
			EntityBoat boat2 = new ProtectedBoat(event.world, boat.posX, boat.posY, boat.posZ);
			boat2.copyDataFromOld(boat);
			event.world.spawnEntityInWorld(boat2);
		}
	}
	
	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event){
		if(event.target instanceof EntityBoat){
			EntityBoat boat = (EntityBoat) event.target;
			System.out.println("boat: " + boat);
			if(boat.ridingEntity != null){
				event.setCanceled(true);
			}
		}
	}
}
