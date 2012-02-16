package fi.trashed.pregnancycalculator;

import java.util.Calendar;

public class CalculateWeeks 
{
	private final int PREGNANCY_TIME_IN_DAYS = 280;
	private long daysBetween = 0;
	
	//private long current;
	//private long estimated;
	
	//private Date current;
	//private Date estimated;
	private Calendar current;
	private Calendar estimated;
	
	private String elapsed;
	
	/**
	 * Constructor
	 * @param long _current, long _estimated
	 * @author Jose
	 */
	public CalculateWeeks(Calendar _current, Calendar _estimated) 
	{
		current = (Calendar)_current.clone();
		estimated = _estimated;
		
		/*elapsed = null;*/
		
		calculate();
	}

	private void calculate()
	{
		// TODO: loppuun tämä: kuluneen raskausajan laskeminen
		while (current.before(estimated)) {
			current.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		
		/*long diff = estimated - current;
		double temp = (double)diff / MILLISECONDS_IN_DAY;
		int daysLeft = (int) Math.round(temp);*/
		
		
		int elapsedDays = PREGNANCY_TIME_IN_DAYS - (int)daysBetween;
		
		if (elapsedDays % 7 == 0) {
			putToString(0, elapsedDays / 7);
		}
		else {
			int weeks = elapsedDays / 7;
			int days = (elapsedDays % 7);
			
			putToString(days, weeks);
		}
		
	}
	
	/** Convert days and weeks to a one string.
	 * 
	 * @param days
	 * @param weeks
	 * 
	 * @author Jose
	 */
	private void putToString(int days, int weeks)
	{
		elapsed = String.valueOf(weeks) + "w + " + String.valueOf(days) + "d";
	}
	
	
	public String getElapsedTime()
	{
		return elapsed;
	}
}
