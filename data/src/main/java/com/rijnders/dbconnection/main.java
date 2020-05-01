package com.rijnders.dbconnection;

import com.rijnders.entityinterfaces.User;
import com.rijnders.users.AllUsersDao;

public class main {
    public static void main(String[] args) {
        AllUsersDao allUsersDao = new AllUsersDao();
        for(User u : allUsersDao.selectAllUsers()){
            System.out.println(u.getUserName());
        }
    }
}
