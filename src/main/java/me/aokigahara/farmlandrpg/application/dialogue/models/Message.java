package me.aokigahara.farmlandrpg.application.dialogue.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Message {
    private String title;
    private String text;
    private List<String> answers;

    public Message(String title, String text, List<String> answers) {
        this.title = title;
        this.text = text;
        this.answers = answers;
    }

}
