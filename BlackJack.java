package Test.blackjack;
import Test.blackjack.*;
import java.util.*;

public class BlackJack {
	private static final String yellow      = "\u001B[33m" ;
	private static final String exit     = "\u001B[0m" ;
	private static Player player;
	private static Player dealer;
	private static Deck deck;
	private static Rule rule;
	private static Scanner sc;
	
	public static void game_start() throws InterruptedException {
		ArrayList<String> command_list = new ArrayList<String>(Arrays.asList("1","2")); // for continuing game or not
		String command;
		
		print_typingText(yellow, "** Welcome to BlackJack game **");
		
		Thread.sleep(1000); 
		howToPlay();
		
		Thread.sleep(1000); 
		print_typingText(yellow, "\n** Game Start **");
		player = new Player();
		dealer = new Player();
		rule = new Rule();
		
		while(true) {
			// set new game
			deck = new Deck();
			
			game_play(player,dealer,deck);
			
			if(player.return_money()<10) { // player have money less than 10 -> can't continue game
				System.out.println("Bankrupt!");
				return;
			}
			
			// if player have money more than 10, can continue game
			while(true) {
				System.out.println("");
				System.out.println("Continue game?");
				System.out.print("Choose by integer: 1. Yes  2. No // ");
				
				sc= new Scanner(System.in);
				
				command = sc.next().trim();
				if(!command_list.contains(command)) { System.out.println("Input valid command"); }
				else { break; }
			}
			
			if(command.equals("1")) { // continue game
				System.out.println("");
				deck = new Deck();
				player.reset_card();
				dealer.reset_card();
				}
			else if(command.equals("2")) { // end game
				System.out.println("Final Score: "
						+ Integer.toString(player.return_money()));
				sc.close();
				break;
				}
			
		}
	}
	
	private static void game_play(Player player, Player dealer, Deck deck) throws InterruptedException {
		int stake;
		
		// Get stake from user input
		Thread.sleep(500); 
		while(true) {
			System.out.print(yellow+"Enter the stake. The stake should be at least 10. (Current money: "
					+ Integer.toString(player.return_money())
					+") // "+exit);
			sc = new Scanner(System.in);
			String user_input = sc.nextLine().trim();
			
			
			try{ // check if valid num is entered
				stake = Integer.parseInt(user_input);
				
				if(stake>player.return_money()) { System.out.println("Stake cannot be bigger than the money you own.");}
				else if(stake>=10) {
					player.change_money(-stake);
					break;
				}
				else { System.out.println("Enter a valid integer."); }
			}
			catch (NumberFormatException ex) {System.out.println("Input should be valid integer.");}
			
		}
		
		Thread.sleep(300); 
		System.out.println("Dealer is mixing the deck.");
		Thread.sleep(400); 
		System.out.println(".");
		Thread.sleep(400); 
		System.out.println(".");
		Thread.sleep(400); 
		System.out.println(".");
		
		
		// init - two cards given to both dealer and player
		dealer.add_card(deck.pick_card());
		dealer.add_card(deck.pick_card());
		
		player.add_card(deck.pick_card());
		player.add_card(deck.pick_card());
		
		Thread.sleep(200); 
		System.out.println("Card given.");
		
		System.out.println("----------");
		Thread.sleep(100); 
		System.out.println("Dealer's deck: "
							+"("+Integer.toString(rule.get_cardSum(dealer))+")");
		dealer.print_card();
		System.out.println("Player's deck: "
							+"("+Integer.toString(rule.get_cardSum(player))+")");
		player.print_card();
		System.out.println("----------");
		
		Thread.sleep(200); 
		standOrHit(player,deck);
		
		Thread.sleep(300); 
		autoSelect(dealer, deck);
		
		System.out.println("Dealer's deck: "
				+"("+Integer.toString(rule.get_cardSum(dealer))+")");
		dealer.print_card();
		System.out.println("----------");
		
		Thread.sleep(300); 
		rule.get_winner(player, dealer, stake);
	}
	
	private static void print_typingText(String color, String message) throws InterruptedException {
		System.out.print(color);
		for(int loc = 0;loc<message.length();loc++) {
			System.out.print(message.charAt(loc));
			Thread.sleep(30);
			}
		System.out.println(exit);
	}
	
	private static void howToPlay() throws InterruptedException { // explaining game rule
		System.out.println("Designed for Single-play");
		System.out.println("");
		
		Thread.sleep(200);
		
		System.out.println(yellow+"How to Play"+exit);
		Thread.sleep(100);
		System.out.println(": As the game start, you recieve two cards from the dealer, and dealer gets one card.");
		Thread.sleep(100);
		System.out.println(": Before opening the card, you can choose to get and extra card(hit) or to stop(stand).");
		Thread.sleep(100);
		System.out.println(": After every player stops, player and dealer both open their cards.");
		Thread.sleep(100);
		System.out.println(": Player wins if he/she has higher total value than the dealer without going over 21");
		Thread.sleep(100);
		System.out.println("  However, if player has lower total value than the dealer or if the value is more than 21, player loses.");
		Thread.sleep(100);
	}
	
	private static void standOrHit(Player player, Deck deck) throws InterruptedException {
		ArrayList<String> command_list = new ArrayList<String>(Arrays.asList("1","2"));
		String command;
		
		while(rule.get_cardSum(player)<21) {
			while(true) {
				System.out.print(yellow+"Choose by integer: 1. Hit (get extra card)  2. Stand (stop) // "+exit);
				sc= new Scanner(System.in);
				command = sc.next().trim();
				
				if(!command_list.contains(command)) { System.out.println("Input valid command"); }
				else { break; }
			}
			
			if(command.equals("1") && rule.get_cardSum(player)<=21) { // hit
				System.out.println("You received an extra card.");
				player.add_card(deck.pick_card());
				Thread.sleep(100); 
				
				System.out.println("----------");
				System.out.println("Player's deck: "
						+"("+Integer.toString(rule.get_cardSum(player))+")");
				player.print_card();
				System.out.println("----------");
				Thread.sleep(100); 
			}
			else if (command.equals("2")) { // stay
				break;
			}
		}
		return;
	}
	
	private static void autoSelect(Player dealer, Deck deck) {
		while(rule.get_cardSum(dealer)<17) {dealer.add_card(deck.pick_card());}
	}
	
	public static void main(String[] args) throws InterruptedException {
		game_start();
	}
}
