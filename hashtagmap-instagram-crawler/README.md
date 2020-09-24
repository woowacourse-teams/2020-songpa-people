# Instagram Crawler

## 모듈 설명

- 인스타그램에서 가게 이름에 대한 해시태그 수, 이미지 등을 크롤링하는 기능을 제공하는 모듈.
- 크롤링 기능을 외부 라이브러리로 제공할 수 있다.
- hashtagmap-common 모듈을 이용해 커스텀 예외 객체를 사용한다.

## 기술 스택

- Jsoup

```Java
public static Document crawling(String url) {
    try {
        return Jsoup.connect(url)
          //위장할 브라우저, 디바이스 정보
            .userAgent(USER_AGENT)
          //최대 연결 시도 시간
            .timeout(HOLDING_TIME)
            .get();
    } catch (IOException e) {
        throw new CrawlingUrlException();
    }
}
```

**사용방법**
```InstagramCrawler.createHashtagDto(검색할 이름)```

```java
public CrawlingDto createHashtagDto(String placeName) {

  	...
      
    return CrawlingDto.of(placeName, hashtagCount, postDtos);
}
```

**CrawlingDto에 포함된 정보**
- 가게이름
- 해시태크 게시물 수
- 인기게시물 9개의 postUrl 과 imageUrl
