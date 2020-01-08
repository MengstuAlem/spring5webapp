package com.property.management.controller;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String ex) {
        super(ex);
    }
}
