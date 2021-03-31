package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Voice;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CafeMapper extends AbstractMapper<Cafe, CafeDto> {

    //    @Autowired
//    public CafeMapper() {
//        super(Cafe.class, CafeDto.class);
//    }
    private ModelMapper mapper;
    private DishMapper dishMapper;
    private UserRepository repository;

    @Autowired
    public CafeMapper(ModelMapper mapper, DishMapper dishMapper, UserRepository repository) {
        super(Cafe.class, CafeDto.class);
        this.mapper = mapper;
        this.dishMapper = dishMapper;
        this.repository = repository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Cafe.class, CafeDto.class)
                .addMappings(m -> m.skip(CafeDto::setMenu))
                .addMappings(m -> m.skip(CafeDto::setVoicesToday))
                .addMappings((m -> m.skip(CafeDto::setUserId)))
                .setPostConverter(toDtoConverter());
    }

    @Override
    protected void mapSpecificFields(Cafe source, CafeDto destination) {
        destination.setUserId(getId(source));
        destination.setMenu(getMenu(source));
        destination.setVoicesToday(getVoices(source));
    }

    ;


    protected Integer getId(Cafe source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }

    //
    protected Set<DishDto> getMenu(Cafe source) {
        return source.getMenu().stream()
                .filter(dish -> dish.getDate().equals(LocalDate.now()))
                .map(dish -> dishMapper.toDto(dish))
                .collect(Collectors.toSet());
    }

    protected Integer getVoices(Cafe source) {
        return source.getVoices().stream()
                .filter(voice -> voice.getDate().equals(LocalDate.now()))
                .mapToInt(voice->1)
                .sum();
    }
}
