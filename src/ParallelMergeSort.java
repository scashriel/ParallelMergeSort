import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class ParallelMergeSort {
    public static void main(String[] args){
        int arrSize = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of elements in your array"));
        int numThreads = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of threads to use"));

        Integer[] array = new Integer[arrSize];
        for (int i = 0; i < arrSize; i++){
            array[i] = (int)(Math.random() * 100);
        }



        ArrayRepository r = new ArrayRepository(array, numThreads);

        for (int i = 0; i < numThreads; i++){
            (new MergeSort(r)).start();
        }
        
        JOptionPane.showMessageDialog(null, r.toString(),"Sorted Array",JOptionPane.INFORMATION_MESSAGE);
    }
}
