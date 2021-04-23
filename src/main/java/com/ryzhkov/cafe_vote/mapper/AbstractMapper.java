package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.BaseDto;
import com.ryzhkov.cafe_vote.model.BaseEntity;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E extends BaseEntity, D extends BaseDto> implements Mapper<E, D> {

    @Autowired
    protected ModelMapper modelMapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
    }

    public List<D> toDto(Collection<E> entities){
        return Objects.isNull(entities)
                ?null
                : entities.stream().map(ent->modelMapper.map(ent, dtoClass)).collect(Collectors.toList());
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(E source, D destination) {};

    void mapSpecificFields(D source, E destination) {}

}
