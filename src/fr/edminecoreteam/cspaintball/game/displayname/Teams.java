package fr.edminecoreteam.cspaintball.game.displayname;

import java.util.HashMap;
import java.util.Map;

public enum Teams
{
    NOTEAM("NOTEAM", 0, 0, "§l§6Rank", "§7", "§7", ""),
    SPEC("SPEC", 1, 1, "§f§6Rank", "§8SPEC", "§8§lSPEC §8", ""),
    ATTAQUANTS("ATTAQUANTS", 2, 2, "§k§6Rank", "§cATTAQUANTS", "§c§lATTAQUANT §c", " §f§l┃ "),
    DEFENSEURS("DEFENSEURS", 3, 3, "§i§6Rank", "§9DEFENSEURS", "§9§lDÉFENSEUR §9", " §f§l┃");
    
    private int power;
    private String name;
    private String orderRank;
    private String displayName;
    private String suffix;
    public static Map<Integer, Teams> teams;
    
    static {
    	Teams.teams = new HashMap<Integer, Teams>();
    	Teams[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
        	Teams teams = values[i];
        	Teams.teams.put(teams.getPower(), teams);
        }
    }
    
    private Teams(String rank, int ordinal, int power, String orderRank, String name, String displayName, String Suffix) {
        this.power = power;
        this.name = name;
        this.orderRank = orderRank;
        this.displayName = displayName;
        this.suffix = Suffix;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static Teams powerToTeam(int power) {
        return Teams.teams.get(power);
    }
    
    public int getPower() {
        return this.power;
    }
    
    public String getOrderTeam() {
        return this.orderRank;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
}
