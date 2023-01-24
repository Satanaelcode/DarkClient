/*     */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import meteordevelopment.meteorclient.events.game.GameLeftEvent;
/*     */ import meteordevelopment.meteorclient.events.world.TickEvent;
/*     */ import meteordevelopment.meteorclient.settings.BoolSetting;
/*     */ import meteordevelopment.meteorclient.settings.IntSetting;
/*     */ import meteordevelopment.meteorclient.settings.Setting;
/*     */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*     */ import meteordevelopment.meteorclient.settings.StringSetting;
/*     */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*     */ import meteordevelopment.meteorclient.systems.modules.Module;
/*     */ import meteordevelopment.meteorclient.utils.Utils;
/*     */ import meteordevelopment.meteorclient.utils.player.ChatUtils;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MessageLagger
/*     */   extends Module
/*     */ {
/*  24 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/*     */   
/*  26 */   private final Setting<String> bc = this.sgGeneral.add((Setting)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)(new StringSetting.Builder()).name("Command")).description("Bypass Command")).defaultValue("/skill")).build());
/*     */   
/*  28 */   private final Setting<Integer> messageLength = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder())
/*  29 */       .name("message-length"))
/*  30 */       .description("The length of the message."))
/*  31 */       .defaultValue(Integer.valueOf(200)))
/*  32 */       .min(1)
/*  33 */       .sliderMin(1)
/*  34 */       .sliderMax(1000)
/*  35 */       .build());
/*     */   
/*  37 */   private final Setting<Boolean> keepSending = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder())
/*  38 */       .name("keep-sending"))
/*  39 */       .description("Keeps sending the lag messages repeatedly."))
/*  40 */       .defaultValue(Boolean.valueOf(false)))
/*  41 */       .build());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Integer> delay;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> whisper;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<String> wc;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<String> wp;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Setting<Boolean> autoDisable;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int timer;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageLagger() {
/*  77 */     super(Categories.Dark, "message-lagger", "Sends dense messages that lag other players on the server."); Objects.requireNonNull(this.keepSending);
/*     */     this.delay = this.sgGeneral.add((Setting)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)((IntSetting.Builder)(new IntSetting.Builder()).name("delay")).description("The delay between lag messages in ticks.")).defaultValue(Integer.valueOf(100))).min(0).sliderMax(1000).visible(this.keepSending::get)).build());
/*     */     this.whisper = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder()).name("whisper")).description("Whispers the lag message to a random person on the server.")).defaultValue(Boolean.valueOf(false))).build());
/*     */     Objects.requireNonNull(this.whisper);
/*     */     this.wc = this.sgGeneral.add((Setting)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)(new StringSetting.Builder()).name("Whisper Command")).description("Whisper Command")).defaultValue("/msg")).visible(this.whisper::get)).build());
/*     */     Objects.requireNonNull(this.whisper);
/*     */     this.wp = this.sgGeneral.add((Setting)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)((StringSetting.Builder)(new StringSetting.Builder()).name("Whisper player")).description("Player to Whisper to")).defaultValue("Notch")).visible(this.whisper::get)).build());
/*  84 */     this.autoDisable = this.sgGeneral.add((Setting)((BoolSetting.Builder)((BoolSetting.Builder)((BoolSetting.Builder)(new BoolSetting.Builder()).name("auto-disable")).description("Disables module on kick.")).defaultValue(Boolean.valueOf(true))).build()); } public void onActivate() { if (Utils.canUpdate() && !((Boolean)this.keepSending.get()).booleanValue()) {
/*  85 */       if (!((Boolean)this.whisper.get()).booleanValue()) {
/*  86 */         sendLagMessage();
/*     */       } else {
/*     */         
/*  89 */         sendLagWhisper();
/*     */       } 
/*  91 */       toggle();
/*     */     } 
/*  93 */     if (((Boolean)this.keepSending.get()).booleanValue()) this.timer = ((Integer)this.delay.get()).intValue();  }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onTick(TickEvent.Pre event) {
/*  98 */     if (this.timer <= 0) {
/*  99 */       if (Utils.canUpdate() && ((Boolean)this.keepSending.get()).booleanValue()) {
/* 100 */         if (!((Boolean)this.whisper.get()).booleanValue()) {
/* 101 */           sendLagMessage();
/*     */         } else {
/*     */           
/* 104 */           sendLagWhisper();
/*     */         } 
/*     */       }
/* 107 */       this.timer = ((Integer)this.delay.get()).intValue();
/*     */     } else {
/*     */       
/* 110 */       this.timer--;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onGameLeft(GameLeftEvent event) {
/* 116 */     if (((Boolean)this.autoDisable.get()).booleanValue() && isActive()) toggle(); 
/*     */   }
/*     */   
/*     */   private void sendLagMessage() {
/* 120 */     for (int i = 0; i < 20; i++) {
/* 121 */       ChatUtils.sendPlayerMsg("" + this.bc + " " + this.bc);
/*     */     }
/*     */   }
/*     */   
/*     */   private void sendLagWhisper() {
/* 126 */     String message = generateLagMessage();
/*     */     
/* 128 */     ChatUtils.sendPlayerMsg("" + this.wc + " " + this.wc + " " + this.wp);
/*     */   }
/*     */   
/*     */   private String generateLagMessage() {
/* 132 */     String message = " ";
/* 133 */     for (int i = 0; i < ((Integer)this.messageLength.get()).intValue(); i++) {
/* 134 */       message = message + message;
/*     */     }
/* 136 */     return message;
/*     */   }
/*     */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\MessageLagger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */