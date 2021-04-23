package com.ryzhkov.cafe_vote.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable{
    private Integer id;

    public boolean isNew() {
        return this.id == null;
    }
}

