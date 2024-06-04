package com.shane.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressParam implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty(message = "could not be empty")
    private String userId;

    private String addressDetail;
}
