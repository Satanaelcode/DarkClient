/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.settings.IntSetting;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.settings.StringSetting;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.meteorclient.utils.player.ChatUtils;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ 
/*    */ public class SpamBypass extends Module {
/* 13 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*    */   
/* 15 */   private final Setting<String> bc = this.sgGeneral.add((Setting)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)(new StringSetting.Builder()).name("Command")).description("Bypass Command")).defaultValue("/skill")).build());
/*    */   
/* 17 */   private final Setting<String> message = this.sgGeneral.add((Setting)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)(new StringSetting.Builder()).name("Message")).description("Message To Spam")).defaultValue("Jinx")).build());
/*    */   
/* 19 */   private final Setting<Integer> amount = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/* 20 */       .name("amount"))
/* 21 */       .description("The amount of messages to send per tick"))
/* 22 */       .defaultValue(Integer.valueOf(10)))
/* 23 */       .min(1)
/* 24 */       .sliderMax(20)
/* 25 */       .build());
/*    */   
/*    */   private int timer;
/*    */ 
/*    */   
/*    */   public SpamBypass() {
/* 31 */     super(Categories.Dark, "Spam-Bypass", "Better than spam.");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void onTick(TickEvent.Post event) {
/* 36 */     if (this.timer < 0) {
/* 37 */       ChatUtils.sendPlayerMsg("" + this.bc + " " + this.bc);
/* 38 */       this.timer = 20;
/*    */     } 
/* 40 */     if (this.timer == 0) {
/* 41 */       for (int i = 0; i < ((Integer)this.amount.get()).intValue(); i++) {
/* 42 */         ChatUtils.sendPlayerMsg("" + this.bc + " " + this.bc);
/*    */       }
/*    */     } else {
/* 45 */       this.timer--;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\SpamBypass.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */