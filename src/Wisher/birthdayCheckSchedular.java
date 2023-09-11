package Wisher;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class birthdayCheckSchedular {
	
	 public static void RunSchedule() {
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	        // Get the current time
	        Calendar now = Calendar.getInstance();

	        // Set the time for the task to run every morning (e.g., 8:00 AM)
	        Calendar scheduledTime = Calendar.getInstance();
	        scheduledTime.set(Calendar.HOUR_OF_DAY, 24); // Hour
	        scheduledTime.set(Calendar.MINUTE, 28);      // Minute
	        scheduledTime.set(Calendar.SECOND, 0);      // Second

	        // Calculate the delay until the first execution
	        long initialDelay = scheduledTime.getTimeInMillis() - now.getTimeInMillis();
	        if (initialDelay < 0) {
	            // If the specified time has already passed today, schedule it for tomorrow
	            scheduledTime.add(Calendar.DAY_OF_MONTH, 1);
	            initialDelay = scheduledTime.getTimeInMillis() - now.getTimeInMillis();
	        }

	        // Schedule the task to run every 24 hours
	        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(new Runnable() {
				public void run() {
				    // Execute your task here
				    System.out.println("Task executed at " + Calendar.getInstance().getTime());
				}
			}, initialDelay, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
	    }
	

}
