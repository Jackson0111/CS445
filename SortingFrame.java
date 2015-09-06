import java.util.Random;

import javax.swing.JFrame;

public class SortingFrame
{
	public static void main(String[] args) throws InterruptedException
	{
		JFrame frame = new JFrame();
		
		int[] data = randomIntArray(40);
		VisualSortingComponent vsc = new VisualSortingComponent(data);
		frame.setTitle("Sorting Visualization");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(vsc);
		frame.setVisible(true);
		
		Thread.sleep(5000);
		
		//bubbleSort(data,vsc);
		gnomeSort(data,vsc);
		
	}

	public static void bubbleSort(int[] data, VisualSortingComponent vsc) throws InterruptedException
	{
		int size = data.length;
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size - 1; j++)
			{
				if(data[j] > data[j + 1])
				{
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					vsc.repaint();
					Thread.sleep(100);
				}
			}
		}
	}
	
	public static void gnomeSort(int[] data, VisualSortingComponent vsc) throws InterruptedException
	{
		for(int i=1; i<data.length-1; i++)
		{
			if(data[0]>data[1])
			{
				int temp = data[0];
				data[0] = data[1];
				data[1] = temp;
				vsc.repaint();
				Thread.sleep(100);
			}
			if(data[i]>data[i+1])
			{
				int temp = data[i];
				data[i] = data[i + 1];
				data[i + 1] = temp;
				vsc.repaint();
				Thread.sleep(100);
			}
			int j=i;
			while(data[j]<data[j-1] && j>1)
			{
				int current = data[j];
				data[j] = data[j-1];
				data[j-1] = current;
				j--;
				vsc.repaint();
				Thread.sleep(100);
			}
		}
		// TO DO
	}
	
	public static int[] randomIntArray(int size)
	{
		int[] result = new int[size];
		
		for(int i = 1; i <= size; i++)
		{
			result[i - 1] = i;
		}
		
		Random rand = new Random();
		
		for(int i = 0; i < size * 100; i++)
		{
			int first = rand.nextInt(size);
			int second = rand.nextInt(size);
			int temp = result[first];
			result[first] = result[second];
			result[second] = temp;
		}
		
		return result;
	}
}
