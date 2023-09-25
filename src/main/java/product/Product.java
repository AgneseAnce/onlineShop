package product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity (name="product")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Product {
@Id
@GeneratedValue
    private UUID id;
    private String name;
    private double price;
    private double quantity;
    private Category category;
//    private boolean availability;




}
