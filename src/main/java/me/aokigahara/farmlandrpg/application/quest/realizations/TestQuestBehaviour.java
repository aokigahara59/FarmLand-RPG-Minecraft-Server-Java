package me.aokigahara.farmlandrpg.application.quest.realizations;

import me.aokigahara.farmlandrpg.application.quest.realizations.phases.BreakResourcePhaseBehaviour;
import me.aokigahara.farmlandrpg.application.resource.realizations.SimpleCoalResource;
import me.aokigahara.farmlandrpg.application.utils.AsyncUtils;
import me.aokigahara.farmlandrpg.domain.quest.QuestBehaviour;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestQuestBehaviour extends QuestBehaviour {

    @Override
    public String getTitle() {
        return "Test quest";
    }

    @Override
    public String getDescription() {
        return "Test description";
    }

    @Override
    public boolean repeatable() {
        return false;
    }

    public TestQuestBehaviour() {
        super();
        addQuestPhaseBehaviour(0, new BreakResourcePhaseBehaviour("Перая 1", "1", 5, SimpleCoalResource.class, (player -> {
            player.sendMessage("Ща кирочку дам погоди");
            AsyncUtils.sleep(300);

            player.sendMessage("3");
            AsyncUtils.sleep(300);
            player.sendMessage("2");
            AsyncUtils.sleep(300);
            player.sendMessage("1");
            AsyncUtils.sleep(300);

            player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
            player.sendTitle("Погнал", "", 5, 30, 10);
        })));
    }
}
