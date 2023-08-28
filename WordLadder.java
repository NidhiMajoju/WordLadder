import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordLadder
{
    private String start;
    private String end;
    private Queue<Stack<String>> ladder;
    private Set <String> set;

    public WordLadder (String start, String end)
    {
        this.start = start.toUpperCase();
        this.end = end.toUpperCase();
        ladder = new LinkedList<>();
        Stack<String> wrd= new Stack<>();
        wrd.add(start);
        ladder.offer(wrd);
        set = new HashSet<>();
        makeDictionary();
    }

    public void makeDictionary ()
    {
        Scanner input = null;
        int count = 0;
        try
        {
            input = new Scanner (new File("dictionary.txt"));
        }
        catch (IOException e)
        {
            System.out.println("Can't find the file");
        }
        while (input.hasNextLine())
        {
               set.add(input.nextLine());
        }
    }
    public Queue<String> findWords (String word)
    {
        Queue<String> words = new LinkedList<>();
        String nextWord = null;
        char [] chars = word.toUpperCase().toCharArray();
        char c = 'a';
        for (int i = 0; i<chars.length; i++)
        {
            c = chars[i];
            for (char ch = 'A'; ch <= 'Z'; ch++)
            {
                chars[i] = ch;
                nextWord = String.valueOf(chars);
                if (set.contains(nextWord) && !(nextWord.equals(word)))
                {
                    words.offer(nextWord);
                    set.remove(nextWord);
                }
            }
            chars[i] = c;
        }
        return words;
    }
    public String makeStack ()
    {
        Stack<String> top = ladder.poll();
        Queue<String> words = findWords(top.peek());
        while (!words.isEmpty())
        {
            Stack <String> current = (Stack<String>) top.clone();
            current.push (words.poll());
            ladder.offer(current);
            if (current.peek().equals(end))
            {
                break;
            }
        }
        if (ladder.isEmpty())
            return null;
        return ladder.peek().peek();
    }
    public String findLadder ()
    {
        if (start.length()!=end.length() || !set.contains(start) || !set.contains(end))
        {
            return ("No Ladder between " + start.toLowerCase() + " and " + end.toLowerCase());
        }
        String result = start;
        while (!result.equals(end))
        {
            result = makeStack();
            if (result == null)
               return ("No Ladder between " + start.toLowerCase() + " and " + end.toLowerCase());

        }
        return ("Found a ladder! >>> " + ladder.peek().toString().toLowerCase());
    }
}