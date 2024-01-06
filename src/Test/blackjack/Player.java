package Test.blackjack;
import Test.blackjack.*;
import java.util.*;

public class Player {
	private ArrayList<Card> own_card;
	private int money;
	
	public Player() {
		own_card = new ArrayList<Card>();
		money =100;
	}
	
	public void reset_card() { // reset deck
		own_card.clear();
	}
	
	public void add_card(Card card) { // add card to the deck
		own_card.add(card);
	}
	
	public ArrayList<Card> return_cards() { // return own_card
		return own_card;
	}
	
	public void print_card() {
		for(Card card:own_card) {System.out.print(card.toString()+" ");}
		System.out.println("");
	}
	
	
	public void change_money(int amount) {
		money += amount;
	}
	
	public int return_money() {
		return money;
	}
}
