package com.jisheng.common.entity.resultCommon;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    private int total;
    private List<Object> rows;

}
