package me.aokigahara.farmlandrpg.application.utils.actionresult;

public class Success implements IActionResult {
    private String message;

    public Success(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
