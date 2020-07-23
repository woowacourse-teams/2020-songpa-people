// package com.songpapeople.hashtagmap.scheduler;
//
// import com.songpapeople.hashtagmap.dto.CrawlingDto;
// import com.songpapeople.hashtagmap.place.domain.model.Place;
// import com.songpapeople.hashtagmap.proxy.Proxy;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
//
// import static org.assertj.core.api.Assertions.*;
//
// class InstagramSchedulerTest {
//     private InstagramScheduler instagramScheduler = new InstagramScheduler();
//
//     @DisplayName("프록시 설정후 인스타 크롤링 테스트")
//     @Test
//     void crawlingWithProxy() {
//         String host = "123.123.123.123";
//         String port = "8080";
//         Proxy proxy = new Proxy(host, port);
//         Place place = Place.builder()
//                 .placeName("스타벅스")
//                 .build();
//
//         CrawlingDto crawlingDto = instagramScheduler.crawlingWithProxy(proxy, place);
//         assertThat(crawlingDto.getPlaceName()).isEqualTo("스타벅스");
//     }
// }
