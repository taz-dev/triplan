# 🛫 우리들의 여행계획을 책임지는 Triplan
<img src="https://user-images.githubusercontent.com/88026773/160267764-6ce97d7d-bbc3-4f6f-a4ca-f964dc77e914.jpg" height="500" width="650">

### 🔎 프로젝트 소개
#### "여행 계획을 세우는데 어려움을 겪는 사람들을 위해 쉽고 빠르게 여행 계획을 세울 수 있도록 도와주는 앱"<br>
  ✔ 지도에서 위치등록을 통해 **한눈에 동선을 파악**할 수 있어요!<br>
  ✔ **체크리스트**를 통해 여행에 필요한 물품들을 꼼꼼하게 확인할 수 있어요!<br>
  ✔ 여행에 소비한 경비와 간단한 메모작성을 통해 **여행 경비 정산**을 쉽게 할 수 있어요!<br>
  ✔ 친구와 함께 **여행일정 공유**를 할 수 있어요!<br>
  
**[TRIPLAN_VELOG](https://velog.io/@tazdev/triplan)** 👈 눌러보시면 프로젝트의 전체적인 기능과 디자인을 볼 수 있어요!

<br>

### 👨‍👨‍👦‍👦 작업 기간 & 팀원 구성
* 기간 : 2021.11 ~ 2022.03
* **TeamTriplan**
  * PM : 김환
  * FrontEnd : 최은석
  * BackEnd : 박정현
  * UI/UX 디자이너 : 김종표
<br>

### ⚙ 기술 스택
* FrontEnd : React Native, Node.js
* BackEnd : JAVA(11), SpringBoot(2.6), Spring Security, Spring Data JPA, MariaDB 
<br>

### 💾 Triplan DB diagram
![triplanDB](https://user-images.githubusercontent.com/88026773/154690467-be5a4780-09ec-4007-9419-04a124951226.PNG)
<br>
<br>
### 👩‍🔧 주요업무 및 상세 역할
![카카오 서버](https://user-images.githubusercontent.com/88026773/148561323-ce041d13-61ca-4830-8cc5-b6431eafe1a0.PNG)
* OAuth2.0을 이용한 Kakao 회원가입 및 로그인 (Google, Naver 추후 구현 예정)
* JWT(Access Token, Refresh Token)로 보안성 강화 및 사용자 편의성 고도화
* RestAPI에 대한 이해를 바탕으로 API 설계(명사를 통한 리소스 식별, 헤더에 데이터 포맷 포함)
* 간단 설정 기능, 프로필 수정 기능, 문의하기 기능
* 여행, 일정, 체크리스트 CRUD 기능 (+ 친구와 여행계획 공유할 수 있는 기능 추가)
* PaaS 기반 클라우드 플랫폼인 Heroku를 활용하여 서버 배포 진행중
<br>

### 🗂 프로젝트 구조
```j
+---main
|   +---java\be\triplan
|   |           +---TriplanApplication.java
|   |           |
|   |           +---config
|   |           |   \---security
|   |           |           CorsConfig.java
|   |           |           JwtAuthenticationFilter.java
|   |           |           JwtProvider.java
|   |           |           PrincipalDetail.java
|   |           |           PrincipalDetailService.java
|   |           |           SecurityConfig.java
|   |           |   
|   |           +---controller
|   |           |       ChecklistController.java
|   |           |       MapController.java
|   |           |       MemberController.java
|   |           |       PlanController.java
|   |           |       PlanJoinController.java
|   |           |       MemberController.java
|   |           |       QuestionController.java
|   |           |       ScheduleController.java
|   |           |       SignController.java
|   |           |
|   |           +---dto
|   |           |   +---checklist
|   |           |   |       ChecklistDto.java
|   |           |   |       ChecklistInsertRequestDto.java
|   |           |   |       ChecklistUpdateRequestDto.java
|   |           |   |
|   |           |   +---common
|   |           |   |       CommonResponse.java
|   |           |   |       CommonResult.java
|   |           |   |       ListResult.java
|   |           |   |       SingleResult.java
|   |           |   |
|   |           |   +---jwt
|   |           |   |       RefreshTokenDto.java
|   |           |   |       TokenDto.java
|   |           |   |       TokenRequestDto.java
|   |           |   |
|   |           |   +---map
|   |           |   |       MapDto.java
|   |           |   |       MapInsertRequestDto.java
|   |           |   |       MapUpdateRequestDto.java
|   |           |   |
|   |           |   +---member
|   |           |   |       MemberDto.java
|   |           |   |       MemberUpdateRequestDto.java
|   |           |   |
|   |           |   +---oauth
|   |           |   |       KakaoAccessTokenDto.java
|   |           |   |       KakaoProfile.java
|   |           |   |       KakaoRequestDto.java
|   |           |   |
|   |           |   +---plan
|   |           |   |       PlanDto.java
|   |           |   |       PlanInsertRequestDto.java
|   |           |   |       PlanUpdateRequestDto.java
|   |           |   |
|   |           |   +---planjoin
|   |           |   |       PlanJoinDto.java
|   |           |   |
|   |           |   +---question
|   |           |   |       QuestionDto.java
|   |           |   |       QuestionInsertRequestDto.java
|   |           |   |
|   |           |   \---schedule
|   |           |           ScheduleDto.java
|   |           |           ScheduleInsertRequestDto.java
|   |           |   
|   |           +---entity
|   |           |       BaseTimeEntity.java
|   |           |       Checklist.java
|   |           |       Map.java
|   |           |       Member.java
|   |           |       Plan.java
|   |           |       PlanJoin.java
|   |           |       Question.java
|   |           |       Schedule.java
|   |           |   
|   |           +---exception
|   |           |       AuthenticationEntryPointException.java
|   |           |       CommunicationException.java
|   |           |       KakaoCommunicationFailureException.java
|   |           |       KakaoLoginFailedException.java
|   |           |       NameTagAlreadyInUseException.java
|   |           |       PlanNotFoundException.java
|   |           |       RefreshTokenException.java
|   |           |       SocialAgreementException.java
|   |           |       UserExistException.java
|   |           |       UserNotFoundException.java
|   |           |   
|   |           +---repository
|   |           |       ChecklistRepository.java
|   |           |       MapRepository.java
|   |           |       MemberRepository.java
|   |           |       PlanJoinRepository.java
|   |           |       PlanRepository.java
|   |           |       QuestionRepository.java
|   |           |       ScheduleRepository.java
|   |           |
|   |           \---service
|   |               |       ChecklistService.java
|   |               |       MapService.java
|   |               |       MemberService.java
|   |               |       PlanJoinService.java
|   |               |       PlanService.java
|   |               |       QuestionService.java
|   |               |       ScheduleService.java
|   |               |       SignService.java
|   |               +---common
|   |               |       ResponseService.java
|   |               |
|   |               \---oauth
|   |                       KakaoService.java 
```
<br>

<!--
* **[TriplanFigma](https://www.figma.com/file/PrBkB1681e36HTfxL7NacJ/Trip-Planner?node-id=0%3A1)** 👈 눌러보시면 프로젝트의 전체적인 디자인을 볼 수 있어요!

![흐름_1119](https://user-images.githubusercontent.com/88026773/144074483-6344ffc6-d26b-4465-8b20-d6f3baebf541.PNG)
### 🎮 Triplan 주요 기능
#### 1. 카카오 회원가입 및 로그인
![카카오 서버](https://user-images.githubusercontent.com/88026773/148561323-ce041d13-61ca-4830-8cc5-b6431eafe1a0.PNG)
<p align="center">[그림1] 카카오 회원가입 및 로그인 동작방식</p>
#### 2. 내 여행일정 친구에게 공유하기

### 💡 프로젝트를 통해 느낀 점
* API 조회 성능을 튜닝하여 실무에서 성능을 최적화 할 수 있는 방법을 더 고민해봐야 할 것 같다.
* 복잡한 대용량 데이터를 다룰 때 성능을 최적화하고, 동시에 유지보수하기 쉬운 애플리케이션이 무엇인가 고민
* JPA를 사용할 때 동적 쿼리와 복잡한 쿼리 문제를 해결하기 위해 Querydsl에 대해 공부 필요할듯
* 실무에서 사용하는 AWS 기반의 프로젝트의 배포 환경 및 인프라 구축하는법 공부
--!>
