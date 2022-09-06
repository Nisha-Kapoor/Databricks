/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    CountQPS obj = new CountQPS();

    obj.put(1,1);
    obj.put(2,2);
    obj.put(3,3);

    obj.get(3);
    obj.get(2);

    obj.put(4,4);
    obj.put(5,5);

    obj.get(4);

    System.out.println(obj.putLoad() +" "+ obj.getLoad());

    try{
      TimeUnit.SECONDS.sleep(31);
    }
    catch(Exception e){}
    

    System.out.println(obj.putLoad() +" "+ obj.getLoad());
  }
}

class CountQPS{
  Deque<Long> put_queue;
  Deque<Long> get_queue;
  HashMap<Integer, Integer> hm;
  public CountQPS(){
    put_queue = new ArrayDeque<>();
    get_queue = new ArrayDeque<>();
    hm = new HashMap<>();
  }
  
  public void put(int key, int val){
    discardTimestamps(put_queue);
    hm.put(key, val);
    put_queue.offer(System.currentTimeMillis() / 1000);
  }

  public void get(int key){
    discardTimestamps(get_queue);
    hm.get(key);
    get_queue.offer(System.currentTimeMillis() / 1000);
  }

  public int putLoad(){    
    discardTimestamps(put_queue);
    return put_queue.size();
  }

  public int getLoad(){
    discardTimestamps(get_queue);
    return get_queue.size();
  }

  public void discardTimestamps(Deque<Long> queue){
    long curr_time = System.currentTimeMillis() / 1000;
    while(!queue.isEmpty() && queue.peek() < curr_time - 30)
      queue.poll();
  }
  

}
