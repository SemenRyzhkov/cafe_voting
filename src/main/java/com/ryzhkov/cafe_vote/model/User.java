package com.ryzhkov.cafe_vote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString(callSuper = true, exclude = {"password"})
public class User extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public User(User user) {
        this(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getRole(), user.getStatus());
    }

    public User(Integer id, String name, String password, String email, Role role, Status status) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
