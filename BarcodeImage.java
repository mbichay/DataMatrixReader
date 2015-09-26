import java.util.Arrays;

public class BarcodeImage implements Cloneable {

	//The exact internal dimensions of 2D data. 
	public static final int MAX_HEIGHT = 30;
	public static final int MAX_WIDTH = 65;

	//This is where to store your image.
	//If the incoming data is too large,
	//instantiate memory anyway, but leave
	//it blank (white). This data will be false
	//for elements that are white, and true for elements that are black.
	private Boolean[][] image_data;


	// Default Constructor
	//instantiates a 2D array (MAX_HEIGHT x MAX_WIDTH) and 
	//stuffs it all with blanks (false).
	public BarcodeImage() {
		this.image_data = new Boolean[BarcodeImage.MAX_HEIGHT][BarcodeImage.MAX_WIDTH];
		for (Boolean[] row : this.image_data)
			Arrays.fill(row,false);
	}


	// Default Constructor
	//takes a 1D array of Strings and converts it to the internal 2D array of booleans. 
	public BarcodeImage(String[] str_data) {
		//do shit here matt
	}


	//
	public Boolean getPixel(int row, int col) {
		// do shit here
		if (indexInvalid(row,col))
			return false;
		return this.image_data[row][col];
	}


	public Boolean setPixel(int row, int col, Boolean value) {
		if (indexInvalid(row,col))
			return false;

		this.image_data[row][col] = value;
		return true;
	}


	private Boolean indexInvalid(int row, int col) {
		if (row > BarcodeImage.MAX_HEIGHT || row < 0 || 
			col > BarcodeImage.MAX_WIDTH || col < 0)
			return false;
		return true;
	}

	private Boolean checkImageSize(String[] data) {
		if (data.length >= BarcodeImage.MAX_HEIGHT || data == null)
			return false;
		else
			for(String dStr : data)
				if (dStr.length() >= BarcodeImage.MAX_WIDTH)
					return false;
		return true;
	}
}