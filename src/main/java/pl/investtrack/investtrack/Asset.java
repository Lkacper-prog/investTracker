package pl.investtrack.investtrack;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name ="asset")
@Getter @Setter @NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String ticker;
    @Column(name = "amount", nullable = false,precision=20,scale=8)
    BigDecimal ammount;
    @Column(name= "purchase_price",nullable = false,precision=10,scale=2)
    BigDecimal purchasePrice;
    @Column(name= "user_id", nullable = false )
    Integer userId;

    public Asset(String ticker, BigDecimal ammount, BigDecimal purchasePrice, Integer userId) {
        this.ticker = ticker;
        this.ammount = ammount;
        this.userId = userId;
        this.purchasePrice = purchasePrice;
    }
}

