package me.aokigahara.farmlandrpg.application.dialogue.models;

import me.aokigahara.farmlandrpg.FarmLandRpg;
import me.aokigahara.farmlandrpg.application.common.abstractions.IPluginMessageByteBuffer;
import me.aokigahara.farmlandrpg.application.utils.DualKeyMap;
import me.aokigahara.farmlandrpg.application.utils.Tuple;
import me.aokigahara.farmlandrpg.application.utils.actions.PlayerAction;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public abstract class Dialogue {

    private DualKeyMap<Message, String, Tuple<Message, PlayerAction>> dialogueMap;
    private Tuple<Message, PlayerAction> initialMessage;
    private final Player player;
    @Nullable
    private Message current;

    public Dialogue(Player player) {
        this.player = player;
        dialogueMap = new DualKeyMap<>();
        setDialogue();
        current = null;
        player.setMetadata("dialogue", new FixedMetadataValue(FarmLandRpg.getInstance(), this));

    }


    public abstract void setDialogue();

    public boolean hasAnswer(int answer){
        var message = current == null ? initialMessage.getFirst() : current;
        return answer <= message.getAnswers().size();
    }

    public void start(){
        CompletableFuture.runAsync(()->{

        });
        current = initialMessage.getFirst();
        sendMessage(current);
        player.setMetadata("dialogue", new FixedMetadataValue(FarmLandRpg.getInstance(), this));
        initialMessage.getSecond().execute(player);
    }

    public void next(int answer){
        Tuple<Message, PlayerAction> next = dialogueMap.get(current, current.getAnswers().get(answer));

        if (next == null){
            removeMessage();
            player.removeMetadata("dialogue", FarmLandRpg.getInstance());
            return;
        }

        CompletableFuture.runAsync(() -> {


        });
        current = next.getFirst();
        next.getSecond().execute(player);
        sendMessage(next.getFirst());
        player.setMetadata("dialogue", new FixedMetadataValue(FarmLandRpg.getInstance(), this));
    }

    private void sendMessage(Message message){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeString(message.getTitle());
        packet.writeString(message.getText());
        packet.writeInt(message.getAnswers().size());
        for(var x : message.getAnswers()){
            packet.writeString(x);
        }
        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":dialogue.message", packet.asByteArray());
    }

    private void removeMessage(){
        var packet = FarmLandRpg.getInjector().getInstance(IPluginMessageByteBuffer.class);
        packet.writeBoolean(true);
        player.sendPluginMessage(FarmLandRpg.getInstance(), FarmLandRpg.ID+":dialogue.remove", packet.asByteArray());
    }

    public void addMessage(Message from, String answer, Message to, PlayerAction toAction){
        dialogueMap.put(from, answer, new Tuple<>(to, toAction));
    }

    public void addMessage(Message message, PlayerAction action){
        initialMessage = new Tuple<>(message, action);
    }
}
