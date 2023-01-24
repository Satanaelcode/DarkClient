/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import baritone.pathing.calc.AStarPathFinder;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.settings.BoolSetting;
/*    */ import meteordevelopment.meteorclient.settings.DoubleSetting;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_746;
/*    */ import net.minecraft.class_863;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JReach
/*    */   extends Module
/*    */ {
/* 36 */   private Optional<class_1297> LastEntity = null;
/*    */   
/* 38 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*    */   
/* 40 */   public final Setting<Boolean> ArmSwing = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/* 41 */       .name("ArmSwing"))
/* 42 */       .description("Automatically draws your bow."))
/* 43 */       .defaultValue(Boolean.valueOf(false)))
/* 44 */       .build());
/*    */ 
/*    */   
/* 47 */   private final Setting<Double> distance = this.sgGeneral.add((Setting)((DoubleSetting.Builder)((DoubleSetting.Builder)(new DoubleSetting.Builder())
/* 48 */       .name("delta"))
/* 49 */       .description("the length of each teleport"))
/* 50 */       .defaultValue(8.5D)
/* 51 */       .min(2.0D)
/* 52 */       .max(10.0D)
/* 53 */       .build());
/*    */ 
/*    */   
/*    */   public JReach() {
/* 57 */     super(Categories.Dark, "Reach+", "More than 6 block reach");
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   private void onTick(TickEvent.Pre event) {
/* 63 */     Optional<class_1297> entity = class_863.method_23101((class_1297)this.mc.field_1724, 150);
/*    */     
/* 65 */     if (this.mc.field_1690.field_1886.method_1434()) {
/* 66 */       class_243 op = this.mc.field_1724.method_19538();
/* 67 */       if (entity.isPresent()) {
/* 68 */         if (((class_746)Objects.<class_746>requireNonNull(this.mc.field_1724)).method_7261(0.0F) < 1.0F) {
/*    */           return;
/*    */         }
/*    */         
/* 72 */         class_243 playerPos = this.mc.field_1724.method_19538();
/*    */         
/* 74 */         teleportFromTo(this.mc, playerPos, ((class_1297)entity.get()).method_19538());
/*    */         
/* 76 */         this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, entity.get());
/* 77 */         if (((Boolean)this.ArmSwing.get()).booleanValue()) this.mc.field_1724.method_6104(class_1268.field_5808); 
/* 78 */         this.LastEntity = entity;
/*    */         
/* 80 */         teleportFromTo(this.mc, ((class_1297)entity.get()).method_19538(), playerPos);
/*    */         
/* 82 */         AStarPathFinder.mc.field_1724.method_33574(playerPos);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void teleportFromTo(class_310 mc, class_243 fromPos, class_243 toPos) {
/* 88 */     double distancePerBlock = ((Double)this.distance.get()).doubleValue();
/* 89 */     double targetDistance = Math.ceil(fromPos.method_1022(toPos) / distancePerBlock);
/* 90 */     for (int i = 1; i <= targetDistance; i++) {
/* 91 */       class_243 tempPos = fromPos.method_35590(toPos, i / targetDistance);
/* 92 */       AStarPathFinder.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(tempPos.field_1352, tempPos.field_1351, tempPos.field_1350, true));
/*    */       
/* 94 */       if (i % 4 == 0)
/*    */         try {
/* 96 */           Thread.sleep(0L);
/*    */         }
/* 98 */         catch (InterruptedException interruptedException) {} 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\JReach.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */