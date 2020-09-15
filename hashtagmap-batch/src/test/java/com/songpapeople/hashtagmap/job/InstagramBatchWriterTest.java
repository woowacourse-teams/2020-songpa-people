package com.songpapeople.hashtagmap.job;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InstagramBatchWriterTest {
    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    private InstagramBatchWriter instagramBatchWriter;
    private List<PostDto> postDtos;

    @BeforeEach
    private void setUp() {
        instagramBatchWriter = new InstagramBatchWriter(instagramRepository, instagramPostRepository);
    }

    @DisplayName("크롤링한 데이터를 저장하는지 확인")
    @TestFactory
    Collection<DynamicTest> writeTest() {
        postDtos = new ArrayList<>();
        for (int i = 1; i <= PostDtos.POPULAR_POST_SIZE; i++) {
            String dummy = String.valueOf(i);
            postDtos.add(new PostDto(dummy, dummy));
        }

        Place place1 = placeRepository.save(Place.builder().kakaoId("1234").build());
        Place place2 = placeRepository.save(Place.builder().kakaoId("12345").build());
        Optional<CrawlingResult> crawlingResult1 = Optional.of(new CrawlingResult(
                CrawlingDto.of("스타벅스", "1234", new PostDtos(postDtos)), place1
        ));
        Optional<CrawlingResult> crawlingResult2 = Optional.of(new CrawlingResult(
                CrawlingDto.of("버거킹", "12345", new PostDtos(postDtos)), place2
        ));
        List<Optional<CrawlingResult>> crawlingResults = Arrays.asList(crawlingResult1, crawlingResult2);

        return Arrays.asList(
                DynamicTest.dynamicTest("크롤링한 Instagram, InstagramPost 저장한다.", () -> {
                    instagramBatchWriter.write(crawlingResults);

                    List<Instagram> instagrams = instagramRepository.findAll();
                    List<InstagramPost> instagramPosts = instagramPostRepository.findAll();

                    assertThat(instagrams).hasSize(2);
                    assertThat(instagramPosts).hasSize(18);
                }),
                DynamicTest.dynamicTest("이미 저장된 Instagram, InstagramPost는 삭제 후 저장한다.", () -> {
                    instagramBatchWriter.write(Collections.singletonList(crawlingResult1));

                    List<Instagram> instagrams1 = instagramRepository.findAll();
                    assertThat(instagrams1).hasSize(2);
                })
        );
    }

    @DisplayName("크롤링에 실패해서 정보가 없을 때 저장하지 않는다.")
    @Test
    void writeWithEmptyCrawlingResultTest() {
        Optional<CrawlingResult> crawlingResult = Optional.empty();

        instagramBatchWriter.write(Collections.singletonList(crawlingResult));

        List<Instagram> instagrams = instagramRepository.findAll();
        List<InstagramPost> instagramPosts = instagramPostRepository.findAll();

        assertThat(instagrams).hasSize(0);
        assertThat(instagramPosts).hasSize(0);
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        instagramPostRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
