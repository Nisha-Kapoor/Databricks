//24 => left 24 bits are fixed, right 8 bits are changeable
// rules = [
//   ("ALLOW", "192.168.100.0/24"), 192.168.100.0 - 192.168.100.255
//   ("DENY", "192.168.0.5/30"),    192.168.0.4 - 192.168.0.7 
//   ("ALLOW", "192.168.1.1/22"),  225 255 11111100 0
//   ("DENY", "8.8.8.8/0"),
//   ("ALLOW", "1.2.3.4")
//   DENY
// ]
// x = 1322
// y = 1982


// // return `True` because it matches the first rule (ALLOW)
// accessOk(rules, "192.168.100.34") 

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
    // String[][] rules_arr =  {{"ALLOW", "192.168.100.0/24"}, {"DENY", "192.168.0.5/30"}, {"ALLOW", "192.168.1.1/22"}, {"DENY", "8.8.8.8/0"}, {"ALLOW", "1.2.3.4"}};


    List<List<String>> rules = new ArrayList<>();
    List<String> rule1 = new ArrayList<>();
    rule1.add("ALLOW");
    rule1.add("192.168.100.0/24");

    rules.add(rule1);

    String ip = "192.168.101.1";
    System.out.println(accessOk(rules, ip));

  }

  public static String accessOk(List<List<String>> rules, String ip){
    for(List<String> rule: rules){
      String action = rule.get(0), cidr = rule.get(1);
      String ip_test = cidr.split("/")[0]; 
      int bits = Integer.parseInt(cidr.split("/")[1]);
      System.out.println(ip_test +" "+bits);

      long ip1 = toLong(ip_test);
      long ip2 = toLong(ip);

      ip1 = shift(ip1, 32 - bits);
      ip2 = shift(ip2, 32 - bits);

      if(ip1 == ip2) return action;

    }
    // default
    return "DENY";
  }


  public static long toLong(String ip){
    String parts[] = ip.split("\\.");
    long sum = 0;
    for(int i = 0; i < parts.length; i++){
      sum *= 256;
      sum += Long.parseLong(parts[i]);
    }
    return sum;
  }

  public static long shift(long ip, int bits){
    return ip >> bits;
  }
}
