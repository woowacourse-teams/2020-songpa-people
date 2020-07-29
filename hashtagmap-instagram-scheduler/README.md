# 시나리오

- db에서 전체 place를 받아온다
- place이름 기준으로 인스타크롤링을 한다. => 이때 프록시를 바꿔주면서 한다.
- 크롤링한 데이터로 instagram과 instagramPost를 만든다.
- Instagram과 instagramPost를 db에 저장해준다.



#  기능 목록 및 메서드

- [ ] 가게목록 받아오기(repository 이용 output:List<Place>)
- [ ] Proxy하나와 Place하나를 주면 CrawlingDto를 생성하는 메서드
- [ ] crawlingDto => instagram으로 가공
- [ ] crawlingDto => instagramPost로 가공
- [ ] List<Place>,Proxies를 주면 ~~ List<crawlingDto> 을 반환
- [ ] List<crawlingDto>을 db에 저장하는 기능