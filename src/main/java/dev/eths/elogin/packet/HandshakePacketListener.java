package dev.eths.elogin.packet;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import com.github.retrooper.packetevents.util.MojangAPIUtil;
import com.github.retrooper.packetevents.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import com.github.retrooper.packetevents.wrapper.login.client.WrapperLoginClientLoginStart;
import com.github.retrooper.packetevents.wrapper.login.server.WrapperLoginServerLoginSuccess;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Optional;
import java.util.UUID;

public class HandshakePacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Login.Client.LOGIN_START) {
            WrapperLoginClientLoginStart handshake = new WrapperLoginClientLoginStart(event);

            String username = handshake.getUsername();
            Optional<UUID> uuidOptional = handshake.getPlayerUUID();

            if (username == null) return;
            if (uuidOptional.isEmpty()) return;

            UUID realUUID = uuidOptional.get();

            UUID offlineUUID = UUID.nameUUIDFromBytes(("OfflinePlayer: " + username).getBytes());
            UUID onlineUUID = MojangAPIUtil.requestPlayerUUID(username);

            boolean isOnline = onlineUUID == realUUID;

            System.out.printf("Premium: %s\nUUID: %s\nName: %s%n",
                    isOnline,
                    realUUID,
                    username);
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
            WrapperLoginServerLoginSuccess loginSuccess = new WrapperLoginServerLoginSuccess(event);

            UserProfile userProfile = loginSuccess.getUserProfile();
            String userName = userProfile.getName();
            UUID realUUID = userProfile.getUUID();

            UUID onlineUUID = MojangAPIUtil.requestPlayerUUID(userName);
            UUID offlineUUID = UUID.nameUUIDFromBytes(("OfflinePlayer: " + userName).getBytes());

//            boolean isOnline = onlineUUID == realUUID;

//            userProfile.setUUID(onlineUUID);
//            loginSuccess.setUserProfile(userProfile);
//            loginSuccess.prepareForSend();
//            loginSuccess.setUserProfile();
//
//            Bukkit.getPlayer("Test").canSee()
//
//            loginSuccess.set

        }
    }


}
