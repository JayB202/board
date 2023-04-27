package com.sparta.board.dto.userDto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])[a-z0-9]*$")
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]*$" )
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
