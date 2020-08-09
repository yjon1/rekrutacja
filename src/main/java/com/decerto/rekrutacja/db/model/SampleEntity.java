package com.decerto.rekrutacja.db.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table
public class SampleEntity {

    @Id
    String uuid;
    Integer intValue;
}
