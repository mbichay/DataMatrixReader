public interface BarcodeIO 
{
	/*
		scan(BarcodeImage)
		accepts a BarcodeImage and stores a copy (either exact or refined).
	*/
	public Boolean scan(BarcodeImage bc);

	/*
		readText(String)
		accepts a String that will be encoded in an image.
	*/
	public Boolean readText(String text);

	/*
		generateImageFromText()
		Method that looks at internally stored String (text).
		That String (text) is used to produce a BarcodeImage
	*/
	public Boolean generateImageFromText();

	/*
		translateImageToText()
		Method that looks at internally stored image.
		That image is used to produce a String(text)
	*/
	public Boolean translateImageToText();

	/*
		displayTextToConsole()
		prints out the text string to the console.
	*/
	public void displayTextToConsole();

	/*
		displayImageToConsole() Description:
		prints out the image to the console.  In our implementation,
		we will do this in the form of a dot-matrix of blanks and asterisks
	*/
	public void displayImageToConsole();


}//	end interface BardcodeIO