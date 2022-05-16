import java.util.Scanner;

public class Player extends Person {
    Scanner scanner = new Scanner(System.in);


    public Player() {
        System.out.println("enter your name");
        String inputName = scanner.nextLine();
        super.setName(inputName);
    }

    public void makeDecision(Deck deck, Deck discarded) {
        int decision = 0;
        boolean getNum = true;

        while (getNum) {
            try {
                System.out.println("would you like to [1]hit or [2]stand");
                decision = scanner.nextInt();
                getNum = false;
            } catch (Exception e) {
                System.out.println("Invalid");
                scanner.next();
            }
        }
        System.out.println("you selected " + decision);
        System.out.println("-------------------------------------------");

        if (decision == 1) {
            this.hit(deck, discarded);

            if (this.getHand().calculatedValue() > 20) {
                return;
            } else {
                this.makeDecision(deck, discarded);
            }
        }
        else {
            System.out.println("you stand");
        }
    }
}
