import java.util.Random;

/**
 * Created by Niklas on 2018-02-08.
 */
public class SplayWithGetTest {
    public static void main(String[] args)
    {
        SplayWithGet<Integer> spwg = new SplayWithGet<Integer>();
        Random r = new Random();
        for(int i = 0; i < 15; i++)
        {
            spwg.add(new Integer(i*2));
        }
        System.out.print(spwg.toString());
        spwg.zagzag(spwg.root);
        System.out.print(spwg.toString());
    }
}
