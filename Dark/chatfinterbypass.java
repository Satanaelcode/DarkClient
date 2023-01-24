/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.game.SendMessageEvent;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class chatfinterbypass
/*    */   extends Module
/*    */ {
/*    */   public chatfinterbypass() {
/* 15 */     super(Categories.Dark, "Chat Filter Bypass", "Bypasses Chat Filters");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void OnMessage(SendMessageEvent event) {
/* 20 */     String message = event.message;
/* 21 */     message = message.replace("a", "a‌");
/* 22 */     message = message.replace("e", "e‌");
/* 23 */     message = message.replace("i", "i‌");
/* 24 */     message = message.replace("o", "o‌");
/* 25 */     message = message.replace("u", "u‌");
/* 26 */     event.message = message;
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\chatfinterbypass.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */