import java.util.ArrayList;

/**
 * The Quiver class is for constructing quivers,
 * mutating vertices, and analyzing the results.
 * 
 * @author  Dimitri Joy
 * @version 1.6
 * created on 2020-01-26
 */
class Quiver {
    private ArrayList<Arrow> arrows;
    private ArrayList<Vertex> vertices;

    /**
     * A quiver comprised of vertices interlinked with
     * arrows via the Arrow and Vertex classes.
     */
    Quiver() {
        // instantiates ArrayLists
        arrows = new ArrayList<Arrow>();
        vertices = new ArrayList<Vertex>();
    }

    /**
     * Create a copy of an existing quiver.
     * 
     * @param q     the quiver to be copied
     */
    Quiver(Quiver q) {
        // instantiates ArrayLists
        arrows = new ArrayList<Arrow>();
        vertices = new ArrayList<Vertex>();
        ArrayList<Vertex> copiedVertices = new ArrayList<Vertex>();
        for (int i = 0; i < q.arrows.size(); ++i) {
            Vertex base = q.arrows.get(i).getBase();
            Vertex terminal = q.arrows.get(i).getTerminal();
            if (!copiedVertices.contains(base) && !copiedVertices.contains(terminal)) {
                Arrow arrowCopy = new Arrow(q.arrows.get(i));
                arrows.add(arrowCopy);
                vertices.add(arrowCopy.getBase());
                vertices.add(arrowCopy.getTerminal());
                copiedVertices.add(arrowCopy.getBase());
                copiedVertices.add(base);
                copiedVertices.add(arrowCopy.getTerminal());
                copiedVertices.add(terminal);
            } else if (!copiedVertices.contains(base)) {
                Vertex baseCopy = new Vertex(base);
                vertices.add(baseCopy);
                copiedVertices.add(baseCopy);
                copiedVertices.add(base);
                for (int j = 0; j < copiedVertices.size(); ++j) {
                    if (copiedVertices.get(j) == terminal) {
                        arrows.add(new Arrow(baseCopy, copiedVertices.get(j - 1)));
                    }
                }
            } else if (!copiedVertices.contains(terminal)) {
                Vertex terminalCopy = new Vertex(terminal);
                vertices.add(terminalCopy);
                copiedVertices.add(terminalCopy);
                copiedVertices.add(terminal);
                for (int j = 0; j < copiedVertices.size(); ++j) {
                    if (copiedVertices.get(j) == base) {
                        arrows.add(new Arrow(copiedVertices.get(j - 1), terminalCopy));
                    }
                }
            } else {
                Vertex v1 = new Vertex(), v2 = new Vertex();
                for (int j = 0; j < copiedVertices.size(); ++j) {
                    if (copiedVertices.get(j) == base) {
                        v1 = copiedVertices.get(j - 1);
                    } else if (copiedVertices.get(j) == terminal) {
                        v2 = copiedVertices.get(j - 1);
                    }
                }
                arrows.add(new Arrow(v1, v2));
            }
        }
    }

    /**
     * Add an arrow to the quiver.
     * 
     * @param a     the arrow to be added
     */
    public void add(Arrow a) {
        arrows.add(a);
        Vertex v1 = a.getBase();
        Vertex v2 = a.getTerminal();
        if (vertices.isEmpty()) { // only add v1 for the first arrow
            vertices.add(v1);
        }
        vertices.add(v2);
    }

    /**
     * Returns the updated value of the mutated vertex.
     * 
     * @param v     the vertex to be updated
     * 
     * @return      the new value of the vertex
     */
    private int updatedValue(Vertex v) {
        int val;
        int arrowsIn = 1, arrowsOut = 1;
        for (int i = 0; i < arrows.size(); ++i) {
            Arrow a = arrows.get(i);
            if (a.getTerminal() == v) {
                arrowsIn *= a.getBaseValue();
            } else if (a.getBase() == v) {
                arrowsOut *= a.getTerminalValue();
            }
        }
        try {
            if ((arrowsIn + arrowsOut) % v.getValue() == 0)
                val = (arrowsIn + arrowsOut) / v.getValue();
            else
                val = 0;
        } catch(Exception e) { // likely division by 0
            val = 0;
        }
        return val;
    }

    /**
     * Update the value of the mutated vertex.
     * 
     * @param v     the vertex to be updated
     */
    private void updateValue(Vertex v) {
        int val;
        int arrowsIn = 1, arrowsOut = 1;
        for (int i = 0; i < arrows.size(); ++i) {
            Arrow a = arrows.get(i);
            if (a.getTerminal() == v) {
                arrowsIn *= a.getBaseValue();
            } else if (a.getBase() == v) {
                arrowsOut *= a.getTerminalValue();
            }
        }
        try {
            if ((arrowsIn + arrowsOut) % v.getValue() == 0)
                val = (arrowsIn + arrowsOut) / v.getValue();
            else
                val = 0;
        } catch(Exception e) { // likely division by 0
            val = 0;
        }
        v.setValue(val);
    }

