package com.dmt;

import com.dmt.bean.User;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        myClass.namePattern("Tarrosa Jr., Donnie Magno");
    }

    public User namePattern(String name) {
        User user = null;

        if(!name.contains(".") && !name.contains(",")) {
            user = new User();

            List<String> names = Arrays.asList(name.split(" "));
            user.setLastName(names.get(2));
            user.setFirstName(names.get(0));
        }

        if(name.contains(".") && name.contains(",")) {
            user = new User();

            List<String> names = Arrays.asList(name.split(" "));
            user.setLastName(names.get(0));
            user.setFirstName(names.get(2));
        }

        if(!name.contains(".") && name.contains(", ")) {
            user = new User();

            List<String> names = Arrays.asList(name.split(", "));
            user.setLastName(names.get(0));

            List<String> firstName = Arrays.asList(names.get(1).split(" "));
            user.setFirstName(firstName.get(0));
        }

        if (name.contains(".") && !name.contains(", ")) {
            List<String> names = Arrays.asList(name.split(" "));

            if (names.get(0).contains(".")) {
                user = new User();

                user.setLastName(names.get(3));
                user.setFirstName(names.get(1));
            } else {
                user = new User();

                user.setLastName(names.get(2));
                user.setFirstName(names.get(0));
            }

        }

        return user;
    }
}
