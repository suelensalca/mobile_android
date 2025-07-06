package com.example.suelen_somativa2.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private static final DataModel instance = new DataModel();
    private final List<UserDetails> userList;

    private DataModel() {
        userList = new ArrayList<>();
        userList.add(new UserDetails("casa1", "senha1"));
        userList.add(new UserDetails("casa2", "senha2"));
        userList.add(new UserDetails("casa3", "senha3"));
        userList.add(new UserDetails("casa4", "senha4"));
        userList.add(new UserDetails("casa5", "senha5"));
        userList.add(new UserDetails("casa6", "senha6"));
    }

    public static DataModel getInstance() {
        return instance;
    }

    public List<UserDetails> getUserList() {
        return userList;
    }

    public void addUser(UserDetails user) {
        userList.add(user);
    }

    public void removeUser(UserDetails user) {
        userList.remove(user);
    }
}
