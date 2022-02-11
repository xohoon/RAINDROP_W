# RAINDROP_W

RAINDROP WEB VERSION

- MacOS(m1)
- Java8
- IntelliJ
- SpringBoot(spring security, JPA, MyBatis, ajax, jQuery, lombok, Thymeleaf)
- MySQL(workbench)
- git(github)

raindrop web version code refactorying ..

*************************** plan ***************************************

*** MEMBER ***

SERVICE FLOW
  - 포인트는 코인으로 교환 ( RAINDROP COIN - R coin ) Y
  - 모의투자에서 당첨된 금액의 1% 포인트 적립 Y
  - 1000 point -> 1 coin Y
  - ??????????????????????????????????????고민???
  - 1등 당첨 -> 졸업 N
  - 매일 로그인시 0.5coin 증정 ( 고민 후 결정 ) N

MYPAGE - (회원정보 관리)
  - 포인트 및 코인 관리 내역 Y
  - 개인정보 확인 및 수정 Y
  - 포인트 > 코인 환전기능 Y

RAINDROP - (실제투자)
  - 결과 확인 리스트 > 회차 선택 > 상세 번호 및 결과(등수, 금액) 조회 페이지로 이동 Y
  - 다가오는 회차만 받을 수 있음(최대 10회) Y
  - 발표 후 바로 확인 가능 ( 등수 당첨금 회차 등 ) Y
  - 모의투자로 적립된 포인트로 사용 ( 없으면 캐시충전 ) Y
  - 구입 날짜 및 시간 알람기능( APP, GoogleMail 활용 - 중요 ) N
  - 번호는 이메일 및 문자(추후 추가)로 받기 가능 N

DROPTOP - (모의투자)
  - 모든 회차 선택 후 모의 투자(회차당 50만원치) Y
  - 추첨받은 회차별 결과 조회 Y
  - 결과 상세페이지 > 등수 금액 등 Y

*** 기존 실시간 반영 -> 페이지 이동 및 새로고침으로 변경(한 페이지에서 기능이 많아져서 데이터의 복작함)

*** ADMIN ***

(관리기능)
  - 저장안된 회차 저장 Y

(회원관리)
  - 회원 리스트 및 삭제기능 Y
  - 회원 모의투자 및 실제 투자 현황 N
  - 회원 코인 추가 기능 Y


*************************** plan ***************************
