package com.tictactoe;



public class TicTacToe {

	public static void main(String[] args)  {
		
		Board b= new Board();
//		b.getBoard()[0][1]='O';      //this setup is a win for PC
//		b.getBoard()[0][2]='O';
//		b.getBoard()[0][0]='O';
//		b.getBoard()[1][1]='X';
//		b.getBoard()[2][0]='X';
//		b.getBoard()[2][1]='X';
		
		b.print();
		while(!b.isGameFinished()){
			b.minimax(b,true);
			System.out.println("The computer played.");
			b.print();
			if(b.isGameFinished()){
				break;
			}
			b.userPlay();
			b.print();
		}
		
		System.out.println("Game Over.");
		switch (b.getScore()) {
		case 10:
			System.out.println("You lost.");
			break;
		case -10:
			System.out.println("You won.");
			break;
		case 0:
			System.out.println("Draw.");
			break;
		default:
			System.out.println("An error occured! :(");
			break;
		}
	
		
		
		
		
		
		

		
//		while(b.isGameFinished()==false){
//			b.pcPlay(b);
//			b.print();
//			b.userPlay();
//			b.print();
//		}
		

	}
	
	
	
	
	
	

	
}	
