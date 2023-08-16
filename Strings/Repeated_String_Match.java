class Solution {
    public int repeatedStringMatch(String a, String b) {
        int len_a = a.length(), len_b = b.length();

        if(len_a > len_b){
            for(int i = 0; i <= len_a - len_b; i++){
                if(a.substring(i, len_b + i).equals(b)) return 1;
            }

            a += a;
            len_a = a.length();
            for(int i = 0; i <= len_a - len_b; i++){
                if(a.substring(i, len_b + i).equals(b)) return 2;
            }
            return -1;
        }

        int x = 1;
        if(len_b % len_a == 0) x = len_b / len_a; 
        else x = (len_b / len_a) + 1;

        String temp = a;
        for(int i = 0; i < x - 1; i++){
            a += temp;
        }
        len_a = a.length();

        for(int i = 0; i <= len_a - len_b; i++){
            if(a.substring(i, i + len_b).equals(b)) return x;
        }

        a += temp;
        len_a = a.length();
        for(int i = 0; i <= len_a - len_b; i++){
            if(a.substring(i, i + len_b).equals(b)) return (x+1);
        }

        return -1;
    }
}