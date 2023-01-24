/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.settings.DoubleSetting;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpamKiller
/*    */   extends Module
/*    */ {
/*    */   int Timer;
/* 20 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*    */   
/* 22 */   private final Setting<Double> velo = this.sgGeneral.add((Setting)((DoubleSetting.Builder)((DoubleSetting.Builder)(new DoubleSetting.Builder())
/* 23 */       .name("velo"))
/* 24 */       .description("the velocity you are thrown at"))
/* 25 */       .defaultValue(1.0D)
/* 26 */       .min(0.1D)
/* 27 */       .build());
/*    */ 
/*    */   
/* 30 */   private final Setting<Double> Timerc = this.sgGeneral.add((Setting)((DoubleSetting.Builder)((DoubleSetting.Builder)(new DoubleSetting.Builder())
/* 31 */       .name("timer"))
/* 32 */       .description(""))
/* 33 */       .defaultValue(10.0D)
/* 34 */       .min(1.0D)
/* 35 */       .build());
/*    */ 
/*    */   
/*    */   public SpamKiller() {
/* 39 */     super(Categories.Dark, "Kill-O-Matic", "you fell from a high place");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onActivate() {
/* 44 */     this.Timer = ((Double)this.Timerc.get()).intValue();
/*    */     
/* 46 */     this.mc.field_1724.method_18800(0.0D, ((Double)this.velo.get()).doubleValue(), 0.0D);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void onTick(TickEvent.Pre event) {
/* 51 */     if (this.Timer <= 0) {
/* 52 */       this.mc.field_1724.method_18800(0.0D, -((Double)this.velo.get()).doubleValue(), 0.0D);
/* 53 */       this.Timer = ((Double)this.Timerc.get()).intValue();
/* 54 */       toggle();
/*    */     } else {
/* 56 */       this.Timer--;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\SpamKiller.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */