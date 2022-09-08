/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.function.Function;

import com.google.common.base.Predicate;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    Function<Integer, Integer> func1 = lz -> lz * 2;
    Function<Integer, Integer> func2 = lz -> lz * 3;
    List<Function<Integer, Integer>> functions = new ArrayList<>(){{
      add(func1);
      add(func2);
    }};

    Predicate<Integer> pred1 = lz -> lz >= 100;
    List<Predicate<Integer>> predicates = new ArrayList<>(){{
      add(pred1);
    }};


    LazyArray arr = new LazyArray(Arrays.asList(10, 20, 30, 40, 50));
    
    System.out.println(arr.indexOf(300, functions, new ArrayList<>()));
    arr = new LazyArray(Arrays.asList(10, 20, 30, 40, 50));
    System.out.println(arr.indexOf(300, functions, predicates));

  }
}

class LazyArray{
  List<Integer> arr;
  public LazyArray(List<Integer> arr){
    this.arr = arr;
  }

  public int indexOf(int val, List<Function<Integer, Integer>> functions, List<Predicate<Integer>> predicates){
    for(Function<Integer, Integer> func: functions){
        this.map(func);
    }

    for(Predicate<Integer> pred: predicates){
        this.filter(pred);
    }

    for(int i = 0; i < arr.size(); i++){
      if(arr.get(i) == val)
        return i;
    }
      
    return -1;
  }

  public LazyArray map(Function<Integer, Integer> func){
    for(int i = 0; i < arr.size(); i++){
      int n = arr.get(i);
      arr.set(i, func.apply(n));
    }
    return this;
  }

  public LazyArray filter(Predicate<Integer> pred){
    List<Integer> new_arr = new ArrayList<>();
    for(int n: arr){
      if(pred.test(n)){
        new_arr.add(n);
      }
    }
    arr = new_arr;
    return this;
  }

}
