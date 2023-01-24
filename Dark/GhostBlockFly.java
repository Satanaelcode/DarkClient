/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ 
/*    */ 
/*    */ public class GhostBlockFly
/*    */   extends Module
/*    */ {
/*    */   class_2680 state;
/*    */   class_2338 pos;
/*    */   
/*    */   public GhostBlockFly() {
/* 19 */     super(Categories.Dark, "GhostBlockFly", "Fly Using Ghost Blocks");
/*    */ 
/*    */     
/* 22 */     this.state = null;
/* 23 */     this.pos = null;
/*    */   }
/*    */   @EventHandler
/*    */   public void onTick(TickEvent.Post event) {
/* 27 */     assert this.mc.field_1687 != null;
/* 28 */     assert this.mc.field_1724 != null;
/* 29 */     if (this.mc.field_1724.method_24515().method_10069(0, -1, 0) != this.pos && this.pos != null && this.state != null) {
/* 30 */       this.mc.field_1687.method_8501(this.pos, this.state);
/*    */     }
/*    */     
/* 33 */     this.pos = this.mc.field_1724.method_24515().method_10069(0, -1, 0);
/* 34 */     this.state = this.mc.field_1687.method_8320(this.pos);
/*    */     
/* 36 */     if (!this.mc.field_1690.field_1832.method_1434() && this.mc.field_1687.method_8320(this.pos).method_26204() instanceof net.minecraft.class_2189 && this.pos != null) {
/* 37 */       this.mc.field_1687.method_8501(this.pos, class_2246.field_10499.method_9564());
/*    */     }
/*    */     
/* 40 */     if (this.mc.field_1690.field_1832.method_1434() && this.mc.field_1687.method_8320(this.pos).method_26204() instanceof net.minecraft.class_2189 && this.pos != null) {
/* 41 */       this.mc.field_1687.method_8501(this.pos, class_2246.field_10124.method_9564());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeactivate() {
/* 47 */     assert this.mc.field_1687 != null;
/* 48 */     this.mc.field_1687.method_8501(this.pos, this.state);
/* 49 */     this.pos = null;
/* 50 */     class_2680 state = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\GhostBlockFly.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */