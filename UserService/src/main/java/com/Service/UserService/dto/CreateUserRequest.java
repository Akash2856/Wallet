package com.Service.UserService.dto;

import com.Service.UserService.Enum.UserIdentificationType;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    String name;
    String email;
    @NotBlank
    String phoneNo;
    @NotBlank //not null + not balnk
    String password;

    @Nonnull // "" pass in case of not null
    UserIdentificationType userIdentificationType;
    //@Nonnull
    String userIdentificationValue;
}
