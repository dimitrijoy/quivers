/**
 * The Arrow class is for constructing links of verticies
 * and arrows to form quivers or directed graphs.
 * 
 * @author  Dimitri Joy
 * @version 1.4
 * created on 2020-01-26
 */
class Arrow {
    private Vertex base; // starting vertex
    private Vertex terminal; // ending vertex

    /**
     * An Arrow is comprised of a base and terminal vertex
     * with an implied direction.
     * 
     * @param v1    the base vertex
     * @param v2    the terminal vertex
     */
    Arrow(Vertex v1, Vertex v2) {
        base = v1;
        terminal = v2;
    }

    /**
     * Create a copy of an existing arrow.
     * 
     * @param a     the arrow to be copied
     */
    Arrow(Arrow a) {
        base = new Vertex(a.getBase());
        terminal = new Vertex(a.getTerminal());
    }

    /**
     * Return the base vertex.
     * 
     * @return      the base vertex
     */
    public Vertex getBase() {
        return base;
    }

    /**
     * Return the terminal vertex.
     * 
     * @return      the terminal vertex
     */
    public Vertex getTerminal() {
        return terminal;
    }

    /**
     * Set the base vertex.
     * 
     * @param v     the new base vertex
     */
    public void setBase(Vertex v) {
        base = v;
    }

    /**
     * Set the terminal vertex.
     * 
     * @param v     the new terminal vertex
     */
    public void setTerminal(Vertex v) {
        terminal = v;
    }

    /**
     * Return the value of the base vertex.
     * 
     * @return      the value of the base vertex
     */
    public int getBaseValue() {
        return base.getValue();
    }

    /**
     * Return the value of the terminal vertex.
     * 
     * @return      the value of the terminal vertex
     */
    public int getTerminalValue() {
        return terminal.getValue();
    }

    /** 
     * Determine if a vertex is in the arrow.
     * 
     * @param v     the vertex to be checked
     * @return      whether or not the vertex was found
     */
    public boolean contains(Vertex v) {
        if (base == v || terminal == v) {
            return true;
        } else {
            return false;
        }
    }

    /** 
     * Determine if a vertex in the arrow contains a certain value.
     * 
     * @param val   the value to be checked
     * @return      whether or not the value was found
     */
    public boolean contains(int val) {
        if (base.getValue() == val || terminal.getValue() == val) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine if both verticies contain the same value.
     * 
     * @param val   the number to be checked
     * @return      whether or not both verticies contain the same value
     */
    public boolean strictlyContains(int val) {
        if (base.getValue() == val && terminal.getValue() == val) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Flip the direction of the arrow.
     */
    public void flip() {
        Vertex temp = base;
        base = terminal;
        terminal = temp;
    }

    /**
     * Determine if the arrow is a loop.
     * 
     * @return      whether or not the arrow is a loop
     */
    public boolean loops() {
        if (base == terminal) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine if two arrows form a loop.
     * 
     * @param a     the arrow to be check
     * @return      whether or not the two arrows form a loop
     */
    public boolean loopsWith(Arrow a) {
        if ((base == a.getTerminal()) && (terminal == a.getBase())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print the base and terminal verticies and the direction of the arrow.
     */
    public void print() {
        System.out.println("(" + base.getId() + base.getValue() + ") ---> (" + terminal.getId() + terminal.getValue() + ")");
    }
}