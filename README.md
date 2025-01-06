# testproject

```
프로젝트 구성

Framework : Spring boot 3.1.5, bootstrap 5.0
Language : Java 17
Database : MariaDB 10.9.2
Server : localhost
Tool : Visual Studio Code
```

### 기능
1. 구글지도 API 활용해서 좌표추출
2. 구글 로그인 기능
3. 네이버 로그인 기능 (네이버로 부터 검수필요)
4. 카카오 로그인 기능 (카카오로 부터 검수필요)
5. 네이버 및 카카오지도를 활용한 데이터 클러스터링 기능 
6. 공공데이터를 활용한 초,중,고 학교 위치 및 학교 정보 확인 기능
7. 이메일 인증기능
- 회원가입시 이메일 인증을 하는데 이메일로 온 인증코드를 입력하면 회원가입이 완료 됩니다.
8. CKEditor 5 를 포함한 게시판 기능
- 게시판 CRUD기능에는 Spring Data JPA를 사용했습니다.
9. 네이버지도 direction 5 기능

(sns로그인 기능은 리펙토링이 필요하다고 판단해 리펙토링 작업중 입니다.)

### 참고자료
* 로그인 화면 구현 : <https://antdev.tistory.com/70?category=919963>
* 유저정보 불러오기 : <https://notspoon.tistory.com/47>
* 참고한 git : <https://github.com/vvsungho/social-login-server.git>
