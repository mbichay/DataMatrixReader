/*
	Module #4
	CST 338
		Matthew Bichay
		Julia Diliberto
		Vanessa Ulloa
		James O'Dea
*/

public class AsciiDataMatrix 
{

	public static void main(String[] args) 
	{
		String[] sImageIn =
	      {
	         "                                               ",
	         "                                               ",
	         "                                               ",
	         "     * * * * * * * * * * * * * * * * * * * * * ",
	         "     *                                       * ",
	         "     ****** **** ****** ******* ** *** *****   ",
	         "     *     *    ****************************** ",
	         "     * **    * *        **  *    * * *   *     ",
	         "     *   *    *  *****    *   * *   *  **  *** ",
	         "     *  **     * *** **   **  *    **  ***  *  ",
	         "     ***  * **   **  *   ****    *  *  ** * ** ",
	         "     *****  ***  *  * *   ** ** **  *   * *    ",
	         "     ***************************************** ",  
	         "                                               ",
	         "                                               ",
	         "                                               "

	      };      
            
         
      
      String[] sImageIn_2 =
	      {
	            "                                          ",
	            "                                          ",
	            "* * * * * * * * * * * * * * * * * * *     ",
	            "*                                    *    ",
	            "**** *** **   ***** ****   *********      ",
	            "* ************ ************ **********    ",
	            "** *      *    *  * * *         * *       ",
	            "***   *  *           * **    *      **    ",
	            "* ** * *  *   * * * **  *   ***   ***     ",
	            "* *           **    *****  *   **   **    ",
	            "****  *  * *  * **  ** *   ** *  * *      ",
	            "**************************************    ",
	            "                                          ",
	            "                                          ",
	            "                                          ",
	            "                                          "

	      };
     
      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
     
      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
     
      // create your own message
      dm.readText("What a great resume builder this is!");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
     
      // vanessa test
      dm.readText("Vanessa was here");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      dm.readText("Vanessa was there");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      dm.readText("Vanessa was everywhere");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
	}//	end main()


}//	end AsciiDataMatrix class