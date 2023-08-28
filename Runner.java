import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.*;

public class Runner {
    public static void main (String [] args)

    {
       Scanner input = null;
       try
       {
           input = new Scanner (new File ("input.txt"));
       }
       catch (IOException e)
       {
           System.out.println ("Can't file the file");
        }
       while (input.hasNextLine())
       {
           WordLadder ladder = new WordLadder(input.next(), input.next());
           System.out.println(ladder.findLadder());
       }

    }

}
