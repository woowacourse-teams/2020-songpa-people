## 기능 목록
-[X] Kakao 애플리케이션 등록
-[X] InitialRect에 대한 모든 데이터를 반환한다.
  -[x] InitialRect를 DividedRect로 나눈다.
  -[x] DividedRect에서 Response 가능한 Place 개수가 초과할 때
  재귀를 이용해서 범위를 재조정한다.
-[X] RestTemplate 통해 API 호출
    -[X] 카테고리: 카페
    -[x] WebClient에서 RestTemplate으로 수정
    -[x] Error Handling
-[ ] 카테고리 Type 분리

### 도메인 용어 정리

- Rect
  - 지도에 해당하는 사각형 범위
  - 기준점을 왼쪽 위로 고정한다.
    - 왼쪽 위의 좌표값과 offset(Rect의 선분)를 입력하면 Rect가 생성된다.
  - Latitude : 위도, x
    - forward 시 증가한다.
  - Longitude: 경도, y 
    - forward 시 감소한다.
- 주어진 Rect에 포함되는 가게 데이터가 기준치를 초과한 경우, Kakao API로 부터
해당 Rect의 모든 가게 데이터를 받아올 수 없다.  
따라서, 주어진 Rect가 Response 가능한 Place(가게) 데이터를 초과할 때
범위를 재조정하여 탐색한다.