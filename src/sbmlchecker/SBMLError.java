/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlchecker;

/**
 *
 * @author Mathialakan.Thavappi
 */

public class SBMLError {
   
    private int id;
    private int column;
    private int line;
    private int code;
    private String category;
    private String escerpt;
    private String serverity;
    private String message;

    public SBMLError() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEscerpt() {
        return escerpt;
    }

    public void setEscerpt(String escerpt) {
        this.escerpt = escerpt;
    }

    public String getServerity() {
        return serverity;
    }

    public void setServerity(String serverity) {
        this.serverity = serverity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
