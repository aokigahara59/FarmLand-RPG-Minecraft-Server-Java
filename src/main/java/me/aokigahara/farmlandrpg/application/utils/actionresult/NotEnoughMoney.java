package me.aokigahara.farmlandrpg.application.utils.actionresult;

public class NotEnoughMoney implements IActionResult {
    private long required;

    public NotEnoughMoney(long required) {
        this.required = required;
    }

    public long getRequired() {
        return required;
    }
}
