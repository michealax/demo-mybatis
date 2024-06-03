package com.shane.mybatis.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDomain {
    private int pageNum;

    private int pageSize;

    private String orderByColumn;

    private String isAsc;
}
