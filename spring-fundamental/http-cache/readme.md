---\
Http Cache(Cache-Control + Etag)

1. cache는 controller 등에 너무 많이 http request 요청 오면 부하 걸리니까, 부하 분산하기 위해 사용.
2. 정적파일들은 부하걸리니까 서버에 요청하지 말고, 클라이언트 단 브라우저에 저장해 놓고 두고두고 써.
3. CacheControl.maxAge(Duration.ofDays(365)); 해 놓을 테니까 1년동안 두고두고 써.
4. 어? 근데 서버에서 내가 캐시한 정적 파일이 바뀌었을 수도 있잖아? 그걸 어떻게 알지? -> 이래서 etag를 쓰는 것.
5. etag는 리소스의 해쉬값. 리소스의 내용이 바뀌면 해쉬값이 달라진다.
6. 맨 처음에 서버->클라이언트에 static js file 보낼 떄, cache-max-age=60초랑 etag='aaaa' 보낸다.
7. 60초 후에, client-> 서버로 다시 static js file 요청 들어오면, 서버의 static파일에 etag와 클라이언트의 etag값을 비교해서,(.header(HttpHeaders.IF_NONE_MATCH, etag) 여기에서 비교) 값이 같다면, 안바뀌었다는거니까 쓰던거 써!, HTTP status는 304를 반환.(there is no need to 're-transmit' the requested resources)
8. etag의 값이 다르다면, static js file이 바뀌었다는 말이니까, 새 static file을 새로운 etag + cache setting과 함께 클라이언트로 반환
9. spring에서는 etag와 cache-control을 사용하여 정적파일을 캐시로 반환함.
10. 근데 cache가 만료될 때만, etag를 비교하는데, 캐시 보관일 1년 잡아서 만료 되기 전에 서버쪽 캐시 데이터가 바뀐다면 어떡할건데? 
11. 리소스 url을 변경하는 식으로 해결. style.css가 아니라, style.3da37df.css처럼, 버전 번호를 파일 이름에 추가하는 것. 그래서 파일이 바뀌면 요청 url이 아예 바뀌도록. 그걸 cache-bursting이라고 한다.
12. 이번 예제의 경우, 서버를 껐다 키면, static 파일에 변경이 있다고 가정해서, 서버를 켜 ResourceVersion.java에 빈이 만들어진 시간(초 단위)을 static file uri path에 넣는 식으로 cache-bursting 처리를 함.


---\
코드 구조


1. etag
   1. 특정 uri에 etag를 부여하는건, EtagFilterConfiguration에서 한다.
   2. etag를 비교해서 같으면 http status code:304를 반환하는건, 내부적으로 일어나는 듯 하다.
2. cache
   1. 모든 incoming uri에 대해 CacheControl.noCache().cachePrivate() 세팅은 CacheWebConfig에서 한다
   2. cache-bursting
      1. <script src="/resources/20220912192024/js/index.js"></script> 에서, 
      2. 날짜(초단위) 생성은 ResourceVersion에서 서버 시작 후 빈 생성시 하고,
      3. 저 static file uri에 1년 duration먹이고 public세팅은 CacheBurstingWebConfig에서 한다.
      4. html handlebar에서 저 uri로 요청하는건, VersionHandlebarsHelper에서 한다.

