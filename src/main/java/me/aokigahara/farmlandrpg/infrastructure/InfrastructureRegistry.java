package me.aokigahara.farmlandrpg.infrastructure;

import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyHolderManager;
import me.aokigahara.farmlandrpg.infrastructure.clientside.keysframework.KeyPacketHandler;
import me.aokigahara.farmlandrpg.infrastructure.eventhandlers.ResourceClickUpdateHandler;
import me.aokigahara.farmlandrpg.infrastructure.eventhandlers.questtabworkes.QuestTabInfoUpdateHandler;

public class InfrastructureRegistry extends AbstractRegistry {
    public void register(){


        registerOutgoingPacket("notify.push");
        registerOutgoingPacket("title.send");
        registerOutgoingPacket("camera.shake");
        registerOutgoingPacket("resource.breaking");
        registerOutgoingPacket("resource.broken");
        registerOutgoingPacket("hit");
        registerOutgoingPacket("leftsidebar");

        registerIncomingPacket("key.pressed", KeyPacketHandler.class);

        registerEvent(QuestTabInfoUpdateHandler.class);
        registerEvent(KeyHolderManager.class);
        registerEvent(ResourceClickUpdateHandler.class);

    }
}
