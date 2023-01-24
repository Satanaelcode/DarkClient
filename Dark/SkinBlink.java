/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import java.util.Random;
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1664;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkinBlink
/*    */   extends Module
/*    */ {
/*    */   public SkinBlink() {
/* 20 */     super(Categories.Dark, "Skin Blinker", "Toggles skin layers at random");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void onTick(TickEvent.Pre event) {
/* 25 */     Random rand = new Random();
/*    */     
/* 27 */     if (rand.nextInt(4) != 0) {
/*    */       return;
/*    */     }
/* 30 */     for (class_1664 part : class_1664.values())
/* 31 */       this.mc.field_1690.method_1631(part, !this.mc.field_1690.method_32594(part)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\SkinBlink.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */