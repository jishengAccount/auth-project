package com.jisheng.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu extends Permission{
    private List<Object> children;

}
