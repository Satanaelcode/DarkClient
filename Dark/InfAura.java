/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import baritone.pathing.calc.AStarPathFinder;
/*    */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*    */ import java.util.Objects;
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.settings.DoubleSetting;
/*    */ import meteordevelopment.meteorclient.settings.EntityTypeListSetting;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.systems.friends.Friends;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_746;
/*    */ 
/*    */ public class InfAura extends Module {
/*    */   private final SettingGroup sgTargeting;
/*    */   
/*    */   public InfAura() {
/* 28 */     super(Categories.Dark, "InfAura", "Infinite Reach with kill aura");
/*    */ 
/*    */     
/* 31 */     this.sgTargeting = this.settings.createGroup("Targeting");
/*    */     
/* 33 */     this.entities = this.sgTargeting.add((Setting)((EntityTypeListSetting.Builder)((EntityTypeListSetting.Builder)(new EntityTypeListSetting.Builder())
/* 34 */         .name("entities"))
/* 35 */         .description("Entities to attack."))
/* 36 */         .onlyAttackable()
/* 37 */         .defaultValue(new class_1299[] { class_1299.field_6097
/* 38 */           }).build());
/*    */ 
/*    */     
/* 41 */     this.range = this.sgTargeting.add((Setting)((DoubleSetting.Builder)((DoubleSetting.Builder)(new DoubleSetting.Builder())
/* 42 */         .name("range"))
/* 43 */         .description("The maximum range the entity can be to attack it."))
/* 44 */         .defaultValue(4.5D)
/* 45 */         .min(0.0D)
/* 46 */         .sliderMax(6.0D)
/* 47 */         .build());
/*    */   }
/*    */   private final Setting<Object2BooleanMap<class_1299<?>>> entities; private final Setting<Double> range;
/*    */   @EventHandler
/*    */   private void ontick(TickEvent.Post tick) {
/* 52 */     if (this.mc.field_1724 != null) {
/* 53 */       for (class_1297 target : this.mc.field_1687.method_18112()) {
/* 54 */         if (((Object2BooleanMap)this.entities.get()).getBoolean(target.method_5864()) && 
/* 55 */           this.mc.field_1724.method_5739(target) < ((Double)this.range.get()).doubleValue() && 
/* 56 */           this.mc.field_1724 != null) {
/* 57 */           if (((class_746)Objects.<class_746>requireNonNull(this.mc.field_1724)).method_7261(0.0F) < 1.0F) {
/*    */             return;
/*    */           }
/* 60 */           assert this.mc.field_1761 != null;
/* 61 */           if (Friends.get().shouldAttack((class_1657)target)) {
/*    */             return;
/*    */           }
/* 64 */           teleportFromTo(this.mc, this.mc.field_1724.method_19538(), target.method_19538());
/* 65 */           this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, target);
/* 66 */           teleportFromTo(this.mc, target.method_19538(), this.mc.field_1724.method_19538());
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void teleportFromTo(class_310 mc, class_243 fromPos, class_243 toPos) {
/* 75 */     double distancePerBlock = 8.5D;
/* 76 */     double targetDistance = Math.ceil(fromPos.method_1022(toPos) / distancePerBlock);
/* 77 */     for (int i = 1; i <= targetDistance; i++) {
/* 78 */       class_243 tempPos = fromPos.method_35590(toPos, i / targetDistance);
/* 79 */       AStarPathFinder.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(tempPos.field_1352, tempPos.field_1351, tempPos.field_1350, true));
/*    */       
/* 81 */       if (i % 4 == 0)
/*    */         try {
/* 83 */           Thread.sleep(0L);
/*    */         }
/* 85 */         catch (InterruptedException interruptedException) {} 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\InfAura.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */