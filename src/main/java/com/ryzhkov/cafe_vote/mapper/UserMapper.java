package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.model.BaseEntity;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Component

public class UserMapper extends AbstractMapper<User, UserDto> {
    @Autowired
    public UserMapper() {
        super(User.class, UserDto.class);
    }

//    private final ModelMapper mapper;
//
//    public UserMapper(ModelMapper mapper) {
//        super(User.class, UserDto.class);
//        this.mapper = mapper;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(User.class, UserDto.class)
//                .addMappings(m -> m.skip(UserDto::setCafes)).setPostConverter(toDtoConverter());
//    }
//
//    @Override
//    void mapSpecificFields(User source, UserDto destination) {
//        destination.setCafes(getCafes(source));
//    }
//
//    private Map<Integer, String> getCafes(User source) {
//        return source.getCafes().stream()
//                .collect(Collectors.toMap(BaseEntity::getId, Cafe::getCafeName));
//    }
}
