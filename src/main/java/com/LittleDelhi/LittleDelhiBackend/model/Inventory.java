package com.LittleDelhi.LittleDelhiBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //one ingredient has one inventory record
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private int quantity;

    private int threshold; //after the qty gets under the threshold the item is flagged

}
