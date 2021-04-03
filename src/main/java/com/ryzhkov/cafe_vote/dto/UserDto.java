package com.ryzhkov.cafe_vote.dto;

import com.ryzhkov.cafe_vote.model.Role;
import lombok.*;

import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends BaseDto{
    private String name;
    private String email;
    private Set<Role>roles;
//    private Map<Integer, String> cafes;
}
