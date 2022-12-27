package com.nowcoder.community.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SentObject implements Serializable {
    String address;
}
