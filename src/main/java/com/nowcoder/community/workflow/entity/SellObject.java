package com.nowcoder.community.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SellObject implements Serializable {
    String name;
    double price;
    String describe;
}
