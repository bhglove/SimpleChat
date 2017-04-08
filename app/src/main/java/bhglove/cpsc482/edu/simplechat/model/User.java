package bhglove.cpsc482.edu.simplechat.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Benjamin on 10/22/16.
 */

public class User implements Serializable{
    protected int id;
    private String name;
    private String number;


    public User(String name, String number){
        this.name = name;
        this.number = number;
    }

    protected User(int id, String name, String number){
        this(name, number);
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }
}

