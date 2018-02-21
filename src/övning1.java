import java.util.Arrays;

/**
 * Created by Niklas on 2018-01-18.
 */
public class övning1 {
    public static void main(String[] args)
    {
        övning1 övn = new övning1();
        övn.övningen();
    }

    public void övningen(){
        int[] array = {1,4,2,1,4,5,1,2,5,5};
        System.out.println(Arrays.toString(array));
        set(array,15,14);
        System.out.println(Arrays.toString(array));
    }

    private void set(int[] array, int num, int index)
    {
        //if(index > array.length - 1){
        //    throw new IndexOutOfBoundsException();
        //}

        int lastIndex = array.length;
        while(--lastIndex > index)
        {
            array[lastIndex] = array[lastIndex-1];
        }
        array[index] = num;
    }
}
