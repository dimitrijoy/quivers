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
 * @version 1.5
 * created on 2020-01-26
 */
class Vertex {
    private int val;
    private String id;

    /**
     * A vertex comprised of default attributes.
     */
    Vertex() {
        // defaults
        val = 1;
        id = "";
    }

    /**
     * A vertex comprised of a value and default identifier.
     * 
     * @param val   the new value of the vertex
     */
    Vertex(int val) {
        this.val = val;
        id = "";
    }

    /**
     * A vertex comprised of an identifer and default value.
     * 
     * @param id    the new identifier of the vertex
     */
    Vertex(String id) {
        this.id = id;
        val = 1;
    }

    /**
     * A vertex comprised of a value and identifier.
     * 
     * @param   val the new value of the vertex
     * @param   id  the new identifier of the vertex
     */
    Vertex(int val, String id) {
        this.val = val;
        this.id = id;
    }

    /**
     * A vertex comprised of an identifier and value.
     * 
     * @param   id  the new identifier of the vertex
     * @param   val the new value of the vertex
     */
    Vertex(String id, int val) {
        this.id = id;
        this.val = val;
    }

    /**
     * Create a copy of an existing vertex.
     * 
     * @param   v   the vertex to be copied
     */
    Vertex(Vertex v) {
        this.id = v.id;
        this.val = v.val;
    }

    /**
     * Get the value of the vertex.
     * 
     * @return      the value of the vertex
     */
    public int getValue() {
        return val;
    }

    /**
     * Set the value of the vertex.
     * 
     * @param val   the new value of the vertex
     */
    public void setValue(int val) {
        this.val = val;
    }

    /**
     * Get the identifier of the vertex.
     * 
     * @return      the identifier of the vertex
     */
    public String getId() {
        return id;
    }

    /**
     * Set the identifier of the vertex.
     * 
     * @param id    the new identifier of the vertex
     */
    public void setId(String id) {
        this.id = id;
    }
}