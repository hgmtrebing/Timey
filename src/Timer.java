
public class Timer 
{
	private long startMillis;
	private long stopMillis;
	private long timeRemaining;
	private long hoursRemaining;
	private long minutesRemaining;
	private long secondsRemaining;
	private boolean stopAtZero; //determines whether the timer should stop at zero, or keep going into negative numbers

	public void set (long hours, long minutes, long seconds)
	{
		if (hours < 0 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59)
		{
			throw new IllegalArgumentException ("Invalid Time!");
		} //checks to ensure that hours, minutes, and seconds are non-negative. Also checks that minutes and seconds are less than 59

		this.timeRemaining = (hours*60*60*1000) + (minutes*60*1000) + (seconds*1000);
	}

	/**
	 * Resets the Timer to 0.
	 */
	public void reset ()
	{
		this.set (0, 0, 0);
		this.startMillis = 0;
		this.stopMillis = 0;
	}

	/**
	 * Starts the Timer. 
	 */
	public void start ()
	{
		this.startMillis = System.currentTimeMillis();
		this.stopMillis = this.startMillis + this.timeRemaining;
	}

	/**
	 *
	 */
	public void stop ()
	{
		this.updateTimeRemaining();
		this.startMillis = 0;
		this.stopMillis = 0;
	}

	private void updateTimeRemaining ()
	{
		this.timeRemaining = this.stopMillis - System.currentTimeMillis(); 

		if (this.stopAtZero && this.timeRemaining <= 0)
		{
			this.timeRemaining = 0;
			this.hoursRemaining = 0;
			this.minutesRemaining = 0;
			this.secondsRemaining = 0;
			return;
		}

		this.hoursRemaining = this.timeRemaining / (1000*60*60);
		this.minutesRemaining = (this.timeRemaining - (this.hoursRemaining*1000*60*60)) / (1000*60);
		this.secondsRemaining = (this.timeRemaining - (this.hoursRemaining*1000*60*60) - (this.minutesRemaining*1000*60)) / 1000; 
	}
	
	/**
	 * Updates, then returns the remaining time on this timer, in milliseconds.
	 */
	public long getTimeRemaining ()
	{
		this.updateTimeRemaining ();
		return this.timeRemaining;
	}

	/**
	 * Returns the hours (if any) remaining on this timer. Unlike getTimeRemaining(), getHoursRemaining() does not update the remaining time first. This is because
	 * this method is mainly to display the hours in a human-readable format.
	 */
	public long getHoursRemaining()
	{
		return this.hoursRemaining;
	}

	/**
	 *Returns the minutes remaining to the closest hour on this timer. For example, if the remaining time is 03:40:00, this method will return 40. This method does not update the remaining time first
	 *and mainly exists to render the remaining time in human-readable format.
	 */
	public long getMinutesRemaining()
	{
		return this.minutesRemaining;
	}

	/**
	 *Returns the seconds remaining to the closest minute on this timer. For example, if the remaining time is 01:35:27, this method will return 27. This method does not update the remaining time first
	 * and mainly exists to render the remaining time in human-readable format.
	 */
	public long getSecondsRemaining()
	{
		return this.secondsRemaining;
	}

	/**
	 * isActive() determines if there is still time remaining on the Timer. isActive() updates the remaining time before performing its check, making it useful for a while-loop.
	 */
	public boolean isActive ()
	{
		this.updateTimeRemaining();
		return (this.getTimeRemaining() > 0);
	}

	//Testing
	public static void main (String[] args)
	{
		Timer timer = new Timer();
		timer.set (Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]));
		timer.start ();
		while (timer.isActive())
		{
			System.out.println (timer.getHoursRemaining() + ":" + timer.getMinutesRemaining() + ":" + timer.getSecondsRemaining());
		}
	}
}
