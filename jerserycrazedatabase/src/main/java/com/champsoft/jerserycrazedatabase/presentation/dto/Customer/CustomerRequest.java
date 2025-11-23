package com.champsoft.jerserycrazedatabase.presentation.dto.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String address,
        @NotBlank String password
) {
}
