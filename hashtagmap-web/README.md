# Web

## 모듈 설명

- 사용자를 위한 기능을 제공하는 어플리케이션 모듈.
- hashtagmap-core 모듈을 사용하여 데이터를 조회한다.
- hashtagmap-common 모듈에서 api 응답 객체를 사용한다.

## 기술 스택

- Vue.js
    - PWA를 이용해 모바일에서도 서비스 활용성 증가
- 무중단 배포
    - nginx를 활용해 무중단 배포로 운영 중
    - Spring actuator를 활용해 어플리케이션의 상태를 관제 중
- spring-boot-starter-web
    - spring-webmvc 프레임워크를 활용해 Model-View-Controller 구조 설계
- Spring REST Docs를 활용한 api 문서화
- JUnit5
- MockMvc를 활용한 Controller Layer 테스트 작성
- REST-assured를 활용한 End-to-End 테스트 작성