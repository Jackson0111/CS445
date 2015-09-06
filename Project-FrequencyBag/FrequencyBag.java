import java.util.ArrayList;


public class FrequencyBag<T>
{
	private Node firstNode;
	private int numberOfEntries;
	
	// TO DO: Instance Variables
	
	/**
	 * Constructor
	 * Constructs an empty frequency bag.
	 */
	public FrequencyBag()
	{
		firstNode=null;
		numberOfEntries=0;
		// TO DO
	}
	
	/**
	 * Adds new entry into this frequency bag.
	 * @param aData the data to be added into this frequency bag.
	 */
	public void add(T aData)
	{
		if(firstNode==null)
		{
			firstNode=new Node(aData, 1, null);
			numberOfEntries++;
		}
		else
		{
			Node currentNode=firstNode;
			while(currentNode.next != null)
			{
				if(currentNode.data.equals(aData))
				{
					currentNode.frequency++;
					numberOfEntries++;
					return;
				}
				else
					currentNode=currentNode.next;
			}
			if(currentNode.data.equals(aData))
			{
				currentNode.frequency++;
				numberOfEntries++;
			}
			else
			{
				currentNode.next=new Node(aData, 1, null);
				numberOfEntries++;
			}
		}
		// TO DO
	}
	/**
	 * Gets the number of occurrences of aData in this frequency bag.
	 * @param aData the data to be checked for its number of occurrences.
	 * @return the number of occurrences of aData in this frequency bag.
	 */
	public int getFrequencyOf(T aData)
	{
		if(firstNode==null)
		{
			return 0;
		}
		Node currentNode = firstNode;
		while(currentNode !=null)
		{
			if(currentNode.data.equals(aData))
			{
				return currentNode.frequency;
			}
				currentNode=currentNode.next;
		}
		return 0;
		// TO DO
	}

	/**
	 * Gets the maximum number of occurrences in this frequency bag.
	 * @return the maximum number of occurrences of an entry in this
	 * frequency bag.
	 */
	public int getMaxFrequency()
	{
		ArrayList<Integer> numberOfFrequency = new ArrayList<Integer>();
		Node currentNode = firstNode;
		if(currentNode==null)
		{
			return 0;
		}
		else
		{
			while(currentNode.next!=null)
			{
				numberOfFrequency.add(currentNode.frequency);
				currentNode=currentNode.next;
			}
		}
		int max=numberOfFrequency.get(0);
		for(int i=1; i<numberOfFrequency.size();i++)
		{
			if(numberOfFrequency.get(i)>max)
			{
				max=numberOfFrequency.get(i);
			}
		}
		return max;
		// TO DO
	}
	
	/**
	 * Gets the probability of aData
	 * @param aData the specific data to get its probability.
	 * @return the probability of aData
	 */
	public double getProbabilityOf(T aData)
	{
		Node currentNode = firstNode;
		double probability=0.00;
		while(currentNode!=null)
		{
			if(currentNode.data.equals(aData))
			{
				probability=(double)((double)currentNode.frequency)/((double)size());
				return probability;
			}
			currentNode=currentNode.next;
		}
		return probability;
		
		// TO DO
	}

	/**
	 * Empty this bag.
	 */
	public void clear()
	{
		firstNode=null;
		numberOfEntries=0;
		// TO DO
	}
	
	/**
	 * Gets the number of entries in this bag.
	 * @return the number of entries in this bag.
	 */
	public int size()
	{
		Node currentNode = firstNode;
		int size=0;
		if(firstNode==null)
		{
			return size;
		}
		while(currentNode!=null)
		{
			size=currentNode.frequency+size;
			currentNode=currentNode.next;
		}
		return size;
		//return numberOfEntries;
		// TO DO
	}
	private class Node
	{
		
		private T data;
		private int frequency;
		private Node next;

		private Node(T aData, int aFrequency, Node nextNode)
		{
			next=nextNode;
			data=aData;
			frequency=aFrequency;
		}
	}
}
