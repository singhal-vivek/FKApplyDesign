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

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid();
		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				System.out.println(grid.board[i][j]+ " ");
			}
		}

	}
}