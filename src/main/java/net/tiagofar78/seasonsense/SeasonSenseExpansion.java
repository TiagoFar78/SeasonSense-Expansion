package net.tiagofar78.seasonsense;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;


public class SeasonSenseExpansion extends PlaceholderExpansion implements Configurable {
	
	private static final String seasons[] = { "Winter", "Winter", "Spring", "Spring", "Summer", "Summer",
			"Summer", "Summer", "Fall" , "Fall", "Winter", "Winter" };
	
	private final Map<String, Object> _defaults = new HashMap<String, Object>();

	@Override
	public Map<String, Object> getDefaults() {
		String winterString = this.getString("Seasons.Winter", "Winter");
		String summerString = this.getString("Seasons.Summer", "Summer");
		String springString = this.getString("Seasons.Spring", "Spring");
		String fallString = this.getString("Seasons.Fall", "Fall");
		
		_defaults.put("Seasons.Winter", winterString);
		_defaults.put("Seasons.Summer", summerString);
		_defaults.put("Seasons.Spring", springString);
		_defaults.put("Seasons.Fall", fallString);		
		
		return _defaults;
	}

	@Override
	public  String getAuthor() {
		return "TiagoFar78";
	}

	@Override
	public String getIdentifier() {
		return "seasonsense";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
	@Override
	public String onPlaceholderRequest(Player p, String identifier) {
		if (identifier.equalsIgnoreCase("season")) {
			return (String) _defaults.get("Seasons." + getSeason());
		}
		
		return null;
	}
	
	private String getSeason() {
		Calendar calendar = Calendar.getInstance();
		return seasons[calendar.get(Calendar.MONTH)];
	}

}