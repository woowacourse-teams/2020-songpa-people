package com.songpapeople.hashtagmap.integration;

import com.songpapeople.hashtagmap.config.vo.Flag;
import com.songpapeople.hashtagmap.kakao.schedule.model.PeriodHistory;
import com.songpapeople.hashtagmap.kakao.schedule.model.Schedule;
import com.songpapeople.hashtagmap.kakao.schedule.repository.PeriodHistoryRepository;
import com.songpapeople.hashtagmap.kakao.schedule.repository.ScheduleRepository;
import com.songpapeople.hashtagmap.kakao.service.dto.KakaoScheduleToggleDto;
import com.songpapeople.hashtagmap.kakao.service.dto.PeriodHistoryDto;
import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.scheduler.domain.KakaoScheduler;
import com.songpapeople.hashtagmap.scheduler.exception.KakaoSchedulerExceptionStatus;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KakaoScheduleIntegrationTest {
    @LocalServerPort
    public int port;

    @Autowired
    private PeriodHistoryRepository periodHistoryRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private KakaoScheduler kakaoScheduler;

    protected static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    private void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("카카오 스케줄러 주기 변경")
    @Test
    public void changePeriodTest() throws Exception {
        String validExpression = "0 0/5 * * * ?";
        CustomResponse<Void> response = changePeriod(validExpression, HttpStatus.OK);

        assertThat(response.getData()).isNull();
        assertThat(response.getCode()).isNull();
        assertThat(response.getMessage()).isNull();
    }

    @DisplayName("(예외) 잘못된 주기(정규식)으로 주기 변경 실패")
    @Test
    public void changePeriodExceptionTest() throws Exception {
        String invalidExpression = "* * * * * * /";
        CustomResponse<Void> response = changePeriod(invalidExpression, HttpStatus.BAD_REQUEST);

        KakaoSchedulerExceptionStatus exceptionStatus = KakaoSchedulerExceptionStatus.INVALID_PERIOD_EXPRESSION;

        assertThat(response.getData()).isNull();
        assertThat(response.getCode()).isEqualTo(exceptionStatus.getCode());
        assertThat(response.getMessage()).isEqualTo(exceptionStatus.getMessage());
    }

    @DisplayName("주기 변경 기록 조회")
    @Test
    public void showPeriodHistoryTest() {
        List<PeriodHistory> expected = periodHistoryRepository.saveAll(Arrays.asList(
                new PeriodHistory("0 0/5 * * * ?"),
                new PeriodHistory("0 0 * * * ?")
        ));

        CustomResponse<List<PeriodHistoryDto>> response = showPeriodHistory();

        List<PeriodHistoryDto> actual = response.getData();
        assertThat(actual).hasSameSizeAs(expected);
        assertThat(response.getCode()).isNull();
        assertThat(response.getMessage()).isNull();
    }

    @DisplayName("KakaoScheduler 실행 상태 toggle 하기")
    @Test
    void toggleSchedulerTest() {
        //given
        String scheduleName = "KAKAO";
        KakaoScheduleToggleDto kakaoScheduleToggleDto = new KakaoScheduleToggleDto(scheduleName);
        scheduleRepository.save(new Schedule(scheduleName, "", Flag.Y));

        //when
        CustomResponse<Void> response = toggleScheduler(kakaoScheduleToggleDto, HttpStatus.OK);

        //then
        Schedule schedule = scheduleRepository.findByName(scheduleName).orElseThrow(RuntimeException::new);
        assertThat(schedule.isActive()).isFalse();
    }

    @DisplayName("KakaoScheduler 실행 상태 조회하기")
    @Test
    void getActiveStatus() {
        //given
        String scheduleName = "KAKAO";
        scheduleRepository.save(new Schedule(scheduleName, "", Flag.Y));

        //when
        CustomResponse<Boolean> response = getActiveStatus(scheduleName, HttpStatus.OK);

        //then
        assertThat(response.getData()).isTrue();
    }

    public CustomResponse<Void> toggleScheduler(KakaoScheduleToggleDto kakaoScheduleToggleDto, HttpStatus expectStatus) {
        return given()
                .body(kakaoScheduleToggleDto)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/kakao/scheduler/toggle")
                .then()
                .log().all()
                .statusCode(expectStatus.value())
                .extract().as(new TypeRef<CustomResponse<Void>>() {
                });
    }

    public CustomResponse<Boolean> getActiveStatus(String schedulerName, HttpStatus expectStatus) {
        return given()
                .param("name", schedulerName)
                .when()
                .get("/kakao/scheduler/status")
                .then()
                .log().all()
                .statusCode(expectStatus.value())
                .extract().as(new TypeRef<CustomResponse<Boolean>>() {
                });
    }

    public CustomResponse<Void> changePeriod(String expression, HttpStatus expect) {
        return given()
                .body(expression)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/kakao/scheduler/period")
                .then()
                .log().all()
                .statusCode(expect.value())
                .extract().as(new TypeRef<CustomResponse<Void>>() {
                });
    }

    public CustomResponse<List<PeriodHistoryDto>> showPeriodHistory() {
        return given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/kakao/scheduler/period")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().as(new TypeRef<CustomResponse<List<PeriodHistoryDto>>>() {
                });
    }

    @AfterEach
    private void tearDown() {
        periodHistoryRepository.deleteAll();
        scheduleRepository.deleteAll();
        kakaoScheduler.stop();
    }
}
