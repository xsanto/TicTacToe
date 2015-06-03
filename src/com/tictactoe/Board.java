package com.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Board {
	
	private char board[][];   //the board
	private boolean gameFinished;   //game finished flag
	private int score; // score for PC's side. +10 is PC win, -10 is PC lose, 0 is draw
	
	Board(){
		board=new char[3][3];    //initialise a 3x3 array for the board
		this.reset();			//set array to all 'e' which means empty 
		gameFinished=false;		//game is not finished , it is just starting out
		score=-999;				//set default score to 999
	}

	Board(Board otherBoard){
		this.board=new char[3][3];	//initialise and set board to empty 
		this.reset();
		for (int i = 0; i < otherBoard.board.length; i++) {           
			for (int j = 0; j <otherBoard.board.length ; j++) {
				this.board[i][j]=otherBoard.board[i][j];                   //copy each cell from other board to board being constructed 
			}
		}
		this.gameFinished=otherBoard.gameFinished;
		this.score=otherBoard.score;
	}

	public char[][] getBoard() {		//getter for the board array
		return board;					
	}


	public boolean isGameFinished() {
		gameFinished=true;               //set game as finished
		if(calcScore()==10 || calcScore()==-10 || calcScore()==0 ){	//if score of board is 10 or -10 or 0 game is finished
			return gameFinished;					
		}else{
			gameFinished=false;
			return gameFinished;
		}
	}
		
	

	public int minimax(Board b,boolean pcTurn){
		if(b.isGameFinished()){
			return b.calcScore();
		}
		else{
			ArrayList<Board>stateList=generateStates(b,pcTurn);
			ArrayList<Integer> scoreList = new ArrayList<Integer>();
			for (Board state : stateList) {
				scoreList.add(minimax(state,!pcTurn));
			}
			if(pcTurn){
			//	stateList.get(scoreList.indexOf(Collections.max(scoreList))); //this is wrong
				//scoreList.indexOf(Collections.max(scoreList));
				int index=scoreList.indexOf(Collections.max(scoreList));
				this.board=stateList.get(index).getBoard();
				return Collections.max(scoreList);
				
			}
			else{
				int index=scoreList.indexOf(Collections.max(scoreList));
				this.board=stateList.get(index).getBoard();
				return Collections.min(scoreList);
			}
		}
	}

	public void reset() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j]='e';
			}
		}
		
	}
	
	public void print(){
		for (int i = 0; i < board.length; i++) {
				System.out.println(board[i][0]+" "+board[i][1]+" "+board[i][2]);
		}
		//System.out.println("Score for PC is : " + score);
	}
	
	public int calcScore(){
	
		boolean win=true;          //win is true by default (I dunno why )
		boolean lose=false;			//lose is false by default
		
		for (int i = 0; i < board.length; i++) {
				win=( ((board[i][0]=='O') && (board[i][1]=='O') && (board[i][2]=='O'))
						|| ((board[0][i]=='O') && (board[1][i]=='O') && (board[2][i]=='O')) );     // vertical and horizontal win condition for PC
				if(win==true){				//if pc won : game is finished, lose is false, score is +10 
					gameFinished=true;		
					lose=false;
					score=10;
					return score;
				}
		}
		
		win=((board[0][0]=='O') && (board[1][1]=='O') && (board[2][2]=='O'))
				|| ((board[0][2]=='O') && (board[1][1]=='O') && (board[2][0]=='O'));     //diagonal win conditions for PC
		if(win==true){         //if pc won : game is finished, lose is false, score is +10 
			lose=false;
			gameFinished=true;
			score=10;
			return score;
		}
		
		for (int i = 0; i < board.length; i++) {
			lose=( ((board[i][0]=='X') && (board[i][1]=='X') && (board[i][2]=='X'))
					|| ((board[0][i]=='X') && (board[1][i]=='X') && (board[2][i]=='X')) );  //horizontal and vertical lose condition for PC (ie win for user)
			if(lose==true){       
				//if pc lose : score is -10, game is finished, win is false
				win=false;                                            
				gameFinished=true;
				score=-10;
				return score;
			}
		}
		lose=((board[0][0]=='X') && (board[1][1]=='X') && (board[2][2]=='X'))
			|| ((board[0][2]=='X') && (board[1][1]=='X') && (board[2][0]=='X')); //diagonal lose conditions for PC 
		if(lose==true){   //if pc lose : score is -10, game is finished, win is false
			win=false;
			score=-10;
			gameFinished=true;
			return score;
		}
		
		boolean draw=true;
		
		for (int i = 0; i < board.length; i++) {	
			for (int j = 0; j < board.length; j++) {
				draw=draw && (board[i][j]=='O' || board[i][j]=='X'); //game is finished with draw as long as NO cell is empty
			}
		}
		if(draw==true){
			score=0;
			return 0;
		}else{
			return score;
		}
		
	}
	
	public int getScore() {
		return score;
	}

	public void userPlay(){
		Scanner userInput= new Scanner( System.in );
		System.out.println("Your turn.");
		String userPositionString=userInput.next();
		//userInput.close();
		int userPosition=Integer.parseInt(userPositionString);
		switch (userPosition) {
		case 7:
			if(this.board[0][0]=='e'){
				this.board[0][0]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 8:
			if(this.board[0][1]=='e'){
				this.board[0][1]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 9:
			if(this.board[0][2]=='e'){
				this.board[0][2]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 4:
			if(this.board[1][0]=='e'){
				this.board[1][0]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 5:
			if(this.board[1][1]=='e'){
				this.board[1][1]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 6:
			if(this.board[1][2]=='e'){
				this.board[1][2]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 1:
			if(this.board[2][0]=='e'){
				this.board[2][0]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 2:
			if(this.board[2][1]=='e'){
				this.board[2][1]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;
		case 3:
			if(this.board[2][2]=='e'){
				this.board[2][2]='X';
			}else{
				System.out.println("Invalid move");
				userPlay();
			}
			break;

		default:
			System.out.println("Invalid move");
			userPlay();
			break;
		}
	}
	
	

	
	public  ArrayList<Board> generateStates(Board b,boolean pcTurn){
		ArrayList<Board> boardList=new ArrayList<Board>();
		for (int i = 0; i < b.getBoard().length; i++) {
			for (int j = 0; j < b.getBoard().length; j++) {
				if(b.getBoard()[i][j]=='e'){
					Board tempBoard=new Board(b);
					
					if(pcTurn){
						tempBoard.getBoard()[i][j]='O';
					}else{
						tempBoard.getBoard()[i][j]='X';
					}
					
					boardList.add(tempBoard);
				}
			}
		}
		return boardList;
	}
};
