
public class TowerOfHanoi
{
	int[][] towers;
	int[] arrayOfDiscs;
	int numberOfDiscs;
	// TO DO: Instance Variables

	/* Construct the Towers of Hanoi (3 towers) with aNumDisc
	 * on the first tower. Each tower can be identified by an
	 * integer number (0 for the first tower, 1 for the second
	 * tower, and 2 for the third tower). Each disc can be identified
	 * by an integer number starting from 0 (for the smallest disc)
	 * and (aNumDisc - 1) for the largest disc.
	 */
	public TowerOfHanoi(int aNumDiscs)
	{
		numberOfDiscs = aNumDiscs;
		towers = new int[numberOfDiscs][3];
		for(int i=0; i<numberOfDiscs; i++)
		{
			towers[i][0] = i;
			towers[i][1] = -1;
			towers[i][2] = -1;
		}

		// TO DO: Constructor
	}
	
	/* Returns an array of integer representing the order of
	 * discs on the tower (from bottom up). The bottom disc should
	 * be the first element in the array and the top disc should be
	 * the last element of the array. The size of the array MUST
	 * be the number of discs on the tower. For example, suppose
	 * the tower 0 contains the following discs 0,1,4,6,7,8 (from top
	 * to bottom). This method should return the array [8,7,6,4,1,0]
	 * (from first to last). 
	 * @param tower the integer identify the tower number.
	 * @return an array of integer representing the order of discs.
	 */
	public int[] getArrayOfDiscs(int tower)
	{
		arrayOfDiscs = new int[getNumberOfDiscs(tower)];
		int j=0;
		for(int i=getNumberOfDiscs(tower)-1; i>=0; i--)
		{
			arrayOfDiscs[j] = towers[i][tower];
			j++;
		}
		return arrayOfDiscs;
		// TO DO
	}
	
	/* Gets the total number of discs in this Towers of Hanoi
	 * @return the total number of discs in this Towers of Hanoi
	 */
	public int getNumberOfDiscs()
	{
		return numberOfDiscs;
		// TO DO
	}
	
	/* Gets the number of discs on a tower.
	 * @param tower the tower identifier (0, 1, or 2)
	 * @return the number of discs on the tower.
	 */
	public int getNumberOfDiscs(int tower)
	{
		int size=0;
		for(int i=0; i<numberOfDiscs; i++)
		{
			if(towers[i][tower]!=-1)
			{
				size++;
			}
		}
		return size;
		// TO DO
	}
	
	/* Moves the top disc from fromTower to toTower. Note that
	 * this operation has to follow the rule of the Tower of Hanoi
	 * puzzle. First fromTower must have at least one disc and second
	 * the top disc of toTower must not be smaller than the top disc
	 * of the fromTower.
	 * @param fromTower the source tower
	 * @param toTower the destination tower
	 * @return ture if successfully move the top disc from
	 *         fromTower to toTower.
	 */
	public boolean moveTopDisc(int fromTower, int toTower)
	{
		int indexFromTower = towers[fromTower].length - getNumberOfDiscs(fromTower);
		int indexToTower = towers[toTower].length - getNumberOfDiscs(toTower);
		if(towers[fromTower].length > 0)
		{
			if(towers[0][toTower] == -1)
			{
				if(towers[indexFromTower][fromTower] < towers[indexToTower][toTower])
				{
					towers[indexToTower-1][toTower] = towers[indexFromTower][fromTower];
					towers[indexFromTower][fromTower] = -1;
					return true;
				}
				else
				{
					return false;
				}
			}	
		}
		else
		{
			return false;
		}
		return false;
		// TO DO
	}
}
