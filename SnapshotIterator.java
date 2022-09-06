/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    SnapshotSet obj = new SnapshotSet();
    obj.add(1);
    obj.add(2);
    obj.add(3);
    obj.add(4);
    obj.add(5);

    Iterator<Integer> iter = obj.iterator();
    int i = 5;
    while(iter.hasNext()){
      System.out.println(iter.next());
      obj.remove(i + 1);
    }
    
  }
}

class SnapshotSet{
  HashSet<Integer> curr_set;
  public SnapshotSet(){
    curr_set = new HashSet<>();
  }

  public void add(int val){
    curr_set.add(val);
  }

  public void remove(int val){
    curr_set.remove(val);
  }

  public boolean contains(int val){
    return curr_set.contains(val);
  }

  public Iterator<Integer> iterator(){
    HashSet<Integer> new_set = (HashSet<Integer>)curr_set.clone();
    return new_set.iterator();
  } 

}
