package kz.ktu.touroperator.model;

import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_date")
    private String cardDate;

    @Column(name = "card_secret_number")
    private Long cardSecretNumber;

    @Column(name = "money_on_card")
    private BigDecimal moneyOnCard;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
