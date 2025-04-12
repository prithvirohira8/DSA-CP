import java.util.*;

class MergeSort {
    static List<Integer> merge(List<Integer> lst1, List<Integer> lst2) {
        // merge
        List<Integer> lst = new ArrayList<>();
        int i = 0, j = 0;
        while (i < lst1.size() && j < lst2.size()) {
            if (lst1.get(i) <= lst2.get(j)) {
                lst.add(lst.get(i));
                i++;
            } else {
                lst.add(lst2.get(j));
                j++;
            }
        }
        if (i < lst1.size()) {
            while (i < lst1.size()) {
                lst.add(lst1.get(i));
                i++;
            }
        }
        if (j < lst2.size()) {
            while (i < lst2.size()) {
                lst.add(lst2.get(j));
                j++;
            }
        }
        return lst;
    }

    static List<Integer> mergeSort(int l, int h, List<Integer> lst) {
        if (h == l) {
            List<Integer> temp = new ArrayList<>();
            temp.add(lst.get(l));
            return temp;
        }
        int mid = (l + h) / 2;
        List<Integer> lst1 = mergeSort(l, mid, lst);
        List<Integer> lst2 = mergeSort(mid + 1, h, lst);
        List<Integer> res = merge(lst1, lst2);
        return res;
    }

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(50);
        lst.add(40);
        lst.add(30);
        lst.add(20);
        lst.add(10);
        lst.add(0);
        List<Integer> res = mergeSort(0, lst.size() - 1, lst);
        System.out.println(res);
    }
}