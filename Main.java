/**
 * The Main class is used for the purpose of manipulating type-A quivers.
 * 
 * @author  Dimitri Joy
 * @version 1.4
 * created on 2020-01-26
 */
class Main {
    public static void main(String args[]) {
        // Verticies
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b", 3);
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        //Vertex h = new Vertex("h");
        //Vertex i = new Vertex("i");

        /**
         *          i---h
         *           \ /
         *        c   e   g
         *       / \ / \ / \
         *      a---b---d---f
         */
        /*
        Quiver q1 = new Quiver();
        q1.add(new Arrow(a, b));
        q1.add(new Arrow(b, c));
        q1.add(new Arrow(c, a));
        q1.add(new Arrow(b, d));
        q1.add(new Arrow(d, e));
        q1.add(new Arrow(e, b));
        q1.add(new Arrow(e, h));
        q1.add(new Arrow(h, i));
        q1.add(new Arrow(i, e));
        q1.add(new Arrow(f, d));
        q1.add(new Arrow(d, g));
        q1.add(new Arrow(g, f));
        */

        /**
         *        c   e
         *       / \ / \
         *      a---b---d
         */
        /*
        Quiver q2 = new Quiver();
        q2.add(new Arrow(a, b));
        q2.add(new Arrow(b, c));
        q2.add(new Arrow(c, a));
        q2.add(new Arrow(b, d));
        q2.add(new Arrow(d, e));
        q2.add(new Arrow(e, b));
        int n = 4, m = 5;
        int counter = 0;
        int values[] = new int[n];
        for (int k1 = 1; k1 <= m; ++k1) {
            a.setValue(k1);
            values[0] = k1;
            for (int k2 = 1; k2 <= m; ++k2) {
                c.setValue(k2);
                values[1] = k2;
                for (int k3 = 1; k3 <= m; ++k3) {
                    d.setValue(k3);
                    values[2] = k3;
                    for (int k4 = 1; k4 <= m; ++k4) {
                        e.setValue(k4);
                        values[3] = k4;
                        Quiver q = new Quiver(q2);
                        q.reduceCycles();
                        if (q.hasValidAssignment()) {
                            for (int val : values) {
                                System.out.print(val + " ");
                            }
                            System.out.println();
                            q2.print();
                            System.out.println();
                            ++counter;
                        }  
                    }
                }
            }
        }
        System.out.println(counter);
        */

        /**
         *        c   e   g
         *       / \ / \ / \
         *      a---b---d---f
         */
        Quiver q3 = new Quiver();
        q3.add(new Arrow(a, b));
        q3.add(new Arrow(b, c));
        q3.add(new Arrow(c, a));
        q3.add(new Arrow(b, d));
        q3.add(new Arrow(d, e));
        q3.add(new Arrow(e, b));
        q3.add(new Arrow(d, f));
        q3.add(new Arrow(f, g));
        q3.add(new Arrow(g, d));
        int n = 6, m = 6;
        int counter = 0;
        int values[] = new int[n]; // values of each vertex, excluding 'b'
        for (int k1 = 1; k1 <= m; ++k1) {
            a.setValue(k1);
            values[0] = k1;
            for (int k2 = 1; k2 <= m; ++k2) {
                c.setValue(k2);
                values[1] = k2;
                for (int k3 = 1; k3 <= m; ++k3) {
                    d.setValue(k3);
                    values[2] = k3;
                    for (int k4 = 1; k4 <= m; ++k4) {
                        e.setValue(k4);
                        values[3] = k4;
                        for (int k5 = 1; k5 <= m; ++k5) {
                            f.setValue(k5);
                            values[4] = k5;
                            for (int k6 = 1; k6 <= m; ++k6) {
                                g.setValue(k6);
                                values[5] = k6;
                                Quiver q = new Quiver(q3);
                                q.reduceCycles();
                                if (q.hasValidAssignment()) {
                                    for (int val : values) {
                                        System.out.print(val + " ");
                                    }
                                    System.out.println();
                                    q3.print();
                                    System.out.println();
                                    ++counter;
                                }  
                            }
                        }
                       
                    }
                }
            }
        }
        System.out.println(counter);
    }

    // ******* UNFINISHED *******
    /**
     * Recursively display information about quivers with valid integer assignments.
     * Every permutation of integer assignments with labelings [1-m] are considered.
     * 
     * @param q
     * @param m
     *//*
    public static void findValidAssignments(Quiver q, int m) {
        int n = q.length() + 1; // number of verticies
        for (int i = 1; i <= m; ++i) {
            // to be completed
        }
    }*/
}