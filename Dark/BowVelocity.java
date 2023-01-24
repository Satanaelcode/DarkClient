/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*    */ import meteordevelopment.meteorclient.settings.BoolSetting;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_2848;
/*    */ import net.minecraft.class_310;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BowVelocity
/*    */   extends Module
/*    */ {
/* 28 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/* 29 */   public final Setting<Boolean> auto = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/* 30 */       .name("AutoDraw"))
/* 31 */       .description("Automatically draws your bow."))
/* 32 */       .defaultValue(Boolean.valueOf(false)))
/* 33 */       .build());
/*    */   
/* 35 */   public static final Logger LOGGER = LogManager.getLogger("instantkill");
/*    */   
/* 37 */   public static final class_310 mc = class_310.method_1551();
/*    */   public static boolean shouldAddVelocity = true;
/*    */   public static boolean shouldAddVelocity1 = false;
/*    */   public static boolean shouldAddVelocity2 = false;
/*    */   public static boolean shouldAddVelocity3 = false;
/*    */   public static boolean shouldAddVelocity0 = false;
/*    */   
/*    */   public BowVelocity() {
/* 45 */     super(Categories.Dark, "BowVelocity", "Uses velocity to make bows do more damage");
/*    */   }
/*    */   @EventHandler
/*    */   public void onTick(TickEvent.Post event) {
/* 49 */     if (((Boolean)this.auto.get()).booleanValue() && mc.field_1724.method_6047().method_7909() == class_1802.field_8102 && 
/* 50 */       !mc.field_1724.method_6115()) {
/* 51 */       mc.field_1690.field_1904.method_23481(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeactivate() {
/* 57 */     mc.field_1690.field_1904.method_23481(false);
/*    */   }
/*    */   @EventHandler
/*    */   public void onInitialize() {
/* 61 */     LOGGER.info("the instant kill is real working");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addVelocityToPlayer() {
/* 67 */     if (shouldAddVelocity) {
/* 68 */       mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 69 */       for (int i = 0; i < 100; i++) {
/* 70 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.0E-9D, mc.field_1724.method_23321(), true));
/* 71 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9D, mc.field_1724.method_23321(), false));
/*    */       } 
/*    */     } 
/* 74 */     if (shouldAddVelocity1) {
/* 75 */       mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 76 */       for (int i = 0; i < 150; i++) {
/* 77 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.0E-9D, mc.field_1724.method_23321(), true));
/* 78 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9D, mc.field_1724.method_23321(), false));
/*    */       } 
/*    */     } 
/* 81 */     if (shouldAddVelocity2) {
/* 82 */       mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 83 */       for (int i = 0; i < 200; i++) {
/* 84 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.0E-9D, mc.field_1724.method_23321(), true));
/* 85 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9D, mc.field_1724.method_23321(), false));
/*    */       } 
/*    */     } 
/* 88 */     if (shouldAddVelocity3) {
/* 89 */       mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 90 */       for (int i = 0; i < 300; i++) {
/* 91 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.0E-9D, mc.field_1724.method_23321(), true));
/* 92 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9D, mc.field_1724.method_23321(), false));
/*    */       } 
/*    */     } 
/* 95 */     if (shouldAddVelocity0) {
/* 96 */       mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 97 */       for (int i = 0; i < 50; i++) {
/* 98 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 1.0E-9D, mc.field_1724.method_23321(), true));
/* 99 */         mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0E-9D, mc.field_1724.method_23321(), false));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\BowVelocity.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */