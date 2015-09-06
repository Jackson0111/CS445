
public class RecursionLinkedList
{
	private Node firstNode;
	private int numberOfEntries;
	
	public RecursionLinkedList()
	{
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public void add(int aData)
	{
		if(numberOfEntries == 0)
		{
			firstNode = new Node(aData);
		}
		else
		{
			firstNode = new Node(aData, firstNode);
		}
		
		numberOfEntries++;
	}
	
	/**
	 * boolean contains(int aData)
	 * 
	 * See whether this RecursionLinkedList contains aData
	 * @param aData  a data to be located
	 * @return true if this RecursionLinkedList contains aData,
	 *         or false otherwise.
	 */
	public boolean contains(int aData)
	{
		return helper(aData, firstNode);
		// TO DO
	}
	
	public boolean helper(int aData, Node currentNode)
	{
		if(currentNode==null)
		{
			return false;
		}
		if(currentNode.data==aData)
		{
			return true;
		}
		return helper(aData, currentNode.next);
	}
	/**
	 * int getFrequencyOf(int aData)
	 * 
	 * Counts the number of times a given data appears in this
	 * RecursionLinkedList.
	 * 
	 * @param aData  the data to be counted
	 * @return the number of times aData appears in this RecursionLinkedList
	 */
	public int getFrequencyOf(int aData)
	{
		return counter(aData, firstNode, 0);
		// TO DO
	}
	
	public int counter(int aData, Node currentNode, int counter)
	{
		if(currentNode!=null)
		{
			if(currentNode.data==aData)
			{
				counter++;
			}
		}
		else
		{
			return counter;
		}
		return counter(aData, currentNode.next, counter);
	}
	/**
	 * String toString()
	 * 
	 * Return a string representation of this RecursionLinkedList. For example,
	 * if this RecursionLinkedList contains 1, 2, 3, 5, 2 and 3 from the first
	 * index to the last index, the returned string should be
	 * "[1,2,3,5,2,3]"
	 * @return the string representation of this RecursionLinkedList.
	 */
	public String toString()
	{
		String representation = "[";
		return strHelper(firstNode, representation);
		
		// TO DO
	}
	public String strHelper(Node currentNode, String rep)
	{
		if(currentNode.next!=null)
		{
			rep+=currentNode.data+",";
		}
		else
		{
			return rep+=currentNode.data+"]";
		}
		return strHelper(currentNode.next, rep);
	}
	/**
	 * int getIndexOf(int aData)
	 * 
	 * Return the index of the first aData where the first index of
	 * the first item in this RecursionLinkedList is 0.
	 * 
	 * @param aData  the data to be located
	 * @return the index of the first aData.
	 */
	public int getIndexOf(int aData)
	{
		return indexHelper(aData, firstNode, 0);
		// TO DO
	}
	public int indexHelper(int aData, Node currentNode, int index)
	{
		if(currentNode!=null)
		{
			if(currentNode.data==aData)
			{
				return index;
			}
		}
		else
		{
			return -1;
		}

		return indexHelper(aData, currentNode.next, index+1);
	}
	private class Node
	{
		private int data;
		private Node next;
		
		private Node(int aData, Node nextNode)
		{
			data = aData;
			next = nextNode;
		}
		
		private Node(int aData)
		{
			this(aData, null);
		}
	}
}
