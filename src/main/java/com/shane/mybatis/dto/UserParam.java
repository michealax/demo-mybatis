package com.shane.mybatis.dto;

import com.shane.mybatis.common.group.EditValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParam implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty(message = "could not be empty", groups = {EditValidationGroup.class})
    private String userId;

    @NotEmpty(message = "could not be empty")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "could not be empty")
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$")
    private String cardNo;

    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 10, message = "nick name should be 1-10")
    private String nickName;

    @NotNull
    @Range(min = 0, max = 1, message = "sex should be 0 or 1")
    private int sex;

    @Max(value = 200, message = "Please input valid age")
    @Min(value = 0)
    private int age;

    @Valid
    private AddressParam address;
}
