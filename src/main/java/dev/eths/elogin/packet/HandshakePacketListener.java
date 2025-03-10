package dev.eths.elogin.packet;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import com.github.retrooper.packetevents.wrapper.handshaking.client.WrapperHandshakingClientHandshake;

public class HandshakePacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Handshaking.Client.HANDSHAKE) {
            WrapperHandshakingClientHandshake handshake = new WrapperHandshakingClientHandshake(event);

            User user = event.getUser();
            UserProfile userProfile = user.getProfile();

            System.out.printf("RECEIVE:\nUUID: %s\nName: %s%n",
                    userProfile.getUUID(),
                    userProfile.getName());
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
    }

}
