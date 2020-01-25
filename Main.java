package Design;

class Grid {
		int[][] board = new int[3][3];

		Grid()
		{
			int i,j;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					this.board[i][j] = -1;
				}
			}
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
	int validMove(int a,int b,Grid grid)
	{
		if(a>=0&&a<3&&b>=0&&b<3&&grid.board[a][b]==-1)
		{
			return 1;
		}
		return 0;
	}
	public void fill(int a,int b,Grid grid)
	{
		if(validMove(a,b,grid)==1)
		{
			grid.board[a][b] = id;
		}
	}
}

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid();
		Human h1 = new Human(1);
		Human h2 = new Human(2);


	}
}