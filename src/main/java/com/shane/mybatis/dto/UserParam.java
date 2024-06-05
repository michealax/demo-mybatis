package com.shane.mybatis.dto;

import com.shane.mybatis.common.group.EditValidationGroup;
import com.shane.mybatis.common.validation.CardNumber;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "UserParam", subTypes = {AddressParam.class})
public class UserParam implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty(message = "{user.msg.userId.notEmpty}", groups = {EditValidationGroup.class})
    private String userId;

    @NotEmpty(message = "could not be empty")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "could not be empty")
    @CardNumber
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
