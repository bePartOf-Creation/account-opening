package com.accountopeningapplication.enums;

import com.accountopeningapplication.entities.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Getter
public enum AccountType {
    SAVINGS,CURRENT
}