    /**
     * Connect verticies x and y if the relationship x ---> v ---> y
     * exists, where v is the mutated vertex.
     * 
     * @param v     the mutated vertex
     */
    private void connect(Vertex v) {
        for (int i = 0; i < arrows.size(); ++i) {
            Arrow a1 = arrows.get(i);
            if (a1.getTerminal() == v) {
                for (int j = 0; j < arrows.size(); ++j) {
                    Arrow a2 = arrows.get(j);
                    if (a2.getBase() == v) {
                        arrows.add(new Arrow(a1.getBase(), a2.getTerminal()));
                    }
                }
            }
        }
    }

    /**
     * Reverse all arrows in the quiver.
     */
    public void reverseArrows() {
        for (int i = 0; i < arrows.size(); ++i) {
            arrows.get(i).flip();
        }
    }

    /**
     * Reverse arrows adjacent to the mutated vertex.
     * 
     * @param v     the mutated vertex
     */
    private void reverseArrows(Vertex v) {
        for (int i = 0; i < arrows.size(); ++i) {
            if (arrows.get(i).contains(v)) {
                arrows.get(i).flip();
            }
        }
    }

    /**
     * Delete loops from the quiver.
     */
    private void deloop() {
        ArrayList<Arrow> loops = new ArrayList<Arrow>();
        for (int i = 0; i < arrows.size() - 1; ++i) {
            for (int j = i + 1; j < arrows.size(); ++j) {
                if (arrows.get(i).loopsWith(arrows.get(j))) {
                    loops.add(arrows.get(i));
                    loops.add(arrows.get(j));
                }
            }
        }
        arrows.removeAll(loops);
    }

    /**
     * Mutate a vertex in the quiver.
     * 
     * @param v     the vertex to be mutated
     */
    public void mutate(Vertex v) {
        updateValue(v);
        connect(v);
        reverseArrows(v);
        deloop();
    }

    /**
     * Print the base and terminal verticies and the direction of each arrow
     * in the quiver.
     */
    public void print() {
        for (int i = 0; i < arrows.size(); ++i) {
            arrows.get(i).print();
        }
    }

    /**
     * Return the number of arrows in the quiver.
     * 
     * @return      the number of arrows in the quiver
     */
    public int length() {
        return arrows.size();
    }

