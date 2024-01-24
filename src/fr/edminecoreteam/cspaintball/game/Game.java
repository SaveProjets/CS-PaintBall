package fr.edminecoreteam.cspaintball.game;

import fr.edminecoreteam.cspaintball.Core;
import fr.edminecoreteam.cspaintball.State;
import fr.edminecoreteam.cspaintball.end.EndListeners;
import fr.edminecoreteam.cspaintball.game.rounds.RoundInfo;
import fr.edminecoreteam.cspaintball.game.tasks.End;
import fr.edminecoreteam.cspaintball.game.tasks.Preparation;
import fr.edminecoreteam.cspaintball.game.tasks.Start;
import fr.edminecoreteam.cspaintball.game.teams.TeamsKit;
import fr.edminecoreteam.cspaintball.game.utils.LoadHolograms;
import fr.edminecoreteam.cspaintball.game.weapons.bombe.Bombe;
import fr.edminecoreteam.cspaintball.utils.dragonbar.BarListener;
import fr.edminecoreteam.cspaintball.utils.dragonbar.BarUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Game
{

    private static Core core = Core.getInstance();

    private final Location attackerSpawn = new Location(Bukkit.getWorld("game"),
            (float) core.getConfig().getDouble("maps." + core.world + ".attacker.x"),
            (float) core.getConfig().getDouble("maps." + core.world + ".attacker.y"),
            (float) core.getConfig().getDouble("maps." + core.world + ".attacker.z"),
            (float) core.getConfig().getDouble("maps." + core.world + ".attacker.f"),
            (float) core.getConfig().getDouble("maps." + core.world + ".attacker.t"));

    private final Location defenserSpawn = new Location(Bukkit.getWorld("game"),
            (float) core.getConfig().getDouble("maps." + core.world + ".defenser.x"),
            (float) core.getConfig().getDouble("maps." + core.world + ".defenser.y"),
            (float) core.getConfig().getDouble("maps." + core.world + ".defenser.z"),
            (float) core.getConfig().getDouble("maps." + core.world + ".defenser.f"),
            (float) core.getConfig().getDouble("maps." + core.world + ".defenser.t"));

    public void preparationRound()
    {
        core.setRoundState(RoundInfo.PREPARATION);
        int rounds = 0;
        int finalrounds = 0;
        core.getServer().broadcastMessage("§d§lTIPS§7: §7Ouvrez votre inventaire pour aller sur la §eBoutique d'armement§7.");
        if (core.getConfig().getString("time").equalsIgnoreCase("short"))
        {
            rounds = core.getConfig().getInt("timers.rounds-short");
            finalrounds = rounds * 2;
            if (core.roundManager().getRound() == rounds) { core.getServer().broadcastMessage("§6§l⚠ §7Vous changerez d'équipe à la prochaine manche."); }
            if (core.roundManager().getRound() == finalrounds) { core.getServer().broadcastMessage("§6§l⚠ §7La partie s'arrête à la fin de cette manche."); }
        }
        if (core.getConfig().getString("time").equalsIgnoreCase("long"))
        {
            rounds = core.getConfig().getInt("timers.rounds-long");
            finalrounds = rounds * 2;
            if (core.roundManager().getRound() == rounds) { core.getServer().broadcastMessage("§6§l⚠ §7Vous changerez d'équipe à la prochaine manche."); }
            if (core.roundManager().getRound() == finalrounds) { core.getServer().broadcastMessage("§6§l⚠ §7La partie s'arrête à la fin de cette manche."); }
        }

        for (Player attackers : core.teams().getAttacker())
        {
            attackers.teleport(attackerSpawn);
            BarUtil.sendBar(attackers, "", 100);
            BarListener barListener = new BarListener(attackers);
            barListener.launch();
            if (core.teams().getAttackerDeath().contains(attackers) || core.roundManager().getRound() == 1 || core.roundManager().getRound() == rounds + 1)
            {
                TeamsKit kit = new TeamsKit();
                kit.equipDefault(attackers);
                if (core.teams().getAttackerDeath().contains(attackers))
                {
                    core.teams().getAttackerDeath().remove(attackers);
                }
            }
            if (!core.teams().getAttackerDeath().contains(attackers) && core.roundManager().getRound() != 1)
            {
                TeamsKit kit = new TeamsKit();
                kit.equipNotDeathDefault(attackers);
            }
        }

        for (Player defensers : core.teams().getDefenser())
        {
            defensers.teleport(defenserSpawn);
            BarUtil.sendBar(defensers, "", 100);
            BarListener barListener = new BarListener(defensers);
            barListener.launch();
            if (core.teams().getDefenserDeath().contains(defensers) || core.roundManager().getRound() == 1 || core.roundManager().getRound() == rounds + 1)
            {
                TeamsKit kit = new TeamsKit();
                kit.equipDefault(defensers);
                if (core.teams().getDefenserDeath().contains(defensers))
                {
                    core.teams().getDefenserDeath().remove(defensers);
                }
            }
            if (!core.teams().getDefenserDeath().contains(defensers) && core.roundManager().getRound() != 1)
            {
                TeamsKit kit = new TeamsKit();
                kit.equipNotDeathDefault(defensers);
            }
        }
        Bombe bomb = new Bombe();
        bomb.getRandom();
        Preparation preparation = new Preparation(core);
        preparation.runTaskTimer((Plugin) core, 0L, 20L);
    }

    public void startRound()
    {
        LoadHolograms holograms = new LoadHolograms();
        holograms.init();
        Start start = new Start(core);
        start.runTaskTimer((Plugin) core, 0L, 20L);
    }

    public void endRound()
    {
        if (core.getConfig().getString("time").equalsIgnoreCase("short"))
        {
            int roundshort = core.getConfig().getInt("timers.rounds-short");
            int finalroundshort = roundshort * 2;
            if (core.roundManager().getRound() == roundshort) { changeTeam(); return; }
            if (core.roundManager().getRound() == finalroundshort) { endGame(); return; }
        }
        if (core.getConfig().getString("time").equalsIgnoreCase("long"))
        {
            int roundlong = core.getConfig().getInt("timers.rounds-long");
            int finalroundlong = roundlong * 2;
            if (core.roundManager().getRound() == roundlong) { changeTeam(); return; }
            if (core.roundManager().getRound() == finalroundlong) { endGame(); return; }
        }

        End end = new End(core);
        end.runTaskTimer((Plugin) core, 0L, 20L);
    }

    public void changeTeam()
    {
        final List<Player> attackers = new ArrayList<Player>();
        final List<Player> defensers = new ArrayList<Player>();

        final int attackerScore = core.pointsManager().getAttackerPoints();
        final int defenserScore = core.pointsManager().getDefenserPoints();

        for (Player pls : core.teams().getAttacker())
        {
            if (core.teams().getAttackerDeath().contains(pls))
            {
                core.teams().getAttackerDeath().remove(pls);
            }
            attackers.add(pls);
        }

        for (Player pls : core.teams().getDefenser())
        {
            if (core.teams().getDefenserDeath().contains(pls))
            {
                core.teams().getDefenserDeath().remove(pls);
            }
            defensers.add(pls);
        }

        core.teams().getAttacker().clear();
        core.teams().getDefenser().clear();

        for (Player pls : attackers)
        {
            core.teams().joinTeam(pls, "defenser");
        }

        for (Player pls : defensers)
        {
            core.teams().joinTeam(pls, "attacker");
        }

        core.pointsManager().setAttackerPoints(defenserScore);
        core.pointsManager().setDefenserPoints(attackerScore);

        End end = new End(core);
        end.runTaskTimer((Plugin) core, 0L, 20L);
    }

    public void endGame()
    {
        core.setState(State.FINISH);
        EndListeners end = new EndListeners();
        end.end();
    }
}
