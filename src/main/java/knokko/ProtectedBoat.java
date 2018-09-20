package knokko;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ProtectedBoat extends EntityBoat {
	
	public ProtectedBoat(World world){
		super(world);
	}
	
	public ProtectedBoat(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		isImmuneToFire = true;
	}

	@Override
	public void moveEntity(double x, double y, double z){
		isCollidedHorizontally = false;
		isCollidedVertically = false;
		for (int i = 0; i < 5; ++i){
			double d1 = this.getEntityBoundingBox().minY + (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * (double)(i + 0) / (double)5 - 0.125D;
        	double d3 = this.getEntityBoundingBox().minY + (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * (double)(i + 1) / (double)5 - 0.125D;
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.getEntityBoundingBox().minX, d1, this.getEntityBoundingBox().minZ, this.getEntityBoundingBox().maxX, d3, this.getEntityBoundingBox().maxZ);
			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.lava))
        	{
				if(y < 0)
					y = 0;
				if(motionY < 0)
					motionY = 0;
        	}
			AxisAlignedBB aabb = new AxisAlignedBB(this.getEntityBoundingBox().minX, d1 + 0.1, this.getEntityBoundingBox().minZ, this.getEntityBoundingBox().maxX, d3 + 0.1, this.getEntityBoundingBox().maxZ);
			if(worldObj.isAABBInMaterial(aabb, Material.lava)){
				y += 0.05;
			}
		}
		super.moveEntity(x, y, z);
		isCollidedHorizontally = false;
		isCollidedVertically = false;
		System.out.println("isRemote = " + worldObj.isRemote + " posY = " + posY);
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		if(riddenByEntity instanceof EntityLivingBase){
			((EntityLivingBase) riddenByEntity).addPotionEffect(new PotionEffect(12, 10));
		}
		if(riddenByEntity != null)
			riddenByEntity.extinguish();
	}
}
