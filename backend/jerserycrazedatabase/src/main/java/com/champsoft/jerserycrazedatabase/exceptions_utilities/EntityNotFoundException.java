package com.champsoft.jerserycrazedatabase.exceptions_utilities;
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super("Owner not found with id" + message);
    }


}
