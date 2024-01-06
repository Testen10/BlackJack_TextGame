package Test.blackjack;
import Test.blackjack.Card;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> unused_card = new ArrayList<Card>();
	private Random rand;
	
	public Deck() {
		rand = new Random();
		for(int num=1;num<=13;num++) {
			for(int loc=0;loc<4;loc++) {
				unused_card.add(new Card(num,loc));
			}
		}
	}
	
	public Card pick_card() { // 덱에서 카드 한 장을 뽑음
		int loc = rand.nextInt(unused_card.size());
		Card card = unused_card.get(loc);
		unused_card.remove(loc);
		
		return card;
	}
	
	public boolean is_remainingCard(){ // 덱에 남은 카드가 있는지 판
		if(unused_card.size()>0) { return true; }
		return false;
	}
	
}
