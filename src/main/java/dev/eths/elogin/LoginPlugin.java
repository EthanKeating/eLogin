package dev.eths.elogin;

import com.github.retrooper.packetevents.PacketEvents;
import dev.eths.elogin.packet.HandshakePacketListener;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginPlugin extends JavaPlugin {

    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().checkForUpdates(false).bStats(false);
        PacketEvents.getAPI().load();
    }

    public void onEnable() {
        PacketEvents.getAPI().init();
        PacketEvents.getAPI().getEventManager().registerListener(new HandshakePacketListener());
    }
    public void onDisable() {

    }
}
