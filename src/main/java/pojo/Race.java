/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author aviwembolekwa
 */
public class Race {
     private static final long serialVersionUID = 1L;
     
    int code;
    String fname, lname, type;
    Boolean question;

    public Race(int code, String fname, String lname, String type, Boolean question) {
        this.code = code;
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.question = question;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getQuestion() {
        return question;
    }

    public void setQuestion(Boolean question) {
        this.question = question;
    }
    
    

}
