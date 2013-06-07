/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.agend.logic;

import java.util.Date;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class Task {
    
    private String name;
    private String description;
    private Date date;

    public Task(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "Task{" + "name=" + name + ", description=" + description + ", date=" + date + '}';
    }
    
    
}
