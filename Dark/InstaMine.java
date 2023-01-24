/*     */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*     */ import meteordevelopment.meteorclient.events.entity.player.StartBreakingBlockEvent;
/*     */ import meteordevelopment.meteorclient.events.render.Render3DEvent;
/*     */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*     */ import meteordevelopment.meteorclient.renderer.ShapeMode;
/*     */ import meteordevelopment.meteorclient.settings.BoolSetting;
/*     */ import meteordevelopment.meteorclient.settings.ColorSetting;
/*     */ import meteordevelopment.meteorclient.settings.EnumSetting;
/*     */ import meteordevelopment.meteorclient.settings.IntSetting;
/*     */ import meteordevelopment.meteorclient.settings.Setting;
/*     */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*     */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*     */ import meteordevelopment.meteorclient.systems.modules.Module;
/*     */ import meteordevelopment.meteorclient.utils.player.Rotations;
/*     */ import meteordevelopment.meteorclient.utils.render.color.Color;
/*     */ import meteordevelopment.meteorclient.utils.render.color.SettingColor;
/*     */ import meteordevelopment.meteorclient.utils.world.BlockUtils;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2846;
/*     */ 
/*     */ public class InstaMine extends Module {
/*  28 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*  29 */   private final SettingGroup sgRender = this.settings.createGroup("Render");
/*     */   
/*  31 */   private final Setting<Integer> tickDelay = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  32 */       .name("delay"))
/*  33 */       .description("The delay between breaks."))
/*  34 */       .defaultValue(Integer.valueOf(0)))
/*  35 */       .min(0)
/*  36 */       .sliderMax(20)
/*  37 */       .build());
/*     */ 
/*     */   
/*  40 */   private final Setting<Boolean> pick = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/*  41 */       .name("only-pick"))
/*  42 */       .description("Only tries to mine the block if you are holding a pickaxe."))
/*  43 */       .defaultValue(Boolean.valueOf(true)))
/*  44 */       .build());
/*     */ 
/*     */   
/*  47 */   private final Setting<Boolean> rotate = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/*  48 */       .name("rotate"))
/*  49 */       .description("Faces the blocks being mined server side."))
/*  50 */       .defaultValue(Boolean.valueOf(true)))
/*  51 */       .build());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private final Setting<Boolean> render = this.sgRender.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/*  57 */       .name("render"))
/*  58 */       .description("Renders a block overlay on the block being broken."))
/*  59 */       .defaultValue(Boolean.valueOf(true)))
/*  60 */       .build());
/*     */ 
/*     */   
/*  63 */   private final Setting<ShapeMode> shapeMode = this.sgRender.add((Setting)((EnumSetting.Builder)((EnumSetting.Builder)((EnumSetting.Builder)(new EnumSetting.Builder())
/*  64 */       .name("shape-mode"))
/*  65 */       .description("How the shapes are rendered."))
/*  66 */       .defaultValue(ShapeMode.Both))
/*  67 */       .build());
/*     */ 
/*     */   
/*  70 */   private final Setting<SettingColor> sideColor = this.sgRender.add((Setting)((ColorSetting.Builder)((ColorSetting.Builder)(new ColorSetting.Builder())
/*  71 */       .name("side-color"))
/*  72 */       .description("The color of the sides of the blocks being rendered."))
/*  73 */       .defaultValue(new SettingColor(204, 0, 0, 10))
/*  74 */       .build());
/*     */ 
/*     */   
/*  77 */   private final Setting<SettingColor> lineColor = this.sgRender.add((Setting)((ColorSetting.Builder)((ColorSetting.Builder)(new ColorSetting.Builder())
/*  78 */       .name("line-color"))
/*  79 */       .description("The color of the lines of the blocks being rendered."))
/*  80 */       .defaultValue(new SettingColor(204, 0, 0, 255))
/*  81 */       .build());
/*     */ 
/*     */   
/*     */   private int ticks;
/*     */   
/*  86 */   private final class_2338.class_2339 blockPos = new class_2338.class_2339(0, -1, 0);
/*     */   private class_2350 direction;
/*     */   
/*     */   public InstaMine() {
/*  90 */     super(Categories.Dark, "insta-mine", "Attempts to instantly mine blocks.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onActivate() {
/*  95 */     this.ticks = 0;
/*  96 */     this.blockPos.method_10103(0, -128, 0);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onStartBreakingBlock(StartBreakingBlockEvent event) {
/* 101 */     this.direction = event.direction;
/* 102 */     this.blockPos.method_10101((class_2382)event.blockPos);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onTick(TickEvent.Pre event) {
/* 107 */     if (this.ticks >= ((Integer)this.tickDelay.get()).intValue()) {
/* 108 */       this.ticks = 0;
/*     */       
/* 110 */       if (shouldMine()) {
/* 111 */         if (((Boolean)this.rotate.get()).booleanValue()) {
/* 112 */           Rotations.rotate(Rotations.getYaw((class_2338)this.blockPos), Rotations.getPitch((class_2338)this.blockPos), () -> this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, (class_2338)this.blockPos, this.direction)));
/*     */         } else {
/* 114 */           this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, (class_2338)this.blockPos, this.direction));
/*     */         } 
/*     */         
/* 117 */         this.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
/*     */       } 
/*     */     } else {
/*     */       
/* 121 */       this.ticks++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean shouldMine() {
/* 126 */     if (this.blockPos.method_10264() == -128) return false; 
/* 127 */     if (!BlockUtils.canBreak((class_2338)this.blockPos)) return false; 
/* 128 */     return (!((Boolean)this.pick.get()).booleanValue() || this.mc.field_1724.method_6047().method_7909() == class_1802.field_8377 || this.mc.field_1724.method_6047().method_7909() == class_1802.field_22024);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onRender(Render3DEvent event) {
/* 133 */     if (!((Boolean)this.render.get()).booleanValue() || !shouldMine())
/* 134 */       return;  event.renderer.box((class_2338)this.blockPos, (Color)this.sideColor.get(), (Color)this.lineColor.get(), (ShapeMode)this.shapeMode.get(), 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\InstaMine.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */