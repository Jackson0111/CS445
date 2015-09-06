/**
 * It is okay to use ArrayList class but you are not allowed to use any other
 * predefined class supplied by Java.
 */
import java.util.ArrayList;

public class CompressDecompress
{
	/**
	 * Get a string representing a Huffman tree where its root node is root
	 * @param root the root node of a Huffman tree
	 * @return a string representing a Huffman tree
	 */
	
	
	public static String getTreeString(final BinaryNodeInterface<Character> root)
	{
		String treePath = "";
		
		if(root == null)
		{
			return "";
		}
		
		if(root.isLeaf())
		{
			treePath = "L" + root.getData().toString();
			return treePath;
		}
		
		treePath = "I";
		
		if(root.getLeftChild() != null)
		{
			treePath = treePath + getTreeString(root.getLeftChild());	
		}
		if(root.getRightChild() != null)
		{	
			treePath = treePath + getTreeString(root.getRightChild());
		}
		
		return treePath;
	}

	/**
	 * Compress the message using Huffman tree represented by treeString
	 * @param root the root node of a Huffman tree
	 * @param message the message to be compressed
	 * @return a string representing compressed message.
	 */
	
	public static String compress(final BinaryNodeInterface<Character> root, final String message)
	{
		String binaryString = "";
		
		if(message.length() == 0)
		{
			return "";
		}
		
		for(int i = 0; i < message.length(); i++)
		{
			char currentChar = message.charAt(i);
			binaryString = binaryString + getPathTo(root, currentChar);	
		}
		
		return binaryString;
	}
	
	
	private static String getPathTo(final BinaryNodeInterface<Character> root, char c)
	{
		return getPathTo(root,c,"");
	}
	private static String getPathTo(BinaryNodeInterface<Character> root, char c, String path)
	{
	
		if(root.isLeaf())
		{
			if(root.getData() == c)
			{
				return path;
			}
			else
			{
				return null;
			}
		}
		
		if(root.getRightChild() != null)
		{
			String thisPath = getPathTo(root.getRightChild(), c, path+ "1");
			if(thisPath != null)
			{
				return thisPath;
			}
		}
		
		if(root.getLeftChild() != null)
		{
			String thisPath = getPathTo(root.getLeftChild(), c, path + "0");
			if(thisPath != null)
			{
				return thisPath;
			}
		}
		return null;
	}
	
	
	/**
	 * Decompress the message using Huffman tree represented by treeString
	 * @param treeString the string represents the Huffman tree of the
	 * compressed message
	 * @param message the compressed message to be decompressed
	 * @return a string representing decompressed message
	 */
	public static String decompress(final String treeString, final String message)
	{
			BinaryNodeInterface<Character> rootNode = new BinaryNode<Character>();
			
			createHuffmanTree(rootNode, treeString);
			
			//TestHuffmanTree(rootNode);
			
			String outputMessage = message;
			
			if (outputMessage.length() == 0) 
			{
				return outputMessage;
			}
			
			while(outputMessage.charAt(0) == '0' || outputMessage.charAt(0) == '1')
			{
				outputMessage = traverseTree(rootNode, outputMessage);
				outputMessage = outputMessage.substring(1);
			}
			
			return outputMessage;	
	}
	
	
	public static String createHuffmanTree(BinaryNodeInterface<Character> root , String treeString)
	{
		if(treeString.length() == 0)
		{
			return treeString;
		}
		else
		{
			if(treeString.charAt(0) == 'I')
			{
				root.setLeftChild(new BinaryNode<Character>());
				treeString = treeString.substring(1);
				treeString = createHuffmanTree(root.getLeftChild(), treeString);
				if(treeString.length() > 0)
				{
					root.setRightChild(new BinaryNode<Character>());
					treeString = createHuffmanTree(root.getRightChild(), treeString);
				}
				
				return treeString;
			}
			else
			{
				root.setData(treeString.charAt(1));
				treeString = treeString.substring(2);
				return treeString;
			}	
		}
	}
	
	public static String traverseTree(BinaryNodeInterface<Character> root, String binaryString)
	{
		if(binaryString.length() == 0 )
		{
			return binaryString;
		}
		
		if(root.isLeaf())
		{
			binaryString = "!" + binaryString + "" + root.getData();
			return binaryString;
		}
		
		if(binaryString.charAt(0) == '0')
		{
			binaryString = binaryString.substring(1);
			binaryString=traverseTree(root.getLeftChild(),binaryString);
			if(binaryString.charAt(0) == '!')
			{
				return binaryString;
			}	
		}
		
		if(binaryString.charAt(0) == '1')
		{
			binaryString = binaryString.substring(1);
			binaryString=traverseTree(root.getRightChild(),binaryString);
			if(binaryString.charAt(0) == '!')
			{
				return binaryString;
			}
		}
		
		return binaryString;
	}
	
	private static void TestHuffmanTree(BinaryNodeInterface<Character> rootNode) {
		if (rootNode.isLeaf()){
			System.out.println(rootNode.getData());
			return;
		}
		if (rootNode.hasLeftChild()){
			TestHuffmanTree(rootNode.getLeftChild());
		}
		if (rootNode.hasRightChild()){
			TestHuffmanTree(rootNode.getRightChild());
		}
		return;
	}
}
