package com.ryzhkov.cafe_vote.test_data;

import com.ryzhkov.cafe_vote.TestMatcher;
import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.model.Cafe;

import java.util.List;
import java.util.stream.Stream;

public class CafeTestData {
//    public static final TestMatcher<Cafe> CAFE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(
//            Cafe.class, "menu", "voices");
    public static final TestMatcher<CafeDto> CAFE_DTO_MATCHER = TestMatcher.usingEqualsComparator(CafeDto.class);

    public static final int CAFE1_ID = 1;

    public static final Cafe cafe1 = new Cafe(CAFE1_ID, "cafe1", null, "http://cafe1.com");
    public static final Cafe cafe2 = new Cafe(CAFE1_ID + 1, "cafe2", "new_cafe", "http://cafe2.com");
    public static final Cafe cafe3 = new Cafe(CAFE1_ID + 2, "cafe3", "best_cafe", "http://cafe3.com");
    public static final Cafe cafe4 = new Cafe(CAFE1_ID + 3, "cafe4", "beautiful_cafe", "http://cafe4.com");
    public static final Cafe cafe5 = new Cafe(CAFE1_ID + 4, "cafe5", "old_cafe", "http://cafe5.ru");
    public static final Cafe cafe6 = new Cafe(CAFE1_ID + 5, "cafe6", null, "http://cafe6.ru");
    public static final Cafe cafe7 = new Cafe(CAFE1_ID + 6, "cafe7", "bar_cafe", "http://cafe7.ru");
    public static final Cafe cafe8 = new Cafe(CAFE1_ID + 7, "cafe8", "otherCafe", "http://cafe8.by");
    public static final Cafe cafe9 = new Cafe(CAFE1_ID + 8, "cafe9", "otherCafe", "http://cafe9.by");


    public static final List<Cafe> admin1Cafe = List.of(cafe1, cafe2, cafe3, cafe4, cafe5, cafe6, cafe7);
    public static final List<Cafe> allCafe = List.of(cafe1, cafe2, cafe3, cafe4, cafe5, cafe6, cafe7, cafe8, cafe9);

    static {
        admin1Cafe.forEach(cafe -> cafe.setUser(UserTestData.admin1));
        Stream.of(cafe8, cafe9).forEach(cafe -> cafe.setUser(UserTestData.admin2));
    }


    public static Cafe getNew() {
        return new Cafe(null, "newCafe", "newDescription", "http://newCafe.ru");
    }

    public static Cafe getUpdated() {
        return new Cafe(CAFE1_ID, "updCafe", "updDescription", "http://updCafe.com");
    }

}
