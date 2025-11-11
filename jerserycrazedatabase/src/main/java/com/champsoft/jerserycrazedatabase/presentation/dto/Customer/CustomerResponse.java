package com.champsoft.jerserycrazedatabase.presentation.dto.Customer;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String address
) {
}
