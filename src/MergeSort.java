//

import java.util.ArrayList;

public class MergeSort extends Thread {
    private ArrayRepository repository;

    public MergeSort(ArrayRepository r){
        this.repository = r;
    }

    public void run(){
        ArrayList<Integer[]> couple;
        Integer[] merged;
        couple = repository.returnTwoArrays();
        while (couple != null){
            merged = repository.merge(couple.get(0), couple.get(1));
            repository.insert(merged);
            couple = repository.returnTwoArrays();
        }
    }

}
