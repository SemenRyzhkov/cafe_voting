package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.VoiceDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Voice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface VoiceMapper {

    VoiceDto toDto(Voice voice);

    @InheritInverseConfiguration
    Voice toEntity(VoiceDto dto);

}
