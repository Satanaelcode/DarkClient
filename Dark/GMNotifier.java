/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.packets.PacketEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1934;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2703;
/*    */ import net.minecraft.class_640;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GMNotifier
/*    */   extends Module
/*    */ {
/*    */   public GMNotifier() {
/* 21 */     super(Categories.Dark, "GM-notifier", "Tells you if someone switches GM");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacket(PacketEvent.Receive event) {
/* 26 */     class_2596 class_2596 = event.packet; if (class_2596 instanceof class_2703) { class_2703 packet = (class_2703)class_2596;
/* 27 */       for (class_2703.class_2705 gamemode : packet.method_46329()) {
/* 28 */         if (!packet.method_46327().contains(class_2703.class_5893.field_29137))
/* 29 */           continue;  class_640 entry1 = this.mc.method_1562().method_2871(gamemode.comp_1106());
/* 30 */         if (entry1 == null)
/* 31 */           continue;  class_1934 gameMode = gamemode.comp_1110();
/* 32 */         if (entry1.method_2958() != gameMode)
/* 33 */           info("Player %s changed gamemode to %s", new Object[] { entry1.method_2966().getName(), gamemode.comp_1110() }); 
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\GMNotifier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */