package net.tiagofar78.seasonsense;

public enum Season {
	
	WINTER,
	SPRING,
	SUMMER,
	FALL;
	
	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
	}

}
