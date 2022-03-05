# 🛫 우리들의 여행계획을 책임지는 Triplan

### 🔎 프로젝트 소개
* 여행 계획을 세우는데 어려움을 겪는 사람들을 위해 쉽고 빠르게 여행 계획을 세울 수 있도록 도와주는 앱
* **[TriplanFigma](https://www.figma.com/file/PrBkB1681e36HTfxL7NacJ/Trip-Planner?node-id=0%3A1)** 👈 눌러보시면 프로젝트의 전체적인 디자인을 볼 수 있어요!
<br>

### 👨‍👨‍👦‍👦 프로젝트 기간 & 팀원
* 기간 : 2021.11 ~ 2022.03
* **TeamTriplan**
  * PM : 김환
  * FrontEnd : 최은석
  * BackEnd : 박정현
  * UI/UX 디자이너 : 김종표
<br>

### ⚙ 기술 스택
* FrontEnd : React Native, Node.js
* BackEnd : JAVA(11), SpringBoot, Spring Security, Spring Data JPA, MariaDB 
<br>

### 💾 Triplan DB diagram
![triplanDB](https://user-images.githubusercontent.com/88026773/154690467-be5a4780-09ec-4007-9419-04a124951226.PNG)
<br>
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

### 🎮 Triplan 주요 기능
#### 1. 카카오 회원가입 및 로그인
![카카오 서버](https://user-images.githubusercontent.com/88026773/148561323-ce041d13-61ca-4830-8cc5-b6431eafe1a0.PNG)
<p align="center">[그림1] 카카오 회원가입 및 로그인 동작방식</p>

#### 2. 내 여행일정 친구에게 공유하기

### 👩‍🔧 프로젝트에서 맡은 역할
* OAuth2.0을 이용한 KAKAO 회원가입 및 로그인 (GOOGLE, NAVER 추후 구현)
* JWT(Access Token, Refresh Token)로 보안성 강화 및 사용자 편의성 고도화
* 친구와 여행 계획 공유하기 
* <br>
<!--
## 💡 프로젝트를 통해 배운점

![흐름_1119](https://user-images.githubusercontent.com/88026773/144074483-6344ffc6-d26b-4465-8b20-d6f3baebf541.PNG)
--!>
