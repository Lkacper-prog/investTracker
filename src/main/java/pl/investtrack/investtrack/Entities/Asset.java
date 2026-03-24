package pl.investtrack.investtrack.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name ="asset")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ticker;
    @Column(name = "amount", nullable = false,precision=20,scale=8)
    private BigDecimal ammount;
    @Column(name= "purchase_price",nullable = false,precision=10,scale=2)
    private BigDecimal purchasePrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Asset(String ticker, BigDecimal ammount, BigDecimal purchasePrice,User user) {
        this.ticker = ticker;
        this.ammount = ammount;
        this.purchasePrice = purchasePrice;
        this.user=user;
    }
}

