public class DataMatrix implements BarcodeIO 
{
   // DATA/VARIABLES
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private final int OFFSET = 1;
   // copy of image scanned in, created by interface or passed into Constructor
   private BarcodeImage image;
   // copy of text read in, passed into Constructor or created by interface method
   private String text;
   // variables that are typically less than MAX_HEIGHT and MAX_WIDTH
   private int actualWidth;
   private int actualHeight;

   // CONSTRUCTORS

   /*
      DataMatrix()
      Default Constructor
      constructs an empty, non-null image and text value
   */
   public DataMatrix() 
   {
      this.image = new BarcodeImage();
      this.actualHeight = 0;
      this.actualWidth = 0;
      this.text = "null";
   }

   /*
      DataMatrix(BarcodeImage)
      Constructor
      sets the image, text is default value, scan method helps avoid duplication
      of code
   */
   public DataMatrix(BarcodeImage image) 
   {
      this();
      this.scan(image);
   }

   /*
      DataMatrix(String)
      sets text, image is default value, readText method avoids duplication of code
   */
   public DataMatrix(String text) 
   {
      this();
      this.readText(text);
   }

   // METHODS

   /*
      readText(String)
      mutator for the String, called by the Constructor
      if text is invalid then false, else true
   */
   public Boolean readText(String text) 
   {
      if (text != null)
      {
         this.text = text;
         return true;
      } 
      return false;
   }   

   /*
      scan(BarcodeImage)
      mutator for image, called by the Constructor
      uses try/catch for CloneNotSupportException

   */
   public Boolean scan(BarcodeImage image) 
   {
      try 
      {
         if (this.image != null)
            this.text = "";

         this.image = (BarcodeImage)image.clone();
         this.actualWidth = computeSignalWidth();
         this.actualHeight = computeSignalHeight();

         return true;
      } 
      catch (CloneNotSupportedException e) 
      {
         // for debugging
         // System.out.println("something" + e.toString()); 
         return false;
      }
   }

   /*
      actualWidth()
      actualHeight()
      accessors for actualWidth and actualHeight variables
   */
   public int actualWidth() { return this.actualWidth; }
   public int actualHeight() { return this.actualHeight; }

   /*
      computeSignalWidth()
      computeSignalHeight
      goes off the bottom lower left corner to compute the image width
      assumes placement of image starting at lower left corner
   */
   private int computeSignalWidth() 
   {
      return this.image.width();
   }

   private int computeSignalHeight() 
   {
      return BarcodeImage.MAX_HEIGHT - this.image.start();
   }

   /*
      generateImageFromText()
      from BarcodeIO Interface
      takes text and converts to binary in order to generate an image
   */
   public Boolean generateImageFromText() 
   {
      String binString; // text represented in binary
      this.actualWidth = this.text.length() + 2;
      this.actualHeight = 9;
      binString = strToBinStr(this.text);
      
      // create left limitation line
      // System.out.println(binString); // for debugging
      for (int row = 0 ; row < actualHeight ; row++)
      {
         this.image.setPixel(row, 0, true);
      }
      
      /*
      // create cols from binary string starting with * and ending with alternating  
      int next=0;
      for (int col = 1 ; col < this.actualWidth - 1 ; col++)
      {
         for (int row = 8 ; row > 0 ; row--)
         {
            switch (row)
            {
            case 8:
               this.image.setPixel(row,col,true);
            case 1:
               if (col%2==0)
                  this.image.setPixel (row,col,true);
            default:
               this.image.setPixel (row,col,binToBool(binString.charAt(next)));
               next++;
            }
         }
      }
      */
         
     // create right open borderline
      for (int row = 0 ; row < actualHeight ; row++)
      {
         if ((row + 1) % 2 == 1)
            this.image.setPixel(row,this.actualWidth - 1,true);
         else 
            this.image.setPixel(row,this.actualWidth - 1,false);
      }
      System.out.println(this.image.getPixel(4,5));
      return true;
   }

   /*
      strToBinStr(String)
      method that converts String to Binary character at a time
   */
   private static String strToBinStr(String text)
   {
       String bString="";
       String temp="";

       for(int i = 0 ; i < text.length() ; i++)
       {
           temp = Integer.toBinaryString(text.charAt(i));
           bString += temp;
       }

       return bString;
   }

   /*
      binToBool(char)
      method that converts a binary value to either true or false
      1 = true
      0 = false
   */
   private Boolean binToBool(char next)
   {
      if (next == '1')
         return true;
      else
         return false;
   }

   /*
      translateImageToText()
      from BarcodeIO Interface
      returns false for null image
      else, loop through pixels to return text values
   */
   public Boolean translateImageToText() 
   {
      if (image == null)
         return false;

      int character;
      for (int i = OFFSET ; i < this.actualWidth - 1 ; ++i)
      {
         character = 0;
         for (int k = OFFSET ; k < this.actualHeight - 1 ; ++k) 
         {
            if (this.image.getPixel(k + this.image.start(),i)) 
            {
               character += key(k);
            }
         }
         this.text += asciiToChar(character);
      }
      return true;
   }

   /*
      displayTextToConsole()
      displays text to console
   */
   public void displayTextToConsole() 
   {
      System.out.println(this.text);
   }

   /*
      displayImageToConsole()
      method that prints the pixels converted from teh text to the console
      does not include extra space around the image
   */
   public void displayImageToConsole() 
   {
      printBorder();

      for (int i = this.image.start(); i < BarcodeImage.MAX_HEIGHT; ++i)
      {
         System.out.print('|');

         for (int k = 0; k < this.actualWidth; ++k)
         {
            if (this.image.getPixel(i,k)){
               System.out.print(DataMatrix.BLACK_CHAR);
            }
            else
            {
               System.out.print(DataMatrix.WHITE_CHAR);
            }
         }
         System.out.print("|\n");
      }
      printBorder();
   }

   /*
      printBorder()
      method used in displayImageToConsole() in order to print outside borders
      for the image.
   */
   private void printBorder()
   {
      for (int i = 0 ; i < this.actualWidth+2; ++i)
         System.out.print('-');
      System.out.println();
   }


   /*
      key(int)
      method uses to take int and return another int used in translateImageToText
   */
   private int key(int num) 
   {
      switch(num)
      {
         case 1:
            return 128;
         case 2:
            return 64;
         case 3:
            return 32;
         case 4:
            return 16;
         case 5:
            return 8;
         case 6:
            return 4;
         case 7:
            return 2;
         default:
            return 1;
      }
   }

   /*
      asciiToChar(int)
      converts int to char
   */
   private char asciiToChar(int num) 
   {
      return (char)num;
   }



   //This private method will make no assumption about the placement
   //of the "signal" within a passed-in BarcodeImage.  In other words,
   //the in-coming BarcodeImage may not be lower-left justified.
   /*
      clearImage()
      clears the image and fills it with all blank (white) values
   */
   public void clearImage() 
   {
      for (int row = 0 ; row < BarcodeImage.MAX_HEIGHT ; row++)
         for (int column = 0 ; column < BarcodeImage.MAX_WIDTH ; column++)
         {
            this.image.setPixel(row,column,false);
         }

      this.actualHeight = 0;
      this.actualWidth = 0;
      
   }
}//   end DataMatrix class