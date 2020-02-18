/**
 * NOTE: If double assignment is preferred, the params and/or the return type
 * of the following constructors/methods must be changed to double as well as val:
 * Vertex.java : Vertex(), getValue(), setValue()
 * Arrow.java  : getBaseValue(), getTerminalValue()
 * Quiver.java : update()
 * 
 * The Vertex class is for constructing vertices used
 * in conjunction with arrows as a part of a quiver.
 * 
 * @author  Dimitri Joy
 * @version 1.3
 * created on 2020-01-26
 */
class Vertex {
    private int val;
    private String id;

    /**
     * A Vertex is comprised of a single numeric value.
     * 
     * @param val
     */
    Vertex(int val) {
        this.val = val;
        id = "";
    }

    /**
     * A Vertex is comprised of a single numeric value and (optionally) a string
     * for identification.
     * 
     * @param   val the new value of the vertex
     * @param   id  the new id of the vertex
     */
    Vertex(int val, String id) {
        this.val = val;
        this.id = id;
    }

    /**
     * A Vertex is comprised of a single numeric value and (optionally) a string
     * for identification.
     * 
     * @param   id  the new value of the vertex
     * @param   val the new id of the vertex
     */
    Vertex(String id, int val) {
        this.id = id;
        this.val = val;
    }

    /**
     * Return the value of the vertex.
     * 
     * @return      the value of the vertex
     */
    public int getValue() {
        return val;
    }

    /**
     * Set the value of the vertex.
     * 
     * @param   val the new value of the vertex
     */
    public void setValue(int val) {
        this.val = val;
    }

    /**
     * Set the value of the vertex.
     * 
     * @return      the id of the vertex
     */
    public String getId() {
        return id;
    }
}