package com.jerrycodes.emis.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String message){
        super(message);
    }

}
