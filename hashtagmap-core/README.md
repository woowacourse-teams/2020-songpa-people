# Core

## 모듈 설명

- DB와 연결되어 있는 도메인 모듈
- 도메인 모델과 Repository가 위치해있다.
- hashtagmap-common 모듈을 이용해 커스텀 예외 객체를 사용한다.

## 기술 스택

- Spring Data JPA
- Spring JDBC
    - uniqeu key로 저장된 값에 ON DUPLICATE KEY UPDATE 쿼리를 native sql로 작성하기 위함
- QueryDsl
- MariaDB