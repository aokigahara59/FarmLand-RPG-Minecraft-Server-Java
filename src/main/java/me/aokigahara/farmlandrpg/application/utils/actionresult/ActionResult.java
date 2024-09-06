package me.aokigahara.farmlandrpg.application.utils.actionresult;

public class ActionResult<T> implements IActionResult {
    private T result;

    public T getResult() {
        return result;
    }

    public ActionResult() {
    }

    public ActionResult(T result) {
        this.result = result;
    }
}