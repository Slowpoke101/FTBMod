package uk.co.andrewleedham.FTBMod.models;

import uk.co.andrewleedham.FTBMod.Entity.TileEntityPedestal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ModelPedestal extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer StandTop;
    ModelRenderer Top_Base;
    ModelRenderer Top_Left;
    ModelRenderer Top_Right;
    ModelRenderer Top_Back;
    ModelRenderer Top_Front;
    ModelRenderer Stand;
    ModelRenderer Cube1;
    ModelRenderer Cube2;
    ModelRenderer Cube3;
    ModelRenderer Cube4;
    ModelRenderer Cube5;
    ModelRenderer Cube6;
    ModelRenderer Cube7;
    ModelRenderer Cube8;
    ModelRenderer Cube9;
    ModelRenderer Cube10;
  
  public ModelPedestal()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 24, 15);
      Base.addBox(-4F, 0F, -4F, 8, 1, 8);
      Base.setRotationPoint(0F, 23F, 0F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      StandTop = new ModelRenderer(this, 24, 15);
      StandTop.addBox(-4F, 0F, -4F, 8, 1, 8);
      StandTop.setRotationPoint(0F, 14F, 0F);
      StandTop.setTextureSize(64, 32);
      StandTop.mirror = true;
      setRotation(StandTop, 0F, 0F, 0F);
      Top_Base = new ModelRenderer(this, 0, 0);
      Top_Base.addBox(-5F, 0F, -5F, 10, 2, 10);
      Top_Base.setRotationPoint(0F, 12F, 0F);
      Top_Base.setTextureSize(64, 32);
      Top_Base.mirror = true;
      setRotation(Top_Base, 0F, 0F, 0F);
      Top_Left = new ModelRenderer(this, 0, 12);
      Top_Left.addBox(-1F, 0F, -5F, 2, 1, 10);
      Top_Left.setRotationPoint(4F, 11F, 0F);
      Top_Left.setTextureSize(64, 32);
      Top_Left.mirror = true;
      setRotation(Top_Left, 0F, 0F, 0F);
      Top_Right = new ModelRenderer(this, 0, 12);
      Top_Right.addBox(-1F, 0F, -5F, 2, 1, 10);
      Top_Right.setRotationPoint(-4F, 11F, 0F);
      Top_Right.setTextureSize(64, 32);
      Top_Right.mirror = true;
      setRotation(Top_Right, 0F, 0F, 0F);
      Top_Back = new ModelRenderer(this, 24, 12);
      Top_Back.addBox(-3F, 0F, -1F, 6, 1, 2);
      Top_Back.setRotationPoint(0F, 11F, 4F);
      Top_Back.setTextureSize(64, 32);
      Top_Back.mirror = true;
      setRotation(Top_Back, 0F, 0F, 0F);
      Top_Front = new ModelRenderer(this, 24, 12);
      Top_Front.addBox(-3F, 0F, -1F, 6, 1, 2);
      Top_Front.setRotationPoint(0F, 11F, -4F);
      Top_Front.setTextureSize(64, 32);
      Top_Front.mirror = true;
      setRotation(Top_Front, 0F, 0F, 0F);
      Stand = new ModelRenderer(this, 40, 0);
      Stand.addBox(-2F, 0F, -2F, 4, 8, 4);
      Stand.setRotationPoint(0F, 15F, 0F);
      Stand.setTextureSize(64, 32);
      Stand.mirror = true;
      setRotation(Stand, 0F, 0F, 0F);
      Cube1 = new ModelRenderer(this, 0, 28);
      Cube1.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube1.setRotationPoint(0F, 10F, 2F);
      Cube1.setTextureSize(64, 32);
      Cube1.mirror = true;
      setRotation(Cube1, 0F, 0F, 0F);
      Cube2 = new ModelRenderer(this, 0, 28);
      Cube2.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube2.setRotationPoint(0F, 10F, -2F);
      Cube2.setTextureSize(64, 32);
      Cube2.mirror = true;
      setRotation(Cube2, 0F, 0F, 0F);
      Cube3 = new ModelRenderer(this, 0, 28);
      Cube3.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube3.setRotationPoint(0F, 10F, 0F);
      Cube3.setTextureSize(64, 32);
      Cube3.mirror = true;
      setRotation(Cube3, 0F, 0F, 0F);
      Cube4 = new ModelRenderer(this, 0, 28);
      Cube4.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube4.setRotationPoint(-2F, 10F, 0F);
      Cube4.setTextureSize(64, 32);
      Cube4.mirror = true;
      setRotation(Cube4, 0F, 0F, 0F);
      Cube5 = new ModelRenderer(this, 0, 28);
      Cube5.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube5.setRotationPoint(-2F, 10F, 2F);
      Cube5.setTextureSize(64, 32);
      Cube5.mirror = true;
      setRotation(Cube5, 0F, 0F, 0F);
      Cube6 = new ModelRenderer(this, 0, 28);
      Cube6.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube6.setRotationPoint(-2F, 10F, -2F);
      Cube6.setTextureSize(64, 32);
      Cube6.mirror = true;
      setRotation(Cube6, 0F, 0F, 0F);
      Cube7 = new ModelRenderer(this, 0, 28);
      Cube7.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube7.setRotationPoint(2F, 10F, 2F);
      Cube7.setTextureSize(64, 32);
      Cube7.mirror = true;
      setRotation(Cube7, 0F, 0F, 0F);
      Cube8 = new ModelRenderer(this, 0, 28);
      Cube8.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube8.setRotationPoint(2F, 10F, 0F);
      Cube8.setTextureSize(64, 32);
      Cube8.mirror = true;
      setRotation(Cube8, 0F, 0F, 0F);
      Cube9 = new ModelRenderer(this, 0, 28);
      Cube9.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube9.setRotationPoint(2F, 10F, -2F);
      Cube9.setTextureSize(64, 32);
      Cube9.mirror = true;
      setRotation(Cube9, 0F, 0F, 0F);
      Cube10 = new ModelRenderer(this, 0, 28);
      Cube10.addBox(-1F, 0F, -1F, 2, 2, 2);
      Cube10.setRotationPoint(0F, 8F, 0F);
      Cube10.setTextureSize(64, 32);
      Cube10.mirror = true;
      setRotation(Cube10, 0F, 0F, 0F);
  }
  
  public void render(TileEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(null, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, null);
    Base.render(f5);
    StandTop.render(f5);
    Top_Base.render(f5);
    Top_Left.render(f5);
    Top_Right.render(f5);
    Top_Back.render(f5);
    Top_Front.render(f5);
    Stand.render(f5);
    int jewels = ((TileEntityPedestal)entity).getJewels();
    ModelRenderer[] array = {Cube1, Cube2, Cube3, Cube4, Cube5, Cube6, Cube7, Cube8, Cube9, Cube10};
    for(int i = 0; i < jewels; i++){
    	array[i].render(f5);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
