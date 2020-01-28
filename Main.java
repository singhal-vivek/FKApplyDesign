
import java.util.Scanner; 
class Grid {
		
		 int i,j;
		 int fwin=-1;
		 int n;
		 int[][] board;
		 int[] box;
		Grid(int tt)
		{
			n = tt;
			board = new int[n][n];
			box = new int[n];
			for(i=0;i<n;i++)
			{
				box[i] = -1;
			}
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
					System.out.print(board[i][j] + "   ");
					if(j%3==2)
						System.out.print("  ||   ");
				}
				
				System.out.println("");
				if(i%3 == 2)
				{
				    System.out.println("=====================================================================================================================================================");
				}

			}
		}
		int validMove(int a,int b,int k)
		{
			if(a>=0&&a<n&&b>=0&&b<n&&board[a][b]==-1&&(a/3)*3+b/3==k)
			{
				return 1;
			}
			return 0;
		}
		boolean  row(int x,int y,int rt)
		{
			int p = board[x][y];
			for(i=3*(rt%3);i<3+3*(rt%3);i++)
			{
				if(board[x][i] != p)
					return false;
			}
			return true;

		}
		boolean  coloumn(int x,int y,int rt)
		{
			int p = board[x][y];
			for(i=3*(rt/3);i<3+3*(rt/3);i++)
			{
				if(board[i][y] != p)
					return false;
			}
			return true;

		}
		boolean dig1(int p,int rt)
		{
			if(board[3*(rt/3)+0][0+3*(rt%3)]==p && board[3*(rt/3)+1][1+3*(rt%3)]==p && board[3*(rt/3)+2][2+3*(rt%3)]==p)
				return true;
			return false;
		}
		boolean dig2(int p,int rt)
		{
			if(board[0+3*(rt/3)][2+3*(rt%3)]==p && board[1+3*(rt/3)][1+3*(rt%3)]==p && board[2+3*(rt/3)][0+3*(rt%3)]==p)
				return true;
			return false;
		}
		boolean winner(int x,int y)
		{
			int p = board[x][y];
			int rt = (x/3)*3 + y/3;
			return row(x,y,rt) || coloumn(x,y,rt) || dig1(p,rt) || dig2(p,rt);
			
		}
		boolean fwinner()
		{
			int fk=0;
			for(i=0;i<n/3;i++)
			{
				fk=0;
				for(j=0;j<n/3-1;j++)
				{
					if(box[i*(n/3)+j] != box[i*(n/3)+j+1] || box[i*(n/3)+j]==-1 ||  box[i*(n/3)+j+1]==-1)
					{
						fk=1;
						break;
					}
				}
				if(fk==0)
					return true;
			}
			for(i=0;i<n/3;i++)
			{
				fk=0;
				for(j=0;j<n/3-1;j++)
				{
					if(box[i+j*(n/3)]!=box[i+j*(n/3)+1]||box[i+j*(n/3)]==-1||box[i+j*(n/3)+1]==-1)
					{
						fk=1;
						break;
					}
				}
				if(fk==0)
					return true;
			}
			for(i=0;i<n/3-1;i++)
			{
				fk=0;
				if(box[i*(n/3+1)]!=box[(i+1)*(n/3+1)]||box[i*(n/3+1)]==-1||box[(i+1)*(n/3+1)]==-1)
				{
					fk=1;
					break;
				}
				
			}
			if(fk==0)
				return true;
			j=n/3-1;
			for(i=0;i<n/3-1;i++)
			{
				fk=0;
				if(box[j]!=box[j+(n/3-1)]||box[j]==-1||box[j+(n/3-1)]==-1)
				{
					fk=1;
					break;
				}
				j=j+(n/3)-1;
			}
			if(fk==0)
				return true;
			return false;
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
		int n;
		System.out.println("enter the value of n::");
		
		Scanner get = new Scanner(System.in); 
		n=get.nextInt();
		Grid grid = new Grid(n);
		Human h1 = new Human(0);
		Human h2 = new Human(1);
		grid.showBoard();
		int flag = 0;
		int count = 0;
		int lst=0;
		while(count < n)
		{
			lst = 0;
			int x,y;
			int b;
			if(flag==0)
			{
				System.out.println("Player_0 enter box number to fill::");
				b = get.nextInt();
				if(b<(n/3)*(n/3)&&grid.box[b] == -1)
				{
					count++;
					lst=1;
					int z = 0;
					while(z<9)
					{
						if(flag == 0)
						{
							System.out.println("Player_0 enter position to fill::");
							x = get.nextInt();
							y = get.nextInt();
							if(grid.validMove(x,y,b)==1)
							{
								h1.fill(x,y,grid);
								grid.showBoard();
								flag = 1;
								z++;
								if(grid.winner(x,y))
								{
									System.out.println("Player_0 Wins this block");
									grid.box[b] = 0;
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
							System.out.println("Player_1 enter position to fill::");
							x = get.nextInt();
							y = get.nextInt();
							if(grid.validMove(x,y,b)==1)
							{
								h2.fill(x,y,grid);
								grid.showBoard();
								flag = 0;
								z++;
								if(grid.winner(x,y))
								{
									System.out.println("Player_1 Wins this box");
									grid.box[b] = 1;
									grid.showBoard();
									break;
								}
							}
							else
							{
								System.out.println("not a valid move");
							}
						}
					}
				}
				else
				{
					System.out.println("not a valid box");
				}
			}
			else
			{
				System.out.println("Player_1 enter box number to fill::");
				b = get.nextInt();
				if(b<(n/3)*(n/3)&&grid.box[b] == -1)
				{
					int z = 0;
					count++;
					lst=1;
					while(z<9)
					{
						if(flag == 0)
						{
							System.out.println("Player_0 enter position to fill::");
							x = get.nextInt();
							y = get.nextInt();
							if(grid.validMove(x,y,b)==1)
							{
								h1.fill(x,y,grid);
								grid.showBoard();
								flag = 1;
								z++;
								if(grid.winner(x,y))
								{
									System.out.println("Player_0 Wins this box");
									grid.box[b]= 0;
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
							System.out.println("Player_1 enter position to fill::");
							x = get.nextInt();
							y = get.nextInt();
							if(grid.validMove(x,y,b)==1)
							{
								h2.fill(x,y,grid);
								grid.showBoard();
								flag = 0;
								z++;
								if(grid.winner(x,y))
								{
									System.out.println("Player_1 Wins this box");
									grid.box[b] = 1;
									grid.showBoard();
									break;
								}
							}
							else
							{
								System.out.println("not a valid move");
							}
						}
					}
				}
				else
				{
					System.out.println("not a valid box");
				}
			}
			flag = flag ^ 1;
			if(lst==1&&grid.fwinner())
			{
				System.out.println("player"+ flag + "wins");
				break;
			}
			
		}
	}
}