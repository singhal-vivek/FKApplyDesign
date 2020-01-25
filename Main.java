package Design;

import java.util.Scanner; 
class Grid {
		int[][] board = new int[3][3];
		 int i,j;
		Grid()
		{
			
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					this.board[i][j] = -1;
				}
			}
		}
		public void showBoard() {
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					System.out.print(this.board[i][j] + "	");
				}
				System.out.println("");
			}
		}
		int validMove(int a,int b)
		{
			if(a>=0&&a<3&&b>=0&&b<3&&this.board[a][b]==-1)
			{
				return 1;
			}
			return 0;
		}
		boolean  row(int x,int y)
		{
			int p = this.board[x][y];
			for(i=0;i<3;i++)
			{
				if(this.board[x][i] != p)
					return false;
			}
			return true;

		}
		boolean  coloumn(int x,int y)
		{
			int p = this.board[x][y];
			for(i=0;i<3;i++)
			{
				if(this.board[i][y] != p)
					return false;
			}
			return true;

		}
		boolean dig1(int p)
		{
			if(this.board[0][0]==p && this.board[1][1]==p && this.board[2][2]==p)
				return true;
			return false;
		}
		boolean dig2(int p)
		{
			if(this.board[0][2]==p && this.board[1][1]==p && this.board[2][0]==p)
				return true;
			return false;
		}
		boolean winner(int x,int y)
		{
			int p = this.board[x][y];
			return row(x,y) || coloumn(x,y) || dig1(p) || dig2(p);
			
		}
}

interface Player {

	void fill(int a,int b,Grid grid);
}

class Human implements Player {
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

public class Main {

	public static void main(String[] args) {
		Scanner get = new Scanner(System.in); 
		Grid grid = new Grid();
		Human h1 = new Human(0);
		Human h2 = new Human(1);
		grid.showBoard();
		int flag = 0;
		int count = 0;
		while(count < 9)
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

		if(count == 9)
		{
			System.out.println("Draw");
		}

	}
}
