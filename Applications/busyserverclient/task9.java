import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.io.*;

public class SharedTasks {
	public static void main (String[] args) {
		Random wait = new Random();
		TimeUnit.SECONDS.sleep(wait);
		System.out.print("This is the end of the program, thank you for waiting.");
	}
}
