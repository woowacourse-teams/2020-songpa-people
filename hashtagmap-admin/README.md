# ADMIN

## 모듈 설명

- 어플리케이션의 관리 페이지 API역할을 하는 모듈
- front 패키지에서 node run build를 하면 필요한 정적 파일이 static으로 이동한다.
- 데이터 수집 (지역구, 좌표, 가게, 인스타그램 해시태그) 에 대한 관리가 가능하다.
- 인증된 사람(관리자)만 접근 할 수 있다.
- core, kakao-scheduler, instagram-service, common 모듈을 의존하여 사용하고있다.

## 기술 스택

- Spring-boot-starter-web
    - Spring-webmvc 프레임워크를 활용해 Model-View-Controller 구조 설계
- Spring REST Docs를 활용한 api 문서화
- MockMvc를 활용한 Controller Layer 테스트 작성
- REST-assured를 활용한 End-to-End 테스트 작성
- Vue.js
