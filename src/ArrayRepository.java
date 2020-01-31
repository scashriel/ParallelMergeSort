import java.util.ArrayList;

public class ArrayRepository {
    private ArrayList<Integer[]> repository;
    private int maxThreads, waiting;
    private boolean done;

    public ArrayRepository(Integer[] a, int maxThreads){
        this.maxThreads = maxThreads;
        this.waiting = 0;
        done = false;
        repository = new ArrayList<>();
        for(Integer i : a) {
            Integer[] addI = new Integer[]{i};
            repository.add(addI);
        }
    }

    public synchronized ArrayList<Integer[]> returnTwoArrays(){
        while(repository.size() <= 1 && !done){
            if(waiting == maxThreads -1){
                notifyAll();
                done = true;
            }
            else{
                waiting++;
                try{
                    wait();
                }
                catch (InterruptedException e){System.out.println(e);};
                waiting--;
            }
        }

        if(done) {
            return null;
        }

        ArrayList<Integer[]> twoArrays = new ArrayList<>();
        twoArrays.add(repository.remove(0));
        twoArrays.add(repository.remove(0));
        return twoArrays;
    }

    public synchronized void insert(Integer[] array){
        repository.add(array);
        notifyAll();
    }

    public synchronized Integer[] merge(Integer[] a1, Integer[] a2){
        Integer[] mergedArray = new Integer[a1.length + a2.length];
        int index1 = 0, index2 = 0, indexMerged = 0;

        while (index1 < a1.length && index2 < a2.length){
            if(a1[index1] < a2[index2]){
                mergedArray[indexMerged++] = a1[index1++];
            }
            else
                mergedArray[indexMerged++] = a2[index2++];
        }

        while (index1 < a1.length){
            mergedArray[indexMerged++] = a1[index1++];
        }

        while (index2 < a2.length){
            mergedArray[indexMerged++] = a2[index2++];
        }

        return mergedArray;
    }

    public synchronized Integer[] getMergedArray(){
        if(!done){
            try {
                wait();
            }
            catch (InterruptedException e) {};
        }
        return repository.get(0);
    }

    public String toString(){
        return toString(getMergedArray());
    }

    public String toString(Integer[] a){
        StringBuilder string = new StringBuilder();
        for(Integer i : a){
            string.append(i + " ");
        }

        return string.toString();
    }
}
