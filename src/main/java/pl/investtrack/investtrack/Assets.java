package pl.investtrack.investtrack;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name ="assets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String ticker;
    @Column(nullable = false)
    BigDecimal ammount;
    @Column(nullable = false,precision=20,scale=8)
    BigDecimal purchasePrice;
    @Column(name= "user_id", nullable = false ,precision=10,scale=2)
    Integer userId;
}

