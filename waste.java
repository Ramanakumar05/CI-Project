import java.util.ArrayList;
import java.util.Arrays;

public class waste {

    public static void main(String[] args) {
        int arr[]={7, 1, 2, 3, 4, 5, 6};
        alternative_Sort(arr);
    }
    static void alternative_Sort(int arr[])
    {
        Arrays.sort(arr);

        int start=0;
        int end=arr.length-1;
        ArrayList<Integer> list=new ArrayList<>();
        while (start<end) {
            list.add(arr[end]);
            list.add(arr[start]);
            start++;
            end--;
        }
        
        // If the size is odd then add the middle that is start pointing to add this element to the list(result)
        if (arr.length % 2 != 0)
            list.add(arr[start]);
        System.out.println(list);
    }
}