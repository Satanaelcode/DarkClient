/*    */ package meteordevelopment.meteorclient.systems.modules.Dark;
/*    */ 
/*    */ import java.util.List;
/*    */ import meteordevelopment.meteorclient.events.packets.PacketEvent;
/*    */ import meteordevelopment.meteorclient.settings.Setting;
/*    */ import meteordevelopment.meteorclient.settings.SettingGroup;
/*    */ import meteordevelopment.meteorclient.settings.StringListSetting;
/*    */ import meteordevelopment.meteorclient.systems.modules.Categories;
/*    */ import meteordevelopment.meteorclient.systems.modules.Module;
/*    */ import meteordevelopment.meteorclient.utils.Utils;
/*    */ import meteordevelopment.meteorclient.utils.player.ChatUtils;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2663;
/*    */ 
/*    */ 
/*    */ public class AutoL
/*    */   extends Module
/*    */ {
/* 23 */   private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
/* 24 */   private final Setting<List<String>> messages = this.sgGeneral.add((Setting)((StringListSetting.Builder)((StringListSetting.Builder)((StringListSetting.Builder)(new StringListSetting.Builder())
/* 25 */       .name("Messages"))
/* 26 */       .description("Messages to send when killing enemy"))
/* 27 */       .defaultValue(List.of("L (target)", "bozo (target)")))
/* 28 */       .build());
/*    */ 
/*    */   
/*    */   public AutoL() {
/* 32 */     super(Categories.Dark, "AutoL", "Disgraceful");
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void OnPacket(PacketEvent.Receive event) {
/* 37 */     class_2596 class_2596 = event.packet; if (class_2596 instanceof class_2663) { class_2663 packet = (class_2663)class_2596;
/* 38 */       class_1297 class_1297 = packet.method_11469((class_1937)this.mc.field_1687); if (class_1297 instanceof class_1657) { class_1657 player = (class_1657)class_1297;
/* 39 */         if (player.method_6032() <= 0.0F) {
/* 40 */           if (player.equals(this.mc.field_1724))
/* 41 */             return;  String text = ((List<String>)this.messages.get()).get(Utils.random(0, ((List)this.messages.get()).size()));
/*    */ 
/*    */           
/* 44 */           text = text.replace("(target)", player.method_5820());
/* 45 */           text = text.replace("(target.coords)", player.method_24515().toString());
/*    */           
/* 47 */           ChatUtils.sendPlayerMsg(text);
/*    */         }  }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Shees\Downloads\DarkClient.jar!\meteordevelopment\meteorclient\systems\modules\Dark\AutoL.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */