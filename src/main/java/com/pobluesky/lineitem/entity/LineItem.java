package com.pobluesky.lineitem.entity;

import com.pobluesky.global.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class LineItem extends BaseEntity {

    protected Boolean isActivated;
    public void deleteLineItem(){
        this.isActivated = false;
    }
}
