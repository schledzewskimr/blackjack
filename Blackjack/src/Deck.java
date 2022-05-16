import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public Deck(boolean makeDeck) {
        deck = new ArrayList<Card>();
        if (makeDeck) {

            for (Suits suit : Suits.values()) {
                for (Values value : Values.values()) {
                    deck.add(new Card(suit, value));
                }
            }
        }
    }

    public boolean hasCards() {
        if (deck.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void emptyDeck() {
        deck.clear();
    }

    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }
    
    public ArrayList<Card> getCards() {
        return deck;
    }

    public void reloadDeckFromDiscarded(Deck discarded) {
        this.addCards(discarded.getCards());
        this.shuffleDeck();
        discarded.emptyDeck();
        System.out.println("ran out of cards, making new deck");
    }

    public int cardsLeft() {
        return deck.size();
    }
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card getCard(int i) {
        return this.deck.get(i);
    }

    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }

    public void removeCard(int i) {
        this.deck.remove(i);
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public String toString() {
        String output = "";
        for (Card card : deck) {
            output += card;
            output += "\n";
        }
        return output;
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        for (int i = 0; i < this.deck.size(); i++) {
            comingFrom.getCard(i);
            comingFrom.removeCard(i);
        }
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {

    }

}