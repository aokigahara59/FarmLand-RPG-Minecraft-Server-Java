package me.aokigahara.farmlandrpg.application.utils.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

@Aspect
public class EventCancellationAspect {

    @Around("execution(* org.bukkit.event.Listener+.on*(..)) && args(event)")
    public void beforeEventExecution(ProceedingJoinPoint joinPoint, Event event){
        if (event instanceof Cancellable cancellable && cancellable.isCancelled()){
            Bukkit.getLogger().warning("asdasdsa");
                return;
        }
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
