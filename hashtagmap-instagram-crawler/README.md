# Instagram Crawler

## 사용 기술

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



## 사용방법

### InstagramCrawler.createHashtagDto(검색할 이름)

```java
public CrawlingDto createHashtagDto(String placeName) {

  	...
      
    return CrawlingDto.of(placeName, hashtagCount, postDtos);
}
```



CrawlingDto에 포함된 정보

- 가게이름
- 해시태크 게시물 수
- 인기게시물 9개의 postUrl 과 imageUrl

