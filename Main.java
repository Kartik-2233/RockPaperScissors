import java.util.*;

class Users implements Runnable {
	private String selectedString;

	void setResult(String selectedString) {
		this.selectedString = selectedString;

	}

	String getResult() {
		return selectedString;
	}

	public void run() {

		String[] options = { "Rock", "Paper", "Scissors" };

		Random random = new Random();

		int randomIndex = random.nextInt(options.length);

		selectedString = options[randomIndex];
		setResult(selectedString);
		System.out.println(Thread.currentThread().getName() + " -> " + selectedString);
	}

}

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to the Game");
		Scanner sc = new Scanner(System.in);
		Users user1 = new Users();
		Users user2 = new Users();
		Thread player1 = new Thread(user1);
		Thread player2 = new Thread(user2);

		System.out.println("Enter first user name");
		String firstUserName = sc.nextLine();
		player1.setName(firstUserName);
		System.out.println("Enter Second user name");
		String secondUserName = sc.nextLine();
		player2.setName(secondUserName);
		player1.start();

		player2.start();
		try {
			player1.join();
			player2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String resultP1 = user1.getResult();
		String resultP2 = user2.getResult();
		// System.out.println(resultP1 + " " + resultP2);
		if (resultP1.equals(resultP2)) {
			System.out.println("Result: It's a Draw!");
		} else if ((resultP1.equals("Rock") && resultP2.equals("Scissors"))
				|| (resultP1.equals("Scissors") && resultP2.equals("Paper"))
				|| (resultP1.equals("Paper") && resultP2.equals("Rock"))) {
			System.out.println("Winner: " + firstUserName);
		} else {
			System.out.println("Winner: " + secondUserName);
		}
		sc.close();
	}
}
