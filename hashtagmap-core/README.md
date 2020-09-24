# Core

## 모듈 설명

- DB와 연결되어 있는 도메인 모듈
- 도메인 모델과 Repository가 위치해있다.

## 기술 스택

- Spring Data JPA
- Spring JDBC
    - batch update를 List로 넘겨서 효율적으로 처리하기 위함
- QueryDsl
- MariaDB