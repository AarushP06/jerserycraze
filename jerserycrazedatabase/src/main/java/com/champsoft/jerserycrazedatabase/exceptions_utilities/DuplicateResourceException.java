package com.champsoft.jerserycrazedatabase.exceptions_utilities;
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super("Duplicate resource error" + message);
    }


}
