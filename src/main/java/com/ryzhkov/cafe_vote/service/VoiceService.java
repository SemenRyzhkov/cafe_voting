//package com.ryzhkov.cafe_vote.service;
//
//import com.ryzhkov.cafe_vote.dto.VoiceDto;
//import com.ryzhkov.cafe_vote.mapper.VoiceMapper;
//import com.ryzhkov.cafe_vote.model.Cafe;
//import com.ryzhkov.cafe_vote.model.User;
//import com.ryzhkov.cafe_vote.model.Voice;
//import com.ryzhkov.cafe_vote.repository.CafeRepository;
//import com.ryzhkov.cafe_vote.repository.UserRepository;
//import com.ryzhkov.cafe_vote.repository.VoiceRepository;
//import com.ryzhkov.cafe_vote.util.exception.NotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class VoiceService {
//    private final CafeRepository cafeRepository;
//    private final UserRepository userRepository;
//    private final VoiceRepository voiceRepository;
//    private final VoiceMapper voiceMapper;
//
//    @Transactional
//    public String makeVoice(int userId, int cafeId) {
//        Cafe cafe = getCafe(cafeId);
//        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
//        Voice voice = get(userId);
//
//        String answer = "You have successfully voted";
//
//        if (voice == null) {
//            voice = new Voice(null, LocalDate.now(), LocalTime.now(), user.getId());
//            voice.setCafeId(cafe.getId());
//        } else if (checkTimeOfVoice()) {
//            voice.setCafeId(cafe.getId());
//            voice.setTime(LocalTime.now());
//        } else {
//            answer = "You have already voted today";
//        }
//
//        voiceRepository.save(voice);
//
//        return answer;
//    }
//
//    public Map<LocalDate, Long> historyOfVoting(int userId, int cafeId) {
//        Cafe cafe = getCafe(cafeId);
//        if (cafe!=null && cafe.getUser().getId() == userId) {
//            return voiceRepository.getHistoryOfVoting(cafeId)
//                    .stream()
//                    .collect(Collectors.groupingBy(Voice::getDate, Collectors.counting()));
//        }else return null;
//    }
//
//    public int todayVoting(int userId, int cafeId) {
//        Cafe cafe = getCafe(cafeId);
//        if (cafe!=null && cafe.getUser().getId()==userId){
//            return voiceRepository.todayVoting(cafeId).size();
//        } else return 0;
//    }
//
//    private boolean checkTimeOfVoice() {
//        return LocalTime.now().compareTo(LocalTime.of(11, 0)) < 0;
//    }
//
//    private Voice get(int userId) {
//        return voiceRepository.findByUserIdAndCurrentDate(userId).orElse(null);
//    }
//
//    private Cafe getCafe(int cafeId) {
//        return cafeRepository.findById(cafeId).orElseThrow(() -> new NotFoundException("cafe not found"));
//    }
//}
