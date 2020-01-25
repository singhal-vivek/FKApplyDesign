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
		Human h1 = new Human(1);
		Human h2 = new Human(2);
		grid.showBoard();
		int flag = 1;
		int count = 0;
		while(count < 9)
		{
			int x,y;
			if(flag == 1)
			{
				System.out.println("Player_1 enter position to fill::");
				x = get.nextInt();
				y = get.nextInt();
				if(grid.validMove(x,y)==1)
				{
					h1.fill(x,y,grid);
					flag = 2;
					count++;
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
					flag = 1;
					count++;
				}
				else
				{
					System.out.println("not a valid move");
				}
			}
			grid.showBoard();
		}
	}
}