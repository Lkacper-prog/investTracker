package pl.investtrack.investtrack.asset;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.investtrack.investtrack.user.User;

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
    private BigDecimal amount;
    @Column(name= "purchase_price",nullable = false,precision=10,scale=2)
    private BigDecimal purchasePrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name= "type" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfAsset type;

    public Asset(String ticker, BigDecimal amount, BigDecimal purchasePrice, User user) {
        this.ticker = ticker;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.user=user;
    }
}

