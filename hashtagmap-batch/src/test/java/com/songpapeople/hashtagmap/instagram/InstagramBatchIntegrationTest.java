package com.songpapeople.hashtagmap.instagram;

import com.songpapeople.hashtagmap.dto.CrawlingDto;
import com.songpapeople.hashtagmap.dto.PostDto;
import com.songpapeople.hashtagmap.dto.PostDtos;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import com.songpapeople.hashtagmap.scheduler.CrawlingResult;
import com.songpapeople.hashtagmap.scheduler.InstagramScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
public class InstagramBatchIntegrationTest {
    public static final String CAFE_NAME = "카페카페페페";

    @Autowired
    private JobLauncherTestUtils myTestJobLauncher;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private InstagramRepository instagramRepository;

    @MockBean
    private InstagramScheduler instagramScheduler;

    @DisplayName("정상 작동")
    @Test
    public void successTest() throws Exception {
        // given
        Place place = placeRepository.save(
                Place.builder()
                        .placeName(CAFE_NAME)
                        .kakaoId("123")
                        .build()
        );

        List<PostDto> postDtos = new ArrayList<>();
        for (int i = 1; i <= PostDtos.POPULAR_POST_SIZE; i++) {
            postDtos.add(new PostDto(null, null));
        }

        CrawlingResult crawlingResult = new CrawlingResult(
                CrawlingDto.of(CAFE_NAME, "1000", new PostDtos(postDtos)),
                place);
        when(instagramScheduler.update(place)).thenReturn(Optional.of(crawlingResult));

        // when
        JobExecution jobExecution = myTestJobLauncher.launchJob();

        // then
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

    @DisplayName("예외가 발생하면 실패하는 chunk 이전까지 수행 후 종료")
    @Test
    void exceptionTest() throws Exception {
        // given
        List<Place> places = Arrays.asList(
                placeRepository.save(
                        Place.builder()
                                .placeName(CAFE_NAME)
                                .kakaoId("100")
                                .build()
                ),
                placeRepository.save(
                        Place.builder()
                                .placeName(CAFE_NAME)
                                .kakaoId("101")
                                .build()
                ),
                placeRepository.save(
                        Place.builder()
                                .placeName(CAFE_NAME)
                                .kakaoId("102")
                                .build()
                )
        );

        List<PostDto> postDtos = new ArrayList<>();
        for (int i = 0; i < PostDtos.POPULAR_POST_SIZE; i++) {
            postDtos.add(new PostDto(null, null));
        }

        CrawlingResult crawlingResult1 = new CrawlingResult(
                CrawlingDto.of(CAFE_NAME, "1000", new PostDtos(postDtos)),
                places.get(0));
        CrawlingResult crawlingResult2 = new CrawlingResult(
                CrawlingDto.of(CAFE_NAME, "1000", new PostDtos(postDtos)),
                places.get(1));
        when(instagramScheduler.update(places.get(0))).thenReturn(Optional.of(crawlingResult1));
        when(instagramScheduler.update(places.get(1))).thenReturn(Optional.of(crawlingResult2));
        when(instagramScheduler.update(places.get(2))).thenThrow(new IllegalArgumentException("크롤링 관련 오류"));

        // when
        JobExecution jobExecution = myTestJobLauncher.launchJob();
        List<StepExecution> stepExecutions = new ArrayList<>(jobExecution.getStepExecutions());
        StepExecution stepExecution = stepExecutions.get(0);

        // then
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("FAILED");
        assertThat(stepExecution.getWriteCount()).isEqualTo(2);
        assertThat(stepExecution.getRollbackCount()).isEqualTo(1);
    }

    @AfterEach
    private void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
