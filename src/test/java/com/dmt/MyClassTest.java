package com.dmt;

import com.dmt.bean.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    MyClass myClass;
    User user;

    @BeforeEach
    void setUp() {
        myClass = new MyClass();
        user = new User();
    }

    @AfterEach
    void tearDown() {
        myClass = null;
        User user = null;
    }

    @Test
    void namePattern() {
        user = myClass.namePattern("Donnie Magno Tarrosa");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());
    }

    @Test
    void namePatternLastNameFirst() {
        user = myClass.namePattern("Tarrosa, Donnie Magno");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());
    }

    @Test
    void namePatternWithSuffixFirst() {
        user = myClass.namePattern("Tarrosa Jr., Donnie Magno");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());
    }

    @Test
    void namePatternWithTitle() {
        user = myClass.namePattern("Engr. Donnie Magno Tarrosa");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());
    }

    @Test
    void namePatternWithSuffix() {
        user = myClass.namePattern("Donnie Magno Tarrosa Jr.");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());;
    }

    @Test
    void namePatternWithTitleAndSuffix() {
        user = myClass.namePattern("Engr. Donnie Magno Tarrosa Jr.");

        assertEquals("Tarrosa", user.getLastName());
        assertEquals("Donnie", user.getFirstName());
    }
}