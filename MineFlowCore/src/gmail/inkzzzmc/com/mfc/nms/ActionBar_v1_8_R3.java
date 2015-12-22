package gmail.inkzzzmc.com.mfc.nms;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar_v1_8_R3 implements ActionBar {

    @Override
    public void send(Player player, String message) {
        CraftPlayer cPlayer = (CraftPlayer) player;
        IChatBaseComponent craftMessage = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(craftMessage, (byte) 2);
        cPlayer.getHandle().playerConnection.sendPacket(packet);
    }

}
