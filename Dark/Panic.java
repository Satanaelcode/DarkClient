/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.meteorclient.systems.modules.Modules;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Panic
/*    */   extends Module
/*    */ {
/*    */   public Panic() {
/* 15 */     super(Categories.Dark, "Panic", "Disables all modules");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onActivate() {
/* 20 */     for (Module module : Modules.get().getActive()) {
/* 21 */       module.toggle();
/*    */     }
/* 23 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\Panic.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */