package Test.blackjack;
import Test.blackjack.*;
import java.util.*;

public class Rule {
	private static final String yellow      = "\u001B[33m" ;
	private static final String exit     = "\u001B[0m" ;
	
	
	int get_cardSum(Player player) {
		int sum=0;
		for(Card card:player.return_cards()) { sum += card.return_num();}
		
		return sum;
	}
	
	public void get_winner(Player player, Player dealer, int money) {
		int player_score = get_cardSum(player);
		int dealer_score = get_cardSum(dealer);
		if(player_score==21 && get_cardSum(dealer)!=21) { // BLACK JACK - get money*3
			System.out.println("BLACKJACK!!!!");
			player.change_money(money*3);
		}
		else if(player_score>21) {  // Bust
			System.out.println(yellow+"BUST"+exit);
		}
		else if(player_score==dealer_score) {  // Tie - get money back
			System.out.println(yellow+"Tie!"+exit);
			player.change_money(money);
		}
		else if (player_score>dealer_score) {  // player win - get money*2
			System.out.println(yellow+"You won!"+exit);
			player.change_money(money*2);
		}
		else{  // dealer win (dealer higher score or both bust) - lose money
			System.out.println(yellow+"Dealer won!"+exit);
		}
		
		System.out.println("Current Money: "+Integer.toString(player.return_money()));
	}
}
