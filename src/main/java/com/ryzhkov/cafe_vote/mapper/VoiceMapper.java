package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.VoiceDto;
import com.ryzhkov.cafe_vote.model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoiceMapper extends AbstractMapper<Voice, VoiceDto> {
    @Autowired
    public VoiceMapper() {
        super(Voice.class, VoiceDto.class);
    }
}
