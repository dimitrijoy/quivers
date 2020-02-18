// TODO: Outsource responsibilities to other class
// TODO: Redesign as a GUI

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Interface class is used for the purpose of implementing
 * and manipulating quivers with a layer of abstraction.
 * 
 * @author  Dimitri Joy
 * @version 1.2
 * created on 2020-01-27
 */
class Interface {
    final private static char OPT_EXIT = 'E';
    final private static char OPTIONS[] = {'V', 'A', 'M', 'P', 'E'};

    /**
     * Handle basic validation and user input.
     */
    public static void main(String args[]) {
        // new quiver and its verticies
        ArrayList<Vertex> verticies = new ArrayList<Vertex>();
        Quiver quiver = new Quiver();

        // due to how the Scanner object functions, all input must be within the main() method
        Scanner sc = new Scanner(System.in);
        String inputStr;
        char input;

        while (true) {// run until System.exit(0)
            do {
                options();
                if (sc.hasNext()) {
                    inputStr = sc.next();
                    input = Character.toUpperCase(inputStr.charAt(0));
                } else { // exits if no tokens remaining
                    input = OPT_EXIT;
                }
            } while (!isValid(input));

            // TODO: add validation for below input
            switch (input) { // no validation for below input
                case 'V':
                    int num; String id;
                    System.out.print("\nGive your vertex a unique identifier.\n>>> "); id = sc.next();
                    System.out.print("\nAssign your vertex a positive integer.\n>>> "); num = sc.nextInt();
                    verticies.add(new Vertex(id, num));
                    break;
                case 'A':
                    String id1, id2;
                    System.out.print("\nFirst enter the unique identifier of the vertex where the arrow will start.\n>>> "); id1 = sc.next();
                    System.out.print("\nAnd now enter the unique identifier of the vertex where the arrow will end.\n>>> "); id2 = sc.next();
                    quiver.add(search(id1, id2, verticies));
                    break;
                case 'M':
                    int freq = 1; // number of times to mutate vertex
                    String id3;
                    System.out.print("\nEnter the unique identifier of the vertex you would like to mutate.\n>>> "); id3 = sc.next();
                    Vertex v = search(id3, verticies); // vertex to be mutated
                    quiver.mutate(v);
                    break;
                case 'P':
                    if (quiver.length() >= 1) {
                        System.out.println();
                        quiver.print();
                    }
                    break;
                case 'E':
                    sc.close();
                    System.exit(0);
                    break; // probably not necessary
            }
        }
    }

    /**
     * Display available options accessible through user input.
     */
    private static void options() {
        System.out.println("\nV: Create a new vertex.");
        System.out.println("A: Create a new arrow.");
        System.out.println("M: Mutate a vertex.");
        System.out.println("P: Print your quiver.");
        System.out.println("E: Exit.");
        System.out.print(">>> ");
    }

    /**
     * Determine if the character is a valid selection from the available
     * options.
     * 
     * @param   c   the character to be checked
     * @return      whether or not the character was found to be valid
     */
    private static boolean isValid(char c) {
        for (int i = 0; i < OPTIONS.length; ++i) {
            if (c == OPTIONS[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search the ArrayList of verticies for the vertex with the
     * matching identifier.
     * 
     * @param   id  the identifier to be searched for
     * @param   v   the ArrayList of verticies
     * @return      the vertex with the matching identifier
     */
    private static Vertex search(String id, ArrayList<Vertex> v) {
        Vertex v1 = null;
        for (int i = 0; i < v.size(); ++i) {
            if (id.equals(v.get(i).getId())) {
                v1 = v.get(i);
            }
        }
        return v1;
    }

    /**
     * Search the ArrayList of verticies for the verticies with the
     * matching identifiers.
     * 
     * @param   id1 the identifier of the propsective base vertex to be searched for
     * @param   id2 the identifier of the prospective terminal vertex to be searched for
     * @param   v   the ArrayList of verticies
     * @return      an arrow with the matching verticies
     */
    private static Arrow search(String id1, String id2, ArrayList<Vertex> v) {
        Vertex v1 = null, v2 = null;
        for (int i = 0; i < v.size(); ++i) {
            if (id1.equals(v.get(i).getId())) {
                v1 = v.get(i);
            } else if (id2.equals(v.get(i).getId())) {
                v2 = v.get(i);
            }
        }
        return new Arrow(v1, v2);
    }
}