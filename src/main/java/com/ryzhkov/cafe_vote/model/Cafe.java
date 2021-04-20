package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Cafe extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    @NonNull
    private String cafeName;

    @Column(name = "description")
    @NonNull
    private String description;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "homepage", nullable = false)
    @URL
    @NonNull
    private String homepage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cafe_id")
    @OrderBy("date desc")
//    @Setter(AccessLevel.NONE)
    @JsonManagedReference

    private Set<Voice> voices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cafe_id")
    @OrderBy("dish")
//    @Setter(AccessLevel.NONE)
    @JsonManagedReference

    private Set<Dish> menu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})

    @JsonBackReference

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
