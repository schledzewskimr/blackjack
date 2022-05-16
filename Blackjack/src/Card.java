import java.util.Random;

public class Card {

    private Suits suit;
    private Values value;

    // create arrays for values and suits - need these to generate random cards
    private Values[] values = Values.values();
    private Random randomValues = new Random();
    private Suits[] suits = Suits.values();
    private Random randomSuits = new Random();

    public Card(Suits suit, Values value) {
        this.value = value;
        this.suit = suit;
    }

    public Card(Card card) {
        this.suit = card.getSuit();
        this.value = card.getValue();
    }
    // random card generated
    public Card() {
        this.suit = getRandomSuit();
        this.value = getRandomValue();
    }

    public String toString() {
        return (value + " of " + suit + " - score = " + this.getCardValue());
    }

    public int getCardValue() {
        return value.cardValue;
    }

    public Values getValue() {
        return value;
    }

    public Suits getSuit() {
        return suit;
    }

    public Values getRandomValue() {
        return values[randomValues.nextInt(values.length)];
    }

    public Suits getRandomSuit() {
        return suits[randomSuits.nextInt(values.length)];
    }
}
