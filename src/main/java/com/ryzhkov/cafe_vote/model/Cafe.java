package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cafes")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cafe extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String cafeName;

    @Column(name = "description")
    private String description;

    @Column(name = "homepage", nullable = false)
    private String homepage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cafe_id")
    @OrderBy("dish")
    @JsonManagedReference
    private List<Dish> menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    public Cafe(Cafe cafe){
        this(cafe.getId(), cafe.getCafeName(), cafe.getDescription(), cafe.getHomepage());
    }

    public Cafe(Integer id, String cafeName, String description, String homepage){
        super(id);
        this.cafeName = cafeName;
        this.description = description;
        this.homepage = homepage;
    }

}
