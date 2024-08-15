import java.util.*;
import java.lang.*;

class Main {

    public static void main(String h[]) {
        int[] nums = { 1, 2, 3 };
        List<Integer> check = new ArrayList<>();
        List<List<Integer>> ans = permute(nums);
        System.out.println(ans);
    }

    static void formPermutations(int ind, int[] nums, List<List<Integer>> ans, List<Integer> ds, List<Boolean> flag) {

        if (ds.size() == nums.length) {
            ans.add(ds);
            System.out.println("ans = " + ans);
            return;
        }
        if (ind == nums.length) {
            // System.out.println("ds = "+ds);
            return;
        }

        ds.add(nums[ind]);
        flag.set(ind, true);
        for (int i = 0; i < nums.length && flag.get(i) != true; i++) {
            ds.add(nums[i]);
            flag.set(ind, true);
            formPermutations(i + 1, nums, ans, new ArrayList<>(ds), new ArrayList<>(flag));
            ds.remove(ds.size() - 1);
            flag.set(ind, false);
            formPermutations(i + 1, nums, ans, new ArrayList<>(ds), new ArrayList<>(flag));
        }

        ds.remove(ds.size() - 1);
        flag.set(ind, false);
        formPermutations(ind + 1, nums, ans, new ArrayList<>(ds), new ArrayList<>(flag));
        return;
    }

    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        List<Boolean> flag = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            flag.add(false);
        }
        formPermutations(0, nums, ans, ds, flag);
        return ans;
    }
}
// int MOD = (int)(1e9 + 7);

// Convert char to int
// int n = Character.getNumericValue(i);
// int n = 'a' gives ASCII value

// Convert int (ASCII Value) to char
// int i = 65;
// char c = (char) i;
// (c = 'a')

// Convert int to char
// int i = 1;
// char c = (char) (i + '0');
// (c = '1')

// Convert char to string
// String s = Character.toString('a')
// Convert int to long
// Long l = Long.valueOf(n);

// Convert long to int
// int i = (int)l;

// Convert int to String
// String s = Integer.toString(n);

// Convert String to int
// int n = Integer.valueOf(s);
// int n = Integer.parseInt(s);
// Convert int to Double
// Double d = Double.valueOf(i)

// Convert Double to int
// int i = (int)d;

// Convert int to
// String str = Integer.toBinaryString(n);

// Convert binary to decimal
// int decimal = Integer.parseInt(binaryString, 2);

// Bitwise Operators
// & -> Bitwise AND operator
// | -> Bitwise OR operator
// ^ -> Bitwise XOR operator
// ~ -> Bitwise Complement(Negation) operator

// Binary number operations
// no of set bits -> int n = Integer.bitCount(num);
// integer to binary string -> String s = Integer.toBinaryString(num);
// binary string to int -> int d = Integer.parseInt("string",2);

// Bit Masking Operations

// i) Finding the ith bit (in short: n & (1 << index))

// int n = 6;
// int index = 0;
// int mask = 1 << index;
// int result = n & mask;
// if(result != 0) System.out.println("bit is 1");
// else System.out.println("bit is 0");

// V.V.IMP NOTE: When we talk about indexes in a binary string
// it is from R.H.S TO L.H.S (FROM RIGHT TO LEFT).
// ALSO IF BIT IS 1 THEN RESULT CAN BE ANYTHING APART FROM 0,
// NOT NECESSARILY 1

// ii) Setting the ith bit (in short: n | (1 << index))

// int n = 6;
// int index = 0;
// int mask = 1 << index;
// int result = n | mask;
// System.out.println("before changing: "+Integer.toBinaryString(n));
// System.out.println("after changing: "+Integer.toBinaryString(result));

// iii) Clearing the ith bit [In Short: n & (~(1 << index))]

// int n = 6;
// int index = 1;
// int mask = ~(1 << index);
// int result = n & mask;
// System.out.println("before changing: "+Integer.toBinaryString(n));
// System.out.println("after changing: "+Integer.toBinaryString(result));

// StringBuilder operations

// 1.) Initialization
// StringBuilder sb = new StringBuilder("hello");

// 2.) String Processing
// i) To insert string at the end
// sb.append(" world");
// prints hello world

// ii) To insert string at a specific index;
// sb.insert(1,"Java");
// prints HJavaello

// iii) To replace a given part pr substring with a new string
// sb.replace(1(start index),3(end index),"Java");
// prints HJavalo

// iv) to delete a paticular portion of Substring
// sb.delete(1,3);
// prints prints Hlo

// v)To reverse string
// sb. reverse();

// vi)To get substring
// sb.substring(start_index,end_index);

// 3.) String operations
// To identify length of string
// sb.length();

// To get character at specific location
// sb.chatAt(index);

// To set character at specific location
// sb.setCharAt(index, char);

// To convert stingbuilder variable to string
// sb.toString();

// 4.) Data Structures and functions
// i) HashSet()s
// To declare a HashSet
// HashSet<Integer> hs = new HashSet();
// To add: hs.add(1);
// To traverse HashSet
// Iterator<Integer> itr = hs.iterator();
// while(itr.hasNext()){
// System.out.println(itr.next());
// }

// BEST WAY TRAVERSAL
// HashSet<String> mySet = new HashSet<String>();
// // add elements to your set

// for (String item : mySet) {
// // Do something with the item
// System.out.println(item);
// }

// ii) r()
// To declare HashMap<Integer,String> hm = new HashMap<Integer,String>();

// To add an element on the HashMap()
// hm.put(key,value)

// To check if a key is present in the HashMap()
// hm.containsKey(key_value) (returns boolean)

// To get the value of a key
// hm.getKey()

// To replace the value of the key
// hm.replace(key, new_value)

// Traversing a HashMap
// for(map.Entry i: map.entrySet()){
// int key = i.getKey();
// String s = i.getString();
// }

// BEST WAY TRAVERSAL
// HashMap<String, Integer> myMap = new HashMap<String, Integer>();
// // add key-value pairs to your map

// for(String key: myMap.keySet()){
// Integer val = myMap.get(key);
// }

// To copy the elements of the array with creating a new array:-
// int[] a = {1,2,3,4}
// int[] b = a.clone();
// b[0]++;
// System.out.println(a[0]+" "+b[0]);
// Output: 1 2;

// iii) Stack
// import java.util.Stack; (Not a part of collections)
// Stack<datatype> stk = new Stack<>();
// stk.push(item)
// stk.pop() removes and returns the element at the top
// stk.peek() returns the element at the top without removing it
// stk.empty() returns true boolean if stack is empty
// stk.search(object o) returns the inHashMapdex of the object in the stack

// iv) Queue
// import java.util.LinkedList;
// import java.util.Queue;
// IMP: DO NOT USE THE NEW PRIORITY QUEUE TYPE IN JAVA, DOES NOT WORK WELL
// Queue<DataType> queue=new LinkedList<DataType>();
// add(object)
// remove() -> It is used to retrieves and removes the head of this queue.
// poll() -> It is used to retrieves and removes the head of this queue, or
// returns null if this queue is empty.
// peek() -> It is used to retrieves, but does not remove, the head of this
// queue, or returns null if this queue is empty.

// lang.StackOverflowError is a runtime error which points to serious problems
// that cannot be caught by an application.
// The java. lang. StackOverflowError indicates
// that the application stack is exhausted and is usually caused by
// deep or infinite recursion.

// lang.NullPointerException is a runtime error which occurs when the pointer is
// pointing to
// something (a variable) which has not yet been defined that is, it is undeined
// or null

// some blah blah memory space exhausted error
// it occurs when you keep on adding elements in a list or another data
// structure.
// data structure have fixed limits on their sizes.
// The size of array or lists is somwhere near 10^5

// the max size of an array in java is 2^32 i.e 2,147,483,647 elements
// array's are pre-initialized by 0's in java

// Filling an array using Arrays.fill
// int ar[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
// Arrays.fill(ar, 10);
// Output: [10, 10, 10, 10, 10, 10, 10, 10, 10]

// fill part of array.
// Fill from index 1 to index 4.
// Arrays.fill(ar, 1, 5, 10);
// Output: [2, 10, 10, 10, 10, 2, 2, 2, 2]

// Fill 2D Array
// int [][]ar = new int [3][4];
// for (int[] row : ar)
// Arrays.fill(row, 10);
// Output: [[10, 10, 10, 10], [10, 10, 10, 10], [10, 10, 10, 10]]

// Sorting

// Lists / ArrayLists

// Sorting in ascending order
// Collections.sort(lst);

// Sorting in descending order
// Collections.sort(lst, Collections.reverseOrder())

// Arrays

// Sorting in ascending order
// Arrays.sort(nums);

// Sorting in descending order
// Arrays.sort(nums, Collection.reverseOrder())

// Binary Search
// for arrays
// Arrays.binarySearch(nums, element);
// return index based on 0 based indexing
// if element is not present in the array then returns -(insertion_point) - 1;
// insertion point is the point at which the element could be inserted in the
// array

// for lists
// Collections.binarySearch(nums, element)
// return index based on 0 based indexing
// if element is not present in the lst then returns -(insertion_point) - 1;
// insertion point is the point at which the element could be inserted in the
// lst

// Real Cheats

// 1.) split()
// String[] arr = s.split(" ");
// .split(" ") helps split the string and create an Array of Strings consisting
// of the words on
// the string splitted by the whitespace

// 2.) toLowerCase()
// s.toLoweCase();
// Converts the characters of a string in Lower Case

// 3.) toUpperCase()
// s.toUpperCase()
// Converts the characters of the string in Upper Case

// class Solution {
// public String findDifferentBinaryString(String[] nums) {
// HashSet<Integer> hs = new HashSet();
// int n = (int)Math.pow(2, nums[0].length() + 1) - 1;

// for(int i = 0; i < nums.length; i++){
// lst.add(Integer.parseInt(nums[i], 2));
// }
// Collections.sort(lst);

// for(int i = 0; i < lst.size(); i++)
// }
// }

// 1 1
// 2 3
// 3 7
// 4
