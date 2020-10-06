package com.songpapeople.hashtagmap.instagram.writer;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramQueryRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.service.CrawlingResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InstagramBatchWriterTest {
    @Autowired
    private InstagramQueryRepository instagramQueryRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private InstagramPostRepository instagramPostRepository;

    @Autowired
    private PlaceRepository placeRepository;

    private InstagramBatchWriter instagramBatchWriter;

    @BeforeEach
    private void setUp() {
        instagramBatchWriter = new InstagramBatchWriter(instagramRepository, instagramQueryRepository, instagramPostRepository);
    }

    @DisplayName("크롤링한 데이터를 저장하는지 확인")
    @TestFactory
    Collection<DynamicTest> writeTest() {
        List<PostDto> postDtos = new ArrayList<>();
        for (int i = 1; i <= PostDtos.POPULAR_POST_SIZE; i++) {
            String dummy = String.valueOf(i);
            postDtos.add(new PostDto(dummy, dummy));
        }

        Place place1 = placeRepository.save(Place.builder().kakaoId("1234").build());
        Place place2 = placeRepository.save(Place.builder().kakaoId("12345").build());

        CrawlingResult crawlingResult1 = new CrawlingResult(CrawlingDto.of("스타벅스", "1234", new PostDtos(postDtos)), place1);
        CrawlingResult crawlingResult2 = new CrawlingResult(CrawlingDto.of("버거킹", "12345", new PostDtos(postDtos)), place2);
        List<CrawlingResult> crawlingResults = Arrays.asList(crawlingResult1, crawlingResult2);

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

    @AfterEach
    void tearDown() {
        instagramPostRepository.deleteAll();
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
