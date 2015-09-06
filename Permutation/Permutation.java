import java.util.ArrayList;


public class Permutation
{
	public static ArrayList<ArrayList<Integer>> permutation(final ArrayList<Integer> list)
	{
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> numList = new ArrayList<Integer>(list);
		ArrayList<ArrayList<Integer>> perms;
		
		int firstNumber;
		result.add(numList);
		
		if(numList.size() == 1)
		{
			return result;
		}
		else
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp = result.remove(0);
			firstNumber = numList.remove(0);
			perms = permutation(numList);
			for(ArrayList<Integer> array : perms)
			{
				for(int i = 0; i <= array.size(); i++)
				{
					ArrayList<Integer> copyArray = (ArrayList<Integer>)array.clone();
					copyArray.add(i,firstNumber);
					result.add(copyArray);
				}
			}
		} 
		return result;



		// TO DO
	}
}


