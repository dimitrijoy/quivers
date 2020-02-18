import java.util.ArrayList;

/**
 * The Main class is used for the purpose of implementing
 * and manipulating type-A quivers.
 * 
 * @author  Dimitri Joy
 * @version 1.1
 * created on 2020-01-26
 */
class Main {
    public static void main(String args[]) {
        ArrayList<Vertex> v = new ArrayList<Vertex>();
    	Quiver q = new Quiver();
        for (int i = 0; i < 11; ++i) {
        	v.add(new Vertex("a" + i, i));
        }

        //int vlen = v.size() - 1;
        for (int i = 0; i < v.size() - 1; ++i) {
            q.add(new Arrow(v.get(i), v.get(i+1)));
        }

        // algorithm 1: O(n)
        // starts with largest vertex
        /*int initial = vlen, delta = -1, mu =0;
        if (v.get(0).getValue() >= v.get(vlen).getValue()) {
            initial = 0;
            delta = 1;
        }
        for (int i = initial; !q.isHomogeneous(1); i += delta) {
            q.print();
            System.out.println();
            q.mutate(v.get(i));
            ++mu;
        }
        System.out.println("# of mutations: " + mu);
        q.print();*/

        // algorithm 2: O(n^2)
        // starts with smallest vertex
        /*mu = 0;
        int i = 0;
        while (!q.homogeneous(1)) {
            q.print();
            System.out.println();
            q.mutate(v.get(i));
            if (i < v.size() - 1) {
                ++i;
            } else {
                i = 0;
            }
            ++mu;
        }
        System.out.println("# of mutations: " + mu);
        q.print();*/
    }
}