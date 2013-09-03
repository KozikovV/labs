package laba4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import interfaces.task4.CollectionUtils;
import interfaces.task4.MapUtils;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 1

        Collection<Integer> c1 = new ArrayList<Integer>();
        c1.add(1);
        c1.add(2);
        c1.add(3);
        c1.add(4);
        c1.add(5);
        c1.add(6);
        c1.add(6);
        c1.add(7);

        Collection<Integer> c2 = new ArrayList<Integer>();
        c2.add(5);
        c2.add(6);
        c1.add(6);
        c2.add(7);
        c2.add(8);
        c2.add(9);
        c2.add(10);
        c2.add(11);
        
        CollectionUtils cu = new CollectionUtilImpl();

        System.out.println("c1 " + c1);
        System.out.println("c2 " + c2);
        
        System.out.println("union: "+cu.union(c1, c2));
        System.out.println("union: "+cu.difference(c1, c2));
        System.out.println("intersection: "+cu.intersection(c1, c2));
        System.out.println("intersectionWithoutDuplicate: "+cu.intersectionWithoutDuplicate(c1, c2));

        System.out.println();
        System.out.println();
        System.out.println();

        // 2

        MapUtils mapUtils = new MapUtilsImpl();

        String string = "Qwasdg zx dswas_s 8sdd_asddfg@ sddg .";

        Map<String, Integer> map = mapUtils.findThrees(string);

        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

    }

}
