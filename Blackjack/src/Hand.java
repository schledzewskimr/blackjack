import java.util.ArrayList;
public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }

    public String toString() {
        String output = "";
        for (Card card : hand) {
            output += card + " || ";
        }
        return output;
    }

    public void discardHandToDeck(Deck discarded) {
        discarded.addCards(hand);
        hand.clear();
    }

    public int calculatedValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getCardValue();
            if (card.getCardValue() == 11) {
                aceCount++;
            }
        }

        if (value > 21 && aceCount > 0) {
            while (aceCount > 0 && value > 21) {
                aceCount--;
                value -= 10;
            }
        }
        return value;
    }

    public Card getCard(int idx) {
        return hand.get(idx);
    }
}
