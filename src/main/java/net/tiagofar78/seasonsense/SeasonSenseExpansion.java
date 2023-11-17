package net.tiagofar78.seasonsense;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.time4j.Moment;
import net.time4j.TemporalType;
import net.time4j.calendar.astro.AstronomicalSeason;

public class SeasonSenseExpansion extends PlaceholderExpansion implements Configurable {
	
	private final Map<String, Object> _defaults = new HashMap<String, Object>();

	private static final String winterString = Season.WINTER.toString();
	private static final String summerString = Season.SUMMER.toString();
	private static final String springString = Season.SPRING.toString();
	private static final String fallString = Season.FALL.toString();
	
	@Override
	public Map<String, Object> getDefaults() {
		String winterConfig = this.getString("Seasons." + winterString, winterString);
		String summerConfig = this.getString("Seasons." + summerString, summerString);
		String springConfig = this.getString("Seasons." + springString, springString);
		String fallConfig = this.getString("Seasons." + fallString, fallString);
		
		_defaults.put("Seasons." + winterString, winterConfig);
		_defaults.put("Seasons." + summerString, summerConfig);
		_defaults.put("Seasons." + springString, springConfig);
		_defaults.put("Seasons." + fallString, fallConfig);
		
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
			return (String) _defaults.get("Seasons." + getSeason().toString());
		}
		
		return null;
	}
	
	private Season getSeason() {
		java.util.Date today = new java.util.Date();

	    Moment momment = TemporalType.JAVA_UTIL_DATE.translate(today);
	    AstronomicalSeason astronomicalSeason = AstronomicalSeason.of(momment);
	    
		return astronomicalSeasonToSeason(astronomicalSeason);
	}
	
	private Season astronomicalSeasonToSeason(AstronomicalSeason astronomicalSeason) {
		switch (astronomicalSeason) {
		case WINTER_SOLSTICE:
			return Season.WINTER;
		case VERNAL_EQUINOX:
			return Season.SPRING;
		case SUMMER_SOLSTICE:
			return Season.SUMMER;
		case AUTUMNAL_EQUINOX:
			return Season.FALL;
		}
		
		return null;
	}

}