import java.util.Scanner;

public class LinjaGame {

	static Scanner s = new Scanner(System.in);
	
	//the game board
	
	
public static char[][] board = new char[][]{
		{'o','o',' ','o','o','o',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		{'o',' ',' ',' ',' ','#'},
		{'o',' ',' ',' ',' ','#'},
		{'o',' ',' ',' ',' ','#'},
		{'o',' ',' ',' ',' ','#'},
		{'o',' ',' ',' ',' ','#'},
		{'o',' ',' ',' ',' ','#'},
		{'#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};	
		
	//
	/*
		public static char[][] board = new char[][]{
			{'#','#','#','#',' ','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{'#','#',' ','#',' ','#'},
			{'#',' ',' ',' ',' ',' '},
			{' ',' ',' ','o',' ','#'},
			{' ',' ',' ',' ',' ',' '},
			{'o','#','o',' ','o',' '},
			{'o',' ','o','o',' ','o'},
			{'o','o',' ',' ','o','o',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};	
	
	//////////////////////////////////////////////////////////////////	
	//*/
			
			
	//main
	public static void main(String[] args) {
		
		printBoard();
		start();
		
	}//end main
	//////////////////////////////////////////////////////////////////
	
	public static void start(){
		System.out.println("\n\t\tCili lojtar nis lojen? (kuq/zi)");
		String p = s.next();
		if(p.equals("kuq")==false && p.equals("zi")==false){
			System.out.println("Ju lutem vendosni 'kuq' ose 'zi'!");
			start();
		}
		else{
			if(p.equals("kuq")){
				firstMoveRed();
				}
			if(p.equals("zi")){
				firstMoveBlack();
				}	
			}
	}
	
	//board printing function
	public static void printBoard() {
		for(int i=1;i<7;i++){
			System.out.print("   ");
			System.out.print(i);
			
		}
		System.out.print("\n   _______________________\n");
		System.out.print("1 ");
		System.out.print("| ");
		for(int i=0;i<18;i++){
			if(i<6)
				
				System.out.print(board[0][i]+" | ");
				
			if(i>=6){
				if(board[0][i]!=' ')
					
					System.out.print(board[0][i]+" | ");	
				}		
			}
		System.out.println("\n  |___|___|___|___|___|___|");
		
		for(int i=1;i<=6;i++){
			System.out.print(i+1+" ");
			System.out.print("| ");
			for(int j=0;j<=5;j++){
				System.out.print(board[i][j]+" | ");
				}
				System.out.println("\n  |___|___|___|___|___|___|");
			}
		System.out.print("8 ");
		System.out.print("| ");
		
		for(int i=0;i<18;i++){
			if(i<6)
				System.out.print(board[7][i]+" | ");
			if(i>=6){
				if(board[7][i]!=' ')
					System.out.print(board[7][i]+"  | ");	
			}
		}
		System.out.println("\n  |___|___|___|___|___|___|");
		
		
	}//end print board
	//////////////////////////////////////////////////////////////////
	
	//stone number in a row, not including the stone just moved
	public static int getRowElements(int row){
		int rowElements = 0;
		for(int i=0;i<6;i++){
				if(board[row][i]!=' '){
					rowElements++;
					}
				}
		return rowElements-1;
	}//end get row elements

	//the first empty spot index in a row, or -1 if the row is full
	public static int getNextEmpty(int row){
		int empty = -1;
		
		if(row==0||row==7){
			for(int i=0;i<18;i++){
				if(board[row][i]==' '){
					empty=i;
					break;
					}
				}
			}
		
		else{
			for(int i=0;i<6;i++){
				if(board[row][i]==' '){
					empty=i;
					break;
					}
				}
			}
		return empty;
	}//end get next empty
	//////////////////////////////////////////////////////////////////
	
	//first moves
	public static void firstMoveRed(){
 		
		System.out.println("\n\t\tLOJTARI I KUQ\n\t\tLEVIZJA E PARE\n");
		
		System.out.println("Vendosni rreshtin e gurit qe do levizet:");
		int r;
		r = s.nextInt()-1;
		if(r<0||r>7){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 8!");
			firstMoveRed();
		}
		System.out.println("Vendosni kolonen e gurit qe do levizet:");
		int c;
		c = s.nextInt()-1;
		if(c<0||c>5){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 6!");
			firstMoveRed();
		}
		
		//rule checks
		//
		if(board[r][c]==' '||board[r][c]=='#'){
			System.out.println("Pozicioni eshte bosh ose permban gur te lojtarit tjeter!");
			firstMoveRed();
			}
		
		if(r==7){//case when the chosen stone is in the opponent's starting zone
			System.out.println("Guri ndodhet ne zonen fillestare te kundershtarit");
			firstMoveRed();
			}
		
		else{//case when the stone is outside of the starting zone
		if(getNextEmpty(r+1)==-1){
			System.out.println("Rreshti eshte i mbushur!\n");
			firstMoveRed();
			}//end rule checks
		
		//make first move by one row
		else{
			board[r+1][getNextEmpty(r+1)] = board[r][c];
			board[r][c]=' ';
			printBoard();
			
			}
		}

	//asking for the second move
		if(endCheck()[2]!=0){
			System.out.println("\nLoja ka perfunduar");
			if(getNextEmpty(r+1)==0){
				System.out.println("\n***Rreshti nuk ka gure te tjere. Nuk mund te beni levizje te dyte.***");
				endGame(endCheck());
			}
				else{
					System.out.println("\nDoni te beni levizjen e dyte? (po/jo)");
					String t = s.next();		
					if(t.equals("po"))
						secMoveRed(r+1);
					else endGame(endCheck());
					}
	}
		else if(getRowElements(r+1)==0){
			System.out.println("\n***Rreshti nuk ka gure te tjere. Nuk mund te beni levizje te dyte.***");
			firstMoveBlack();
		}
			else secMoveRed(r+1);
			
			
		
	}//end first move red
	
	public static void firstMoveBlack(){
		
System.out.println("\n\t\tLOJTARI I ZI\n\t\tLEVIZJA E PARE\n");
		
		System.out.println("Vendosni rreshtin e gurit qe do levizet:");
		int r;
		r = s.nextInt()-1;
		if(r<0||r>7){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 8!");
			firstMoveBlack();
		}
		System.out.println("Vendosni kolonen e gurit qe do levizet:");
		int c;
		c = s.nextInt()-1;
		if(c<0||c>5){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 6!");
			firstMoveBlack();
		}
			
			//kontrolli i rregullave
			//
			if(board[r][c]==' '||board[r][c]=='o'){
				System.out.println("Pozicioni eshte bosh ose permban gur te lojtarit tjeter!");
				firstMoveBlack();
				}
			
			
			if(r==0){//rasti kur guri eshte ne zonen fillestare te kundershtarit
				System.out.println("Guri ndodhet ne zonen fillestare te kundershtarit");
				firstMoveBlack();
				}
			
			else{//rasti kur guri eshte jashte zones se fillimit
				
			if(getNextEmpty(r-1)==-1){
				System.out.println("Rreshti eshte i mbushur!\n");
				firstMoveBlack();
				}//end rule checks
			
			//behet levizja e pare me nje rresht
			else {
				board[r-1][getNextEmpty(r-1)] = board[r][c];
				board[r][c]=' ';
				printBoard();
				}
			}
		
		//pyetet per levizjen e dyte
			if(endCheck()[2]!=0){
				System.out.println("\nLoja ka perfunduar");
				if(getNextEmpty(r-1)==0){
					System.out.println("\n***Rreshti nuk ka gure te tjere. Nuk mund te beni levizje te dyte.***");
					endGame(endCheck());
				}
					else{
						System.out.println("\nDoni te beni levizjen e dyte? (po/jo)");
						String t = s.next();		
						if(t.equals("po"))
							secMoveBlack(r-1);
						else endGame(endCheck());
						}
		}
			else if(getRowElements(r-1)==0){
				System.out.println("\n***Rreshti nuk ka gure te tjere. Nuk mund te beni levizje te dyte.***");
				firstMoveRed();
			}
				else secMoveBlack(r-1);
	}//mbaroi levizja e pare per te ziun
	//////////////////////////////////////////////////////////////////
	
	//levizja e dyte
	public static void secMoveRed(int row){
		
System.out.println("\n\t\tLOJTARI I KUQ\n\t\tLEVIZJA E DYTE\n");
		
		System.out.println("Vendosni rreshtin e gurit qe do levizet:");
		int r;
		r = s.nextInt()-1;
		if(r<0||r>7){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 8!");
			secMoveRed(row);
		}
		System.out.println("Vendosni kolonen e gurit qe do levizet:");
		int c;
		c = s.nextInt()-1;
		if(c<0||c>5){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 6!");
			secMoveRed(row);
		}
			
			//rule checks
			//
			if(board[r][c]==' '||board[r][c]=='#'){
				System.out.println("Pozicioni eshte bosh ose permban gur te lojtarit tjeter!");
				secMoveRed(row);
				}
			
			if(r==7){
				System.out.println("Guri ndodhet ne zonen fillestare te kundershtarit");
				secMoveRed(row);
				}
		
			//
			if(row==7){//case when the previous move ended in the opponent's starting zone
				
				if(r+1>=7){//if the resulting move ends in the opponent's starting zone
					board[7][getNextEmpty(7)] = board[r][c];
					board[r][c]=' ';
					printBoard();
					if(endCheck()[2]!=0)
						endGame(endCheck());
					else firstMoveBlack();
					}
				
				else if(r+1<7 && getNextEmpty(r+1)==-1){
					System.out.println("Rreshti eshte i mbushur!\n");
					secMoveRed(row);
					}
				
				else{//makes second move
					board[r+1][getNextEmpty(r+1)] = board[r][c];
					board[r][c]=' ';
					printBoard();
					if(endCheck()[2]!=0)
						endGame(endCheck());
					else firstMoveBlack();
					}
				}//end previous comment
		
			//
			else{//case when the previous move ended before the starting zone
				
			if(getRowElements(row)+r>=7){//if this move has to surpass the opponent's starting zone, then it ends on the starting zone
					board[7][getNextEmpty(7)] = board[r][c];
					board[r][c]=' ';
					printBoard();
					if(endCheck()[2]!=0)
						endGame(endCheck());
					else firstMoveBlack();
					}	
				
			else if(getNextEmpty(getRowElements(row)+r)==-1){
				System.out.println("Rreshti eshte i mbushur!\n");
				secMoveRed(row);
				}
			//end rule checks
			
			else{//makes second move
				board[getRowElements(row)+r][getNextEmpty(getRowElements(row)+r)] = board[r][c];
				board[r][c]=' ';
				printBoard();
				if(endCheck()[2]!=0)
					endGame(endCheck());
				else firstMoveBlack();
				}
			}		
	}//end second move red

	public static void secMoveBlack(int row){
		

		System.out.println("\n\t\tLOJTARI I ZI\n\t\tLEVIZJA E DYTE\n");
		
		System.out.println("Vendosni rreshtin e gurit qe do levizet:");
		int r;
		r = s.nextInt()-1;
		if(r<0||r>7){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 8!");
			secMoveBlack(row);
		}
		System.out.println("Vendosni kolonen e gurit qe do levizet:");
		int c;
		c = s.nextInt()-1;
		if(c<0||c>5){
			System.out.println("Ju lutem vendosni nje numer midis 1 dhe 6!");
			secMoveBlack(row);
		}
		
			//rule checks
			//
			if(board[r][c]==' '||board[r][c]=='o'){
				System.out.println("Pozicioni eshte bosh ose permban gur te lojtarit tjeter!");
				secMoveBlack(row);
				}
		
			if(r==0){
				System.out.println("Guri ndodhet ne zonen fillestare te kundershtarit");
				secMoveBlack(row);
				}
			
			//
			if(row==0){//case when the previous first move ended on the opponent's starting zone
				if(r-1<=0){//if the resulting move ends in the opponent's starting zone
					board[0][getNextEmpty(0)] = board[r][c];
					board[r][c]=' ';
					printBoard();
					if(endCheck()[2]!=0)
						endGame(endCheck());
					else firstMoveRed();
					}
				
				else if(getNextEmpty(r-1)==-1 && r-1>0){
					System.out.println("Rreshti eshte i mbushur!\n");
					secMoveBlack(row);
					}
			
				else{//makes second move
					board[r-1][getNextEmpty(r-1)] = board[r][c];
					board[r][c]=' ';
					printBoard();
					if(endCheck()[2]!=0)
						endGame(endCheck());
					else firstMoveRed();
					}
				
				}
			
			//
			else{//case when the previous move ended after the starting zone
				
			if(r-getRowElements(row)<=0){//if this move has to surpass the opponent's starting zone, then it ends on the starting zone
				board[0][getNextEmpty(0)] = board[r][c];
				board[r][c]=' ';
				printBoard();
				if(endCheck()[2]!=0)
					endGame(endCheck());
				else firstMoveRed();
				}
		
			else if(getNextEmpty(r-getRowElements(row))==-1){
				System.out.println("Rreshti eshte i mbushur!\n");
				secMoveBlack(row);
				}
			//end rule checks

			//makes second move
			else{
				board[r-getRowElements(row)][getNextEmpty(r-getRowElements(row))] = board[r][c];
				board[r][c]=' ';
				printBoard();
				if(endCheck()[2]!=0)
					endGame(endCheck());
				else firstMoveRed();
				}
			}		
	}//end second move black
	//////////////////////////////////////////////////////////////////
	
	//game end
	public static int[] endCheck(){
		
		int k=0,m=0;
		int count = 0;
		int check = 0;//game not over
		int[] sum = new int[3];//point sum variables(0,1) and winner or tie holder(2)
		
		//game end check
		for(int i=0;i<7;i++){
			if(i==0){
				//count=0;
				for(int j=0;j<18;j++){
					if(board[0][j]=='#'){
						count++;
					}
					if(board[0][j]=='o'){
						check=2;
						break;
					}
						
				}//end first row elements checking
			}//end first row checking
			
			
			if(count==12 && check!=2 && i==0){
				check=1;//game over
				break;
				}
			
			else if(i>0 && check==0){   
					for(int j=0;j<6;j++){
						if(board[i][j]=='#'){
							count++;
							k=i;
							}
						if(board[i][j]=='o'){
							m=i;
							break;
							}
						}
					}
			if(count<12 && m!=0)
				break;
			if(count==12 && k==m)
				break;
			if(count==12 && k<m){
				check=1;//game over
				break;
				}
			
				
		}//end row loop
		//end game end check
		
		//point calculation
		if(check==1){
			for(int i=0;i<=7;i++){
			if(i==0){
				for(int j=0;j<18;j++){
					if(board[i][j]=='#')
						sum[0]+=5;
					}
				}
			
			else{
				for(int j=0;j<6;j++){
					if(i==1 && board[i][j]=='#')
						sum[0]+=3;
					if(i==2 && board[i][j]=='#')
						sum[0]+=2;
					if(i==3 && board[i][j]=='#')
						sum[0]+=1;
					if(i==4 && board[i][j]=='o')
						sum[1]+=1;
					if(i==5 && board[i][j]=='o')
						sum[1]+=2;
					if(i==6 && board[i][j]=='o')
						sum[1]+=3;
					}
				}
			if(i==7){
				for(int j=0;j<18;j++){
					if(board[i][j]=='o')
					sum[1]+=5;
					}
				}
			}
		}//end point calculation
		
		//winner setting, or tie
		if (check==1 && sum[0]>sum[1])
			sum[2]=2;//black wins
		if(check==1 && sum[1]>sum[0])
			sum[2]=1;//red wins
		if(check==1 && sum[0]==sum[1])
			sum[2]=3;//tie
		
		return sum;

	}
	
	public static void endGame(int sum[]){
		System.out.println("\n\t\tLOJA PERFUNDOI\n\t\t_______________________\n\n\t\tLojtari i ZI:\t"+sum[0]+" pike\n\t\tLojtari i KUQ:\t"+sum[1]+" pike\n\t\t_______________________");
		if (sum[2]==1)
			System.out.println("\n\t\tLojtari i KUQ fiton!");
		if (sum[2]==2)
			System.out.println("\n\t\tLojtari i ZI fiton!");
		if (sum[2]==3)
			System.out.println("\n\t\tBARAZIM!");
	}
	//////////////////////////////////////////////////////////////////

}//end class