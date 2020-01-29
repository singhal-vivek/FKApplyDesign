import java.util.Scanner; 
class Grid {
		
		 int i,j;
		
		 int n;
		 int[][] board;

		Grid(int tt)
		{
			n = tt;
			board = new int[n][n];
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					board[i][j] = -1;
				}
			}
		}
		public void showBoard() {
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					if(board[i][j]==-1)
					{
						System.out.print(".	");
					}
					if(board[i][j]==0)
					{
						System.out.print("X	");
					}
					if(board[i][j]==1)
					{
						System.out.print("O	");
					}
				}
				System.out.println("");
			}
		}
		int validMove(int a,int b)
		{
			if(a>=0&&a<n&&b>=0&&b<n&&board[a][b]==-1)
			{
				return 1;
			}
			return 0;
		}
		boolean  row(int x,int y)
		{
			int p = board[x][y];
			int a,b,c;
			c=0;
			a=x;
			b=y;
			while(b>=0&&board[x][b] == p&&c<4)
			{
				c++;
				b--;
			}
			b=y+1;
			while(b<n&&board[x][b]==p&&c<4)
			{
				c++;
				b++;
			}
			if(c>=4){
				return true;
			}
			return false;

		}
		boolean dig1(int x,int y)
		{
			int a,b,c;
			a=x;
			b=y;
			c=0;
			int p = board[x][y];
			while(a>=0&&b>=0&&a<n&&b<n&&board[a][b]==p&&c<4)
			{
				c++;
				a--;
				b--;
			}
			a=x+1;
			b=y+1;
			while(a<n&&b<n&&a>=0&&b>=0&&board[a][b]==p&&c<4)
			{
				c++;
				a++;
				b++;
			}
			if(c>=4){
			
				return true;
			}
			return false;
		}
		boolean dig2(int x,int y)
		{
			int a,b,c;
			a=x;
			b=y;
			c=0;
			int p = board[x][y];
			while(a>=0&&b>=0&&a<n&&b<n&&board[a][b]==p&&c<4)
			{
				c++;
				a++;
				b--;
			}
			a=x-1;
			b=y+1;
			while(a<n&&b<n&&a>=0&&b>=0&&board[a][b]==p&&c<4)
			{
				c++;
				a--;
				b++;
			}
			if(c>=4){
				
				return true;
			}
			return false;
		}
		boolean winner(int x,int y)
		{
			
			return row(x,y) || dig1(x,y) || dig2(x,y);
			
		}
}

class Human  {
	int id;
	Human(int x)
	{
		this.id = x;
	}
	
	public void fill(int a,int b,Grid grid)
	{
		
			grid.board[a][b] = id;
		
	}
}

public class HexTicTacToe {

	public static void main(String[] args) {

		Scanner get = new Scanner(System.in); 
		Grid grid = new Grid(20);
		Human h1 = new Human(0);
		Human h2 = new Human(1);
		grid.showBoard();
		int flag = 0;
		int count = 0;
		while(count < 100*100)
		{
			int x,y;
			if(flag == 0)
			{
				System.out.println("Player_1 enter position to fill::");
				x = get.nextInt();
				y = get.nextInt();
				if(grid.validMove(x,y)==1)
				{
					h1.fill(x,y,grid);
					flag = 1;
					count++;
					if(grid.winner(x,y))
					{
						System.out.println("Player_1 Wins");
						grid.showBoard();
						break;
					}
				}
				else
				{
					System.out.println("not a valid move");
				}
			}
			else
			{
				System.out.println("Player_2 enter position to fill::");
				x = get.nextInt();
				y = get.nextInt();
				if(grid.validMove(x,y)==1)
				{
					h2.fill(x,y,grid);
					flag = 0;
					count++;

					if(grid.winner(x,y))
					{
						System.out.println("Player_2 Wins");
						grid.showBoard();
						break;
					}
				}
				else
				{
					System.out.println("not a valid move");
				}
			}
			grid.showBoard();
		}

		if(count == 100*100)
		{
			System.out.println("Draw");
		}

		
}
}
