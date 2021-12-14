# RAINDROP_W

RAINDROP WEB VERSION

> MacOS(m1)
> Java8
> IntelliJ
> SpringBoot(spring security, JPA, MyBatis, ajax, jQuery, lombok, Thymeleaf)
> MySQL(workbench)
> git(github)

raindrop web version code refactorying ..
*************************** plan ***************************************

*** WEB ***

*** MEMBER ***

SERVICE FLOW
  - 포인트는 코인으로 교환 ( RAINDROP COIN - R coin ) N
  - 모의투자에서 당첨된 금액의 1% 포인트 적립 N
  - 1000 point -> 1 coin N
  - ??????????????????????????????????????고민???
  - 1등 당첨 -> 졸업 N
  - 매일 로그인시 0.5coin 증정 ( 고민 후 결정 ) N
  - 후기 및 투자게시판 좋아요 10개 이상 게시글 3개 작성시 1coin 증정 ( 고민 후 결정 ) N

MYPAGE - (회원정보 관리)
  - 포인트 및 코인 관리 내역 N
  - 개인정보 확인 및 수정 N
  - 흠;

RAINDROP - (실제투자)
  - 다가오는 회차만 받을 수 있음(최대 10회) N
  - 발표 후 바로 확인 가능 ( 등수 당첨금 회차 등 ) N
  - 모의투자로 적립된 포인트로 사용 ( 없으면 캐시충전 ) N
  - 모든 알고리즘 사용 가능 N
  - 구입 날짜 및 시간 알람기능( APP, GoogleMail 활용 - 중요 ) N
  - 번호는 이메일 및 문자(추후 추가)로 받기 가능 N
  - 지난 번호 및 등수, 당첨금 확인 페이지 추가 N ??
  - 번호보기 전 비밀번호 확인 N ??
  - 

DROPTOP - (모의투자)
  - 모든 회차 선택 후 모의 투자(회차당 50만원치) Y
  - 추첨받은 회차별 결과 조회 Y
  - 지난 번호 및 등수, 당첨금 확인 페이지 추가 N
  - 모의 투자 돌린 회차 -> 클릭시 번호 -> 클릭시 당첨 결과


*** ADMIN ***

(관리기능)
  - 저장안된 회차 저장 Y
  - 모의투자 기능 똑같이(테스트용) N ??

(회원관리)
  - 회원 리스트 및 삭제기능 N
  - 회원 모의투자 및 실제 투자 현황
  - 회원 코인 추가 기능


*** DATABASE ***

 ** member **
  -> RAINDROP 회원테이블
  - id 회원 고유 번호
  - email 로그인 아이디 및 번호 전송
  - name 회원 이름
  - password 회원 비밀번호
  - phone 회원 전화번호 및 번호 전송
  - nick 회원 닉네임(본인설정)
  - point 모의투자 당첨금으로 쌓이는 포인트
  - coin 번호 받을 수 있는 코인
  - type
  - role 권한
  - status_1 1등 당첨 유무
  - in_date 가입 일
  - up_date 정보 수정 일

 ** point_drop / cash_drop **
  -> 부여받은 조합 저장 테이블
  - id 테이블 고유번호
  - member_id 회원 고유번호
  - round 회차
  - round_id 라운드별 횟수
  - ranking 당첨 등수
  - num1 ~ 6 부여받은 번호
  - sum 1 ~ 6 더한 수
  - in_date 저장 날짜
  - up_date 수정 날짜

 ** prize_list **
  -> 회차별 당첨번호 저장 테이블
  - id 회차별 당첨번호 고유번호
  - event_num ?
  - num1 ~ 7 당첨번호
  - sum 1 ~ 7 합
  - multi 1 ~ 7 곱
  - in_date 저장 날짜

 ** point_rain / cash_rain **
  -> 회원별 당첨 결과 및 당첨금 테이블
  - id 회차별 결과 고유번호
  - member_id 회차별 결과 회원 고유번호
  - round 회차
  - rank01 ~ 05 1 ~ 5등 횟수
  - game_total 총 게임수
  - revenue_total 총 당첨금
  - revenue_after_tax 세금제외 받는 금액
  - in_date 저장 날짜
  - up_date 수정 날짜

아이디어 생각

모의투자 네이밍 droptop
실제번호 네이밍 raindrop

디비에서
번호 리스트 네이밍 _list
당첨결과 네이밍 _result

*** APP ***

 ** 회원 **
  - 번호받기 및 알람용으로 활용 N

*************************** plan ***************************