    /**
     * Check if every vertex in the quiver shares the same value.
     * 
     * @param val   the value to be checked
     * @return      whether or not the quiver is homogeneous
     */
    public boolean isHomogeneous(int val) {
        for (int i = 0; i < arrows.size(); ++i) {
            if (!arrows.get(i).strictlyContains(val)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the quiver has an integer assignment that will remains as integers
     * after every possible mutation.
     * 
     * The algorithm used in hasValidAssignment() provides a quicker method than the exhaustive process
     * of performing every possible mutation by checking if each vertex yields an integer result after
     * a single mutation.
     * 
     * @return      whether or not the quiver has an integer assignments that will remains as integers
     */
    public boolean hasValidAssignment() {
        Quiver q = new Quiver(this);
        q.reduceCycles();
        for (int i = 0; i < q.vertices.size(); ++i) {
            try {
                if (updatedValue(q.vertices.get(i)) <= 0)
                    return false; // only positive integers are allowed
            } catch(Exception e) { return false; }
        }
        return true;
    }

    /**
     * Check whether the quiver contains at least one oriented 3-cycle.
     * 
     * @return      whether or not the quivers contains an oriented 3-cycle
     */
    public boolean hasOrientedCycles() { // only concerned with 3-cycles for now
        // searches for a relationship of this kind: (v1, v2), (v2, v3), (v3, v1)
        for (int i = 0; i < arrows.size() - 2; ++i) {
            for (int j = i + 1; j < arrows.size() - 1; ++j) {
                if (arrows.get(i).getTerminal() == arrows.get(j).getBase()) {
                    for (int k = j + 1; k < arrows.size(); ++k) {
                        if (arrows.get(j).getTerminal() == arrows.get(k).getBase()) {
                            if (arrows.get(i).getBase() == arrows.get(k).getTerminal()) { // nested to improve readability
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return the verticies of every oriented 3-cycle.
     * 
     * @return      the verticies of every oriented 3-cycle
     */
    public ArrayList<Vertex[]> findOrientedCycles() {
        ArrayList<Vertex[]> cycles = new ArrayList<Vertex[]>();
        // searches for a relationship of this kind: (v1, v2), (v2, v3), (v3, v1)
        for (int i = 0; i < arrows.size() - 2; ++i) {
            for (int j = i + 1; j < arrows.size() - 1; ++j) {
                if (arrows.get(i).getTerminal() == arrows.get(j).getBase()) {
                    for (int k = j + 1; k < arrows.size(); ++k) {
                        if (arrows.get(j).getTerminal() == arrows.get(k).getBase()) {
                            if (arrows.get(i).getBase() == arrows.get(k).getTerminal()) { // nested to improve readability
                                Vertex cycle[] = {arrows.get(i).getBase(), // stored in an array for use in reducableCycles()
                                                  arrows.get(j).getBase(), // and similar structure to a tuple
                                                  arrows.get(k).getBase()};
                                cycles.add(cycle);
                            }
                        }
                    }
                }
            }
        }
        return cycles;
    }

    /**
     * Return the mutable verticies of every reducable open oriented 3-cycle;
     * that is, each 3-cycle with at least one vertex not attached to
     * another quiver.
     * 
     * @return      the mutable verticies
     */
    private ArrayList<Vertex> reducableOpenCycles() {
        ArrayList<Vertex> mutations = new ArrayList<Vertex>();
        if (hasOrientedCycles()) {
            ArrayList<Vertex[]> cycles = findOrientedCycles();
            for (int i = 0; i < cycles.size(); ++i) { // checks every vertex in every array of verticies
                for (int j = 0; j < cycles.get(i).length; ++j) {
                    int counter = 0;
                    for (int k = 0; k < arrows.size(); ++k) { // counts the number of arrows a vertex shares
                        if (arrows.get(k).contains(cycles.get(i)[j])) {
                            ++counter;
                        }
                    }
                    if (counter <= 2) { // no mutable vertex should appear in more than 2 arrows
                        mutations.add(cycles.get(i)[j]);
                        break; // prevents adding more than one mutable vertex from the same cycle
                    }
                }
            }
        }
        return mutations;
    }

    /**
     * Reduce every open oriented 3-cycle into an acyclic form.
     */
    public void reduceOpenCycles() {
        ArrayList<Vertex> mutations = reducableOpenCycles();
        for (int i = 0; i < mutations.size(); ++i) {
            mutate(mutations.get(i));
        }
    }

    /**
     * Return the mutable verticies of every reducable closed oriented 3-cycle;
     * that is, each 3-cycle with every vertex attached to another quiver.
     * 
     * @return      the mutable verticies
     */
    private ArrayList<Vertex> reducableClosedCycles() {
        ArrayList<Vertex> mutations = new ArrayList<Vertex>();
        if (hasOrientedCycles()) {
            ArrayList<Vertex[]> cycles = findOrientedCycles();
            for (int i = 0; i < cycles.size(); ++i) {
                Vertex v1 = cycles.get(i)[0]; // only one vertex per 3-cycle is relevant
                mutations.add(v1);
                // essentially want to start with one vertex from the 3-cycle
                // and mutate along the quiver attached to this vertex
                for (int j = 0; j < arrows.size(); ++j) {
                    if (arrows.get(j).contains(v1)) { // nested to improve readability
                        if (!(arrows.get(j).contains(cycles.get(i)[1]) || arrows.get(j).contains(cycles.get(i)[2]))) {
                            // if-statements are necessary since the direction of each arrow is unpredictable
                            if (arrows.get(j).getBase() == v1) {
                                Vertex v2 = arrows.get(j).getTerminal();
                                mutations.add(v2);
                                // in a more general case, this method should be defined recursively
                                // until a vertex with only one arrow is reached
                                for (int k = 0; k < arrows.size(); ++k) {
                                    if (arrows.get(k).contains(v2) && !arrows.get(k).contains(v1)) {
                                        if (arrows.get(k).getBase() == v2) {
                                            mutations.add(arrows.get(k).getTerminal());
                                        } else {
                                            mutations.add(arrows.get(k).getBase());
                                        }
                                    }
                                }
                            } else {
                                Vertex v3 = arrows.get(j).getBase();
                                mutations.add(v3);
                                for (int k = 0; k < arrows.size(); ++k) {
                                    if (arrows.get(k).contains(v3) && !arrows.get(k).contains(v1)) {
                                        if (arrows.get(k).getBase() == v3) {
                                            mutations.add(arrows.get(k).getTerminal());
                                        } else {
                                            mutations.add(arrows.get(k).getBase());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return mutations;
    }

    /**
     * Reduce every closed oriented 3-cycle into an acyclic form.
     */
    public void reduceClosedCycles() {
        ArrayList<Vertex> mutations = reducableClosedCycles();
        for (int i = 0; i < mutations.size(); ++i) {
            mutate(mutations.get(i));
        }
    }

    /**
     * Reduce every oriented 3-cycle into an acyclic form.
     */
    public void reduceCycles() {
        reduceOpenCycles(); // order matters
        reduceClosedCycles();
    }

    /**
     * Create a copy of an existing quiver.
     * 
     * @return      the copy of an existing quiver
     */
    public Quiver copy() {
        return new Quiver(this);
    }
}