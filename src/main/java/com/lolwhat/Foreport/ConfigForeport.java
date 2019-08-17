package com.lolwhat.Foreport;

import net.minecraftforge.common.config.Config;

@Config(modid = Foreport.MODID)
public class ConfigForeport {
	public enum BlockToasts {
		NONE("None"),
		ONLY_RECIPE("Only recipe toasts"),
		ONLY_RECIPE_AND_TUTORIAL("Only recipe and tutorial toasts"),
		ALL_EXCEPT_ADVANCEMENT("All except advancement toasts"),
		ALL("All");
		private String name;
		private BlockToasts(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	@Config.LangKey("foreport.config.block_toasts")
	public static BlockToasts blockToasts = BlockToasts.ALL_EXCEPT_ADVANCEMENT;
	
	@Config.LangKey("foreport.config.disable_recipe_book")
	@Config.Comment("If set to true, the recipe book introduced in 1.12 will be disabled.")
	public static boolean disableRecipeBook = true;
	
	@Config.LangKey("foreport.config.fix_walking_backwards")
	@Config.Comment("If set to true, the pre-1.12 animation for walking backwards will be used.")
	public static boolean fixWalkingBackwards = true;
	
	@Config.LangKey("foreport.config.fix_sneaking")
	@Config.Comment("If set to true, the pre-1.12 sneaking animation will be used.")
	public static boolean fixSneaking = true;
}
