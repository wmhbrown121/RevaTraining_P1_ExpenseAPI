package dev.hbrown.entities;

import org.intellij.lang.annotations.JdkConstants;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private int id;

    @Column(name="manager_name")
    private String name;

    @Column(name="manager_username")
    private String username;

    @Column(name="manager_pass")
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
