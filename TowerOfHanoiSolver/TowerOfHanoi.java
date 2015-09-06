import java.util.*;

public class TowerOfHanoi
{
	int numberOfDiscs;
	ArrayList<Integer> firstTower;
	ArrayList<Integer> secondTower;
	ArrayList<Integer> thirdTower;
	

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
		firstTower = new ArrayList<Integer>(numberOfDiscs);
		secondTower = new ArrayList<Integer>(0);
		thirdTower = new ArrayList<Integer>(0);
		for(int i = 1; i <= numberOfDiscs; i++)
			firstTower.add(numberOfDiscs - i);
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
		int[] result;
		switch(tower){
			case 0:
				result = new int[getNumberOfDiscs(0)];
				if(getNumberOfDiscs(0) > 0)
					for(int i = 0; i < result.length; i++)
						result[i] = firstTower.get(i); 
				break;
				
			case 1:
				result = new int[getNumberOfDiscs(1)];
				if(getNumberOfDiscs(1) > 0)
					for(int i = 0; i < result.length; i++)
						result[i] = secondTower.get(i); 
				break;
			case 2:
				result = new int[getNumberOfDiscs(2)];
				if(getNumberOfDiscs(2) > 0)
					for(int i = 0; i < result.length; i++)
						result[i] = thirdTower.get(i); 
				break;
			default:
				return null;
		}
		return result;
	}
	
	/* Gets the total number of discs in this Towers of Hanoi
	 * @return the total number of discs in this Towers of Hanoi
	 */
	public int getNumberOfDiscs()
	{
		return numberOfDiscs;
	}
	
	/* Gets the number of discs on a tower.
	 * @param tower the tower identifier (0, 1, or 2)
	 * @return the number of discs on the tower.
	 */
	public int getNumberOfDiscs(int tower)
	{
		
		switch(tower){
		case 0:
			return firstTower.size();
		case 1:
			return secondTower.size();
		case 2:
			return thirdTower.size();
		default:
			return 0;
		}
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
		int index;
		int index2;
		switch(fromTower){
		case 0:
			index = firstTower.size() - 1;
			if(index+1 < 0)
				return false;
			if(toTower == 1){
				secondTower.add(firstTower.get(index));
				index2 = secondTower.size() - 1;
				if(index2 > 1){
					if(secondTower.get(index2) > secondTower.get(index2-1)){
						secondTower.remove(index2);
						return false;
					}
				}
				firstTower.remove(index);
				return true;
			}
			if(toTower == 2){
				thirdTower.add(firstTower.get(index));
				index2 = thirdTower.size() - 1;
				if(index2 > 1){
					if(thirdTower.get(index2) > thirdTower.get(index2-1)){
						thirdTower.remove(index2);
						return false;
					}
				}
				firstTower.remove(index);
				return true;
			}
			return false;
			
		case 1:
			index = secondTower.size() - 1;
			if(index+1 < 0)
				return false;
			if(toTower == 0){
				firstTower.add(secondTower.get(index));
				index2 = firstTower.size() - 1;
				if(index2 > 1){
					if(firstTower.get(index2) > firstTower.get(index2-1)){
						firstTower.remove(index2);
						return false;
					}
				}
				secondTower.remove(index);
				return true;
			}
			if(toTower == 2){
				thirdTower.add(secondTower.get(index));
				index2 = thirdTower.size() - 1;
				if(index2 > 1){
					if(thirdTower.get(index2) > thirdTower.get(index2-1)){
						thirdTower.remove(index2);
						return false;
					}
				}
				secondTower.remove(index);
				return true;
			}
			return false;
			
		case 2:
			index = thirdTower.size() - 1;
			if(index+1 < 0)
				return false;
			if(toTower == 0){
				firstTower.add(thirdTower.get(index));
				index2 = firstTower.size() - 1;
				if(index2 > 1){
					if(firstTower.get(index2) > firstTower.get(index2-1)){
						firstTower.remove(index2);
						return false;
					}
				}
				thirdTower.remove(index);
				return true;
			}
			if(toTower == 1){
				secondTower.add(thirdTower.get(index));
				index2 = secondTower.size() - 1;
				if(index2 > 1){
					if(secondTower.get(index2) > secondTower.get(index2-1)){
						secondTower.remove(index2);
						return false;
					}
				}
				thirdTower.remove(index);
				return true;
			}
			return false;
			
		default:
			return false;
		}
	}
}
