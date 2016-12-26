
public class Stopwatch
{
	private long timeElapsed = 0;
	private long timeLastPolled;
	private boolean isActive = false;

	//-----------------------------------------------------------

	private void update ()
	{
		if (!this.isActive)
		{
			//throw an exception
		}

		long current = System.currentTimeMillis();
		this.timeElapsed += (current - this.timeLastPolled); 
		this.timeLastPolled = current;	
	}

	//-----------------------------------------------------------

	public boolean start ()
	{
		if (isActive)
		{
			return false;
		}

		this.timeLastPolled = System.currentTimeMillis();
		this.isActive = true;
		return true; //indicates method was successful
	}	

	//------------------------------------------------------------

	public boolean stop ()
	{
		if (!this.isActive)
		{
			return false;
		}

		this.update();
		this.isActive = false;
		
		return true; //indicates method was successful 
	}
 
	//----------------------------------------------------------- 

	public long getTimeElapsed ()
	{
		return this.timeElapsed;
	}

	//-----------------------------------------------------------

	
}
