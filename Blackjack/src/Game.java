import java.util.Scanner;
public class Game {
    private int wins, losses, pushes;
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private Balance balance;
    boolean doubleDown;

    public Game() {
        deck = new Deck(true);
        discarded = new Deck();

        dealer = new Dealer();
        player = new Player();
        balance = new Balance(5000);

        deck.shuffleDeck();
        startRound();
    }

    private void startRound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------");
        System.out.println("you have $" + balance.getBalance());
        System.out.println("how much would you like to bet?");
        int bet = scanner.nextInt();
        if (bet % 5 != 0) {
            System.out.println("please bet in increments of 5");
            startRound();
        }
        if (bet > balance.getBalance()) {
            System.out.println("you dont have that much");
            startRound();
        } 
        else{

            if (wins > 0 || losses > 0 || pushes > 0) {
                System.out.println();
                System.out.println("Starting Next Round ... ");
                System.out.println("you have $" + balance.getBalance());
                System.out.println("Wins: " + wins + " || Losses: " + losses + " || Pushes: " + pushes);
                dealer.getHand().discardHandToDeck(discarded);
                player.getHand().discardHandToDeck(discarded);
            }

            if (deck.cardsLeft() < 4) {
                deck.reloadDeckFromDiscarded(discarded);
            }

            dealer.getHand().takeCardFromDeck(deck);
            dealer.getHand().takeCardFromDeck(deck);
            player.getHand().takeCardFromDeck(deck);
            player.getHand().takeCardFromDeck(deck);

            dealer.printFirstHand();
            player.printHand();

            if (dealer.hasBlackjack()) {
                dealer.printHand();

                if (player.hasBlackjack()) {
                    System.out.println("You both have 21");
                    pushes++;
                    startRound();
                } else {
                    System.out.println("dealer has blackjack");
                    dealer.printHand();
                    losses++;
                    balance.setBalance(-bet);
                    startRound();
                }
            }
            if (player.hasBlackjack()) {
                System.out.println("you have blackjack");
                wins++;
                balance.setBalance(bet);
                startRound();
            }

                System.out.println("would you like to double down? y/n");
                String fixBug = scanner.nextLine();
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    bet += bet;
                    System.out.println("you doubled down! Now betting $" + bet);
                    player.getHand().takeCardFromDeck(deck);
                    player.printHand();
                    doubleDown = false;

                } else {
                    doubleDown = true;

                }
            
            if (doubleDown == true) {
                player.makeDecision(deck, discarded);
            }
            if (player.getHand().calculatedValue() > 21) {
                System.out.println("you have busted");
                losses++;
                balance.setBalance(-bet);
                startRound();
            }

            dealer.printHand();
            while (dealer.getHand().calculatedValue() < 17) {
                dealer.hit(deck, discarded);
            }

            if (dealer.getHand().calculatedValue() > 21) {
                System.out.println("dealer busts");
                wins++;
                balance.setBalance(bet);
            } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
                System.out.println("you lose");
                losses++;
                balance.setBalance(-bet);
            } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
                System.out.println("you win");
                wins++;
                balance.setBalance(bet);
            } else {
                System.out.println("push");
            }

            startRound();
        }
    }
    
}
