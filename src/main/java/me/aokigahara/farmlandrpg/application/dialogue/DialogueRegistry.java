package me.aokigahara.farmlandrpg.application.dialogue;

import me.aokigahara.farmlandrpg.application.dialogue.handlers.DialogueWorkHandler;
import me.aokigahara.farmlandrpg.application.utils.AbstractRegistry;

public class DialogueRegistry extends AbstractRegistry {

    public void register(){
        registerOutgoingPacket(":dialogue.message");
        registerOutgoingPacket(":dialogue.select");
        registerOutgoingPacket(":dialogue.remove");
        registerEvent(DialogueWorkHandler.class);
    }
}
