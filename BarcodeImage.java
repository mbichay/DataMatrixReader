import java.util.Arrays;
import java.util.ArrayList;

public class BarcodeImage implements Cloneable 
{

	// The exact internal dimensions of 2D data. 

	//	VARIABLES
	/*
		where the image will be stored.
		for large data memory will be instantiated anyway but false
		white (blank) = false
		black = true
	*/
	public static final int MAX_HEIGHT = 30;
	public static final int MAX_WIDTH = 65;
	private Boolean[][] image_data;
	private int start;
	private int width;

	//	CONSTRUCTORS

	/*
		BarcodeImage()
		Default Constructor
		instantiates a 2D array (MAX_HEIGHT X MAX_WIDTH) with blank values
	*/
	public BarcodeImage() 
	{
		this.image_data = new Boolean[BarcodeImage.MAX_HEIGHT][BarcodeImage.MAX_WIDTH];
		this.start = 0;
		this.width = 0;
		for (Boolean[] row : this.image_data)
			Arrays.fill(row,false);
	}

	/*
		BarcodeImage(String[])
		Constructor
		takes 1D array of Strings and converts to 2D array of boolean values.
	*/ 
	public BarcodeImage(String[] str_data) 
	{
		this.image_data = new Boolean[BarcodeImage.MAX_HEIGHT][BarcodeImage.MAX_WIDTH];
		ArrayList<String> strDataTemp = new ArrayList<String>();

		for (String str : str_data)
			if (!str.trim().equals(""))
				strDataTemp.add(str.trim()); // Requires Error checking

		String[] strData = strDataTemp.toArray(new String[strDataTemp.size()]);
		falseParityCC(strData);
		computeSignalWidthAndScanningStart(strData);
		insertImageData(strData);
	}

	//	METHODS

	/*
		start()
		width()
		accessor for private start and width variables
	*/
	public int start() { return this.start; }
	public int width() { return this.width; }

	/*
		getPixel(int,int)
		accessor for each pixel in the image
	*/
	public Boolean getPixel(int row, int col) 
	{
		if (!indexInvalid(row,col))
		{
			return false;
		}

		return this.image_data[row][col];
	}

	/*
		setPixel(int,int,boolean)
		mutator for each pixel
	*/
	public Boolean setPixel(int row, int col, Boolean value) 
	{
		if (indexInvalid(row,col))
			return false;

		this.image_data[row][col] = value;

		return true;
	}

	/*
		indexInvalid(int,int)
		helper method that checks if row and column values are valid or invalid
	*/
	private Boolean indexInvalid(int row, int col) 
	{
		if (row > BarcodeImage.MAX_HEIGHT || row < 0 || 
			col > BarcodeImage.MAX_WIDTH || col < 0)
			return false;

		return true;
	}

	/*
		checkImageSize(String[])
		method to check image size, false if too large or null
	*/
	private Boolean checkImageSize(String[] data) 
	{
		if (data.length >= BarcodeImage.MAX_HEIGHT || data == null)
			return false;
		else
			for(String dStr : data)
				if (dStr.length() >= BarcodeImage.MAX_WIDTH)
					return false;

		return true;
	}

	/*
		falseParityCC(String[])
		method to error check from the trim() method
	*/
	private void falseParityCC(String[] strData) 
	{
		int width = strData[strData.length-1].length();
		for (int i = 0; i < strData.length; ++i)
		{
			while(strData[i].length() < width) 
			{
				strData[i] += " ";
			}
		}
	}

	/*
		insertImageData(String[])
		helper method that inserts string into the boolean array
	*/
	private void insertImageData(String[] imgData) 
	{
		for (int i = this.start; i < BarcodeImage.MAX_HEIGHT; ++i)
		{
			for (int k = 0; k < this.width; ++k) 
			{
				if (imgData[i-this.start].charAt(k) == ' ') 
				{
					this.image_data[i][k] = false;
				} 
				else 
				{
					this.image_data[i][k] = true;
				}
			}
		}
	}

	/*
		displayToConsole()
		method that displays the image to the console, used for debugging
	*/
	public void displayToConsole() 
	{
		for (int i = this.start; i < MAX_HEIGHT; ++i)
		{
			for (int k = 0; k < width; ++k)
			{
				if (this.image_data[i][k])
					System.out.print('*');
				else
					System.out.print(' ');
			}

			System.out.println();
		}
	}

	/*
		computeSignalWidthAndScanningStart(String[])
		method to fill start and width variables with values
	*/
	private void computeSignalWidthAndScanningStart(String[] imgData)
	{
		this.start = BarcodeImage.MAX_HEIGHT - imgData.length;
		this.width = imgData[imgData.length-1].length();
	}

	/*
		clone()
		throws exception
	*/
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		return super.clone();
	}

}//	end BarcodeImage class