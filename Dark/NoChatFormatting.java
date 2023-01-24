/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2561;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoChatFormatting
/*    */   extends Module
/*    */ {
/*    */   public NoChatFormatting() {
/* 16 */     super(Categories.Dark, "NoChatFormatting", "Stops Chat Colour Codes");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void onRevieve(ReceiveMessageEvent event) {
/* 21 */     event.setMessage((class_2561)class_2561.method_43470(event.getMessage().getString()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\NoChatFormatting.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */