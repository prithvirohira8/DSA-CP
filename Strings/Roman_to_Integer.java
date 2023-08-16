// Roman to Integer

// Question Link: https://leetcode.com/problems/roman-to-integer/description/

class Solution {
    public int romanToInt(String s) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("I", 1);
        hm.put("V",5);
        hm.put("X",10);
        hm.put("L",50);
        hm.put("C",100);
        hm.put("D",500);
        hm.put("M",1000);

        hm.put("IV",4);
        hm.put("IX",9);
        hm.put("XL",40);
        hm.put("XC",90);
        hm.put("CD",400);
        hm.put("CM",900);

        int n = 0, i = 0;
        for(i = 0; i  < s.length() - 1; i++){
            if(s.charAt(i) == 'I'){
                if(s.charAt(i+1) == 'V'){
                    n += hm.get("IV");
                    i += 1;
                    continue;
                } 
                if(s.charAt(i+1) == 'X'){
                    n += hm.get("IX");
                    i += 1;
                    continue;
                } 
            }
            else if(s.charAt(i) == 'X'){
                if(s.charAt(i+1) == 'L'){
                    n += hm.get("XL");
                    i += 1;
                    continue;
                } 
                if(s.charAt(i+1) == 'C'){
                    n += hm.get("XC");
                    i += 1;
                    continue;
                } 
            }
            else if(s.charAt(i) == 'C'){
                if(s.charAt(i+1) == 'D'){
                    n += hm.get("CD");
                    i += 1;
                    continue;
                } 
                if(s.charAt(i+1) == 'M'){
                    n += hm.get("CM");
                    i += 1;
                    continue;
                } 
            }
            n += hm.get(String.valueOf(s.charAt(i)));
        }
        
        if(i != s.length()) n += hm.get(String.valueOf(s.charAt(s.length() - 1)));
        
        return n;
    }
}

// Time Complexity: O(N)

// Space Complexity: O(N)