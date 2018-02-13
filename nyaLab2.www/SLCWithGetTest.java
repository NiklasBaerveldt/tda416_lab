/**
 * Created by Niklas on 2018-02-06.
 */
public class SLCWithGetTest {
    public static void main(String[] args)
    {
         SLCWithGet<Integer> slc = new SLCWithGet<>();
         for(int i = 0; i <= 16; i+=2)
         {
             slc.add(new Integer(i));
         }
         for(int i = 1; i <= 15; i+=2)
         {
             slc.add(new Integer(i));
         }
         for(int i = 0; i <= 16; i+=2)
         {
             Integer integ = slc.get(new Integer(i));

             if(integ!=null)
                 System.out.println(i + " integer " + integ.intValue());
         }
    }
}
