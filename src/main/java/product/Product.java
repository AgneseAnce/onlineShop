package product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Product {
@Id
@GeneratedValue
    private UUID id = UUID.randomUUID();
    private String name;
    private double price;
    private double quantity;
    @Enumerated(EnumType.STRING)
    private Category category;
//    private boolean availability;




}
