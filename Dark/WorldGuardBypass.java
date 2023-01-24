/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_746;
/*    */ 
/*    */ public class WorldGuardBypass
/*    */   extends Module
/*    */ {
/*    */   class_746 player;
/*    */   
/*    */   public WorldGuardBypass() {
/* 18 */     super(Categories.Dark, "WG-Bypass", "Bypass world guard");
/*    */ 
/*    */     
/* 21 */     this.player = this.mc.field_1724;
/*    */   }
/*    */   @EventHandler
/*    */   private void onTick(TickEvent.Pre event) {
/* 25 */     double hspeed = 0.0625D;
/* 26 */     double vspeed = 0.0625D;
/*    */     
/* 28 */     class_243 forward = (new class_243(0.0D, 0.0D, hspeed)).method_1024(-((float)Math.toRadians((Math.round(this.mc.field_1724.method_36454() / 90.0F) * 90))));
/* 29 */     class_243 moveVec = class_243.field_1353;
/*    */ 
/*    */     
/* 32 */     if (this.mc.field_1690.field_1894.method_1434()) {
/* 33 */       moveVec = moveVec.method_1019(forward);
/* 34 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/* 35 */     } else if (this.mc.field_1690.field_1881.method_1434()) {
/* 36 */       moveVec = moveVec.method_1019(forward.method_22882());
/* 37 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/*    */     
/*    */     }
/* 40 */     else if (this.mc.field_1690.field_1913.method_1434()) {
/* 41 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/* 42 */       moveVec = moveVec.method_1019(forward.method_1024((float)Math.toRadians(90.0D)));
/* 43 */     } else if (this.mc.field_1690.field_1849.method_1434()) {
/* 44 */       moveVec = moveVec.method_1019(forward.method_1024((float)-Math.toRadians(90.0D)));
/* 45 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/*    */     
/*    */     }
/* 48 */     else if (this.mc.field_1690.field_1903.method_1434()) {
/* 49 */       moveVec = moveVec.method_1031(0.0D, vspeed, 0.0D);
/* 50 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/* 51 */     } else if (this.mc.field_1690.field_1832.method_1434()) {
/* 52 */       moveVec = moveVec.method_1031(0.0D, -vspeed, 0.0D);
/* 53 */       this.mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 56 */     this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724
/* 57 */           .method_23317() + moveVec.field_1352, this.mc.field_1724.method_23318() + moveVec.field_1351, this.mc.field_1724.method_23321() + moveVec.field_1350, false));
/*    */     
/* 59 */     this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(this.mc.field_1724
/* 60 */           .method_23317() + moveVec.field_1352, this.mc.field_1724.method_23318() - 100.0D, this.mc.field_1724.method_23321() + moveVec.field_1350, true));
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\WorldGuardBypass.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */