package kz.ktu.touroperator.model;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "text_for_user")
public class TextForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="text_id", referencedColumnName = "id")
    private Text textForAdmin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}