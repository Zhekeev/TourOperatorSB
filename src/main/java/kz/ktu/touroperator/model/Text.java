package kz.ktu.touroperator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "text_for_admin")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToOne(mappedBy = "textForAdmin")
    private TextForUser textForUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}