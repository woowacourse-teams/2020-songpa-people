# Kakao Api

## 모듈 설명
- Kakao Map Api를 이용하여 카카오맵의 데이터를 제공받는다.

## 기술 스택
- Rest Template


**사용방법**

findPlaces(가게 카테고리, 검색할 지역 좌표)
```java
public List<KakaoPlaceDto> findPlaces(String category, Rect rect) {
        return findPlaces(category, rect, DEFAULT_OFFSET);
    }
```
