package com.feedthebeast.virtualchest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import net.minecraftforge.common.Configuration;

public class Configs {
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface CfgId {
		public  boolean block() default false;
		public String comment() default "";
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface CfgBool {public String comment() default "";}
	
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface CfgInteger {public String comment() default "";}
	
	
	
	
	
	// Blocks!
	public static @CfgId(block=true,comment="Block ID for Virtual Chest") int VirtualChestId=500;
	public static @CfgId(block=true,comment="Block ID for Virtual Store") int VirtualStoreId=501;
	public static @CfgId(block=true,comment="Block ID for Virtual Tank") int VirtualTankId=502;
	
	public static void load(Configuration config)
	{
		try {
			config.load();
			Field[] fields=Configs.class.getFields();
			for(Field field:fields) {
				CfgId annotation=field.getAnnotation(CfgId.class);
				if(annotation!=null)
				{
					int id=field.getInt(null);
					if(annotation.block())
					{
						id=config.getBlock(field.getName(), id,annotation.comment()).getInt();
					}
					else
					{
						id=config.getItem(field.getName(), id, annotation.comment()).getInt();
					}
					field.setInt(null, id);
				}
				else
				{
					if(field.isAnnotationPresent(CfgBool.class))
					{
						CfgBool bAnnotation=field.getAnnotation(CfgBool.class);
						boolean bool=field.getBoolean(null);
						bool=config.get(Configuration.CATEGORY_GENERAL, field.getName(), bool,bAnnotation.comment()).getBoolean(bool);
						field.setBoolean(null, bool);
					}
					if(field.isAnnotationPresent(CfgInteger.class))
					{
						CfgInteger iAnnotation=field.getAnnotation(CfgInteger.class);
						int integer=field.getInt(null);
						integer=config.get(Configuration.CATEGORY_GENERAL, field.getName(), integer,iAnnotation.comment()).getInt(integer);
						field.setInt(null, integer);
					}
				}
			}
		} catch (Exception e)
		{
			// Failed, throw somethign here later
		}
		finally
		{
			config.save();
		}
	}
}
