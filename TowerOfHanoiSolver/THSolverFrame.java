import javax.swing.JFrame;

public class THSolverFrame
{
	public static void main(String[] args) throws InterruptedException
	{
		int numberOfDiscs = 10;
		TowerOfHanoi towers = new TowerOfHanoi(numberOfDiscs);
		THComponent thc = new THComponent(towers);
		int source=0;
		int dest=2;
		int help=1;
		
		JFrame frame = new JFrame();
		frame.setTitle("Tower of Hanoi");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(thc);
		
		frame.setVisible(true);
		
		Thread.sleep(5000);
		
		solveTower(towers, thc,numberOfDiscs, source, dest, help);
		
		System.out.println("DONE!!!");
	}
	
	public static void solveTower(TowerOfHanoi towers, THComponent thc,  int numOfDiscs, int source, int dest, int help) throws InterruptedException
	{
		if(numOfDiscs==1)
		{
			towers.moveTopDisc(source, dest);
			thc.repaint();
			Thread.sleep(10);
		}
		else
		{
			solveTower(towers, thc, numOfDiscs-1, source, help, dest);
			solveTower(towers, thc, 1, source, dest, help);
			solveTower(towers, thc, numOfDiscs-1, help, dest, source);
		}
		
		
		// TO DO
	}
}
