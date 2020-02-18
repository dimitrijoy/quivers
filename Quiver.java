import java.util.ArrayList;

/**
 * The Quiver class is for constructing quivers,
 * mutating vertices, and analyzing the results.
 * 
 * @author  Dimitri Joy
 * @version 1.3
 * created on 2020-01-26
 */
class Quiver {
    private ArrayList<Arrow> arrows;
    private ArrayList<Vertex> verticies;

    /**
     * A Quiver is comprised of a sequence verticies interlinked with
     * arrows via the Arrow and Vertex classes, respectively.
     */
    Quiver() {
        arrows = new ArrayList<Arrow>();
        verticies = new ArrayList<Vertex>();
    }

    /**
     * Add an arrow to the quiver.
     * 
     * @param   a   an arrow to be added
     */
    public void add(Arrow a) {
        arrows.add(a);
        Vertex v1 = a.getBase();
        Vertex v2 = a.getTerminal();
        if (verticies.isEmpty()) { // only add v1 for the first arrow
            verticies.add(v1);
        }
        verticies.add(v2);
    }

    /**
     * Returns the updated value of the mutated vertex.
     * 
     * @param   v   the vertex to be updated
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
            val = (arrowsIn + arrowsOut) / v.getValue();
        } catch(Exception e) { // likely division by 0
            val = 0;
        }
        return val;
    }

    /**
     * Update the value of the mutated vertex.
     * 
     * @param   v   the vertex to be updated
     */
    private void updateValue(Vertex v) {
        int arrowsIn = 1, arrowsOut = 1;
        for (int i = 0; i < arrows.size(); ++i) {
            Arrow a = arrows.get(i);
            if (a.getTerminal() == v) {
                arrowsIn *= a.getBaseValue();
            } else if (a.getBase() == v) {
                arrowsOut *= a.getTerminalValue();
            }
        }
        int val = (arrowsIn + arrowsOut) / v.getValue();
        v.setValue(val);
    }

    /**
     * Connect verticies x and y if the relationship x ---> v ---> y
     * exists, where v is the mutated vertex.
     * 
     * @param   v   the mutated vertex
     */
    private void connectThrough(Vertex v) {
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
     * @param   v   the mutated vertex
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
     * @param   v   the vertex to be mutated
     */
    public void mutate(Vertex v) {
        updateValue(v);
        connectThrough(v);
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
     * @param   val the value to be checked
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

    public boolean hasValidAssignment() {
        for (int i = 0; i < verticies.size(); ++i) {
            try {
                if (updatedValue(verticies.get(i)) <= 0)
                    return false; // only positive integers are allowed
            } catch(Exception e) { return false; }
        }
        return true;
    }

    public boolean hasOrientedCycles() { // only concerned with 3-cycles for now
        // searching for a relationship of this kind: (v1, v2), (v2, v3), (v3, v1)
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
}