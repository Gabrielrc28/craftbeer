package com.beerhouse.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beer")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Beer {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "alcohol_Content")
    private String alcoholContent;
    @Column(name = "price")
    private Number price;
    @Column(name = "category")
    private String category;
}
