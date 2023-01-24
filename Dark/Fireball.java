/*     */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*     */ 
/*     */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*     */ import meteordevelopment.meteorclient.settings.EnumSetting;
/*     */ import meteordevelopment.meteorclient.settings.IntSetting;
/*     */ import meteordevelopment.meteorclient.settings.Setting;
/*     */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*     */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*     */ import meteordevelopment.meteorclient.systems.modules.Module;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2487;
/*     */ import net.minecraft.class_2489;
/*     */ import net.minecraft.class_2499;
/*     */ import net.minecraft.class_2520;
/*     */ import net.minecraft.class_3965;
/*     */ 
/*     */ 
/*     */ public class Fireball
/*     */   extends Module
/*     */ {
/*  29 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*  30 */   private final Setting<Modes> mode = this.sgGeneral.add((Setting)((EnumSetting.Builder)((EnumSetting.Builder)((EnumSetting.Builder)(new EnumSetting.Builder())
/*  31 */       .name("mode"))
/*  32 */       .description("the mode"))
/*  33 */       .defaultValue(Modes.Instant))
/*  34 */       .build());
/*     */   
/*  36 */   private final Setting<Integer> power = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  37 */       .name("power"))
/*  38 */       .description("How big the explosions are"))
/*  39 */       .defaultValue(Integer.valueOf(15)))
/*  40 */       .min(1)
/*  41 */       .sliderMax(200)
/*  42 */       .build());
/*     */   
/*  44 */   private final Setting<Integer> speed = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  45 */       .name("speed"))
/*  46 */       .description("How fast the fireball moves"))
/*  47 */       .defaultValue(Integer.valueOf(15)))
/*  48 */       .min(1)
/*  49 */       .sliderMax(200)
/*  50 */       .build());
/*     */   
/*     */   public Fireball() {
/*  53 */     super(Categories.Dark, "FireballClicker", "Shoots fireballs where you click");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onActivate() {
/*  58 */     if (!(this.mc.field_1724.method_31549()).field_7477) {
/*  59 */       info("You must be in creative mode", new Object[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onclick(TickEvent.Pre event) {
/*  65 */     if (this.mc.field_1690.field_1886.method_1434() && this.mc.field_1755 == null) {
/*  66 */       class_243 posrot2; class_1799 Motion, Instant; class_2487 tag, class_24871; class_2499 motion, Pos, class_24991; class_239 hr = this.mc.field_1719.method_5745(600.0D, 0.0F, true);
/*  67 */       class_1799 rst = this.mc.field_1724.method_6047();
/*  68 */       class_243 posrot = this.mc.field_1724.method_5720().method_1021(((Integer)this.speed.get()).intValue());
/*  69 */       class_3965 bhr = new class_3965(this.mc.field_1724.method_33571(), class_2350.field_11033, new class_2338(this.mc.field_1724.method_33571()), false);
/*  70 */       class_243 blockpos = hr.method_17784();
/*  71 */       class_2338 pos = new class_2338(blockpos);
/*  72 */       switch ((Modes)this.mode.get()) {
/*     */         case Instant:
/*  74 */           posrot2 = this.mc.field_1724.method_5720().method_1021(100.0D);
/*  75 */           Instant = new class_1799((class_1935)class_1802.field_8374);
/*  76 */           class_24871 = new class_2487();
/*  77 */           Pos = new class_2499();
/*  78 */           class_24991 = new class_2499();
/*  79 */           Pos.add(class_2489.method_23241(pos.method_10263()));
/*  80 */           Pos.add(class_2489.method_23241(pos.method_10264()));
/*  81 */           Pos.add(class_2489.method_23241(pos.method_10260()));
/*  82 */           class_24991.add(class_2489.method_23241(posrot2.field_1352));
/*  83 */           class_24991.add(class_2489.method_23241(posrot2.field_1351));
/*  84 */           class_24991.add(class_2489.method_23241(posrot2.field_1350));
/*  85 */           class_24871.method_10566("Pos", (class_2520)Pos);
/*  86 */           class_24871.method_10566("Motion", (class_2520)class_24991);
/*  87 */           class_24871.method_10569("ExplosionPower", ((Integer)this.power.get()).intValue());
/*  88 */           class_24871.method_10582("id", "minecraft:fireball");
/*  89 */           Instant.method_7959("EntityTag", (class_2520)class_24871);
/*  90 */           this.mc.field_1761.method_2909(Instant, 36 + (this.mc.field_1724.method_31548()).field_7545);
/*  91 */           this.mc.field_1761.method_2896(this.mc.field_1724, class_1268.field_5808, bhr);
/*  92 */           this.mc.field_1761.method_2909(rst, 36 + (this.mc.field_1724.method_31548()).field_7545);
/*     */           break;
/*     */         case fireball:
/*  95 */           Motion = new class_1799((class_1935)class_1802.field_8374);
/*  96 */           tag = new class_2487();
/*  97 */           motion = new class_2499();
/*  98 */           motion.add(class_2489.method_23241(posrot.field_1352));
/*  99 */           motion.add(class_2489.method_23241(posrot.field_1351));
/* 100 */           motion.add(class_2489.method_23241(posrot.field_1350));
/* 101 */           tag.method_10566("Motion", (class_2520)motion);
/* 102 */           tag.method_10569("ExplosionPower", ((Integer)this.power.get()).intValue());
/* 103 */           tag.method_10582("id", "minecraft:fireball");
/* 104 */           Motion.method_7959("EntityTag", (class_2520)tag);
/* 105 */           this.mc.field_1761.method_2909(Motion, 36 + (this.mc.field_1724.method_31548()).field_7545);
/* 106 */           this.mc.field_1761.method_2896(this.mc.field_1724, class_1268.field_5808, bhr);
/* 107 */           this.mc.field_1761.method_2909(rst, 36 + (this.mc.field_1724.method_31548()).field_7545);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Modes {
/* 114 */     Instant, fireball;
/*     */   }
/*     */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\Fireball.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */