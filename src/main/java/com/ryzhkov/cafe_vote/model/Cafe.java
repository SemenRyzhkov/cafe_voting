package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cafes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cafe extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    String cafeName;

    @Column(name = "description")
    String description;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "homepage", nullable = false)
    @URL
    String homepage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cafe_id")
    @OrderBy("date desc")
//    @Setter(AccessLevel.NONE)
    private Set<Voice> voices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cafe_id")
    @OrderBy("dish")
//    @Setter(AccessLevel.NONE)
    private Set<Dish> menu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private User user;


//    public void setMenu(Set<Dish> menu) {
//        //this.sonEntities = aSet; //This will override the set that Hibernate is tracking.
//        this.menu.clear();
//        if (menu != null) {
//            this.menu.addAll(menu);
//        }
//    }
//
//    public void setVoices(Set<Voice> voices) {
//        //this.sonEntities = aSet; //This will override the set that Hibernate is tracking.
//        this.voices.clear();
//        if (voices != null) {
//            this.voices.addAll(voices);
//        }
//    }

}
