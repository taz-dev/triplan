# π« μ°λ¦¬λ€μ μ¬νκ³νμ μ±μμ§λ Triplan
<img src="https://user-images.githubusercontent.com/88026773/160267764-6ce97d7d-bbc3-4f6f-a4ca-f964dc77e914.jpg" height="500" width="650">

### π νλ‘μ νΈ μκ°
#### "μ¬ν κ³νμ μΈμ°λλ° μ΄λ €μμ κ²ͺλ μ¬λλ€μ μν΄ μ½κ³  λΉ λ₯΄κ² μ¬ν κ³νμ μΈμΈ μ μλλ‘ λμμ£Όλ μ±"<br>
  β μ§λμμ μμΉλ±λ‘μ ν΅ν΄ **νλμ λμ μ νμ**ν  μ μμ΄μ!<br>
  β **μ²΄ν¬λ¦¬μ€νΈ**λ₯Ό ν΅ν΄ μ¬νμ νμν λ¬Όνλ€μ κΌΌκΌΌνκ² νμΈν  μ μμ΄μ!<br>
  β μ¬νμ μλΉν κ²½λΉμ κ°λ¨ν λ©λͺ¨μμ±μ ν΅ν΄ **μ¬ν κ²½λΉ μ μ°**μ μ½κ² ν  μ μμ΄μ!<br>
  β μΉκ΅¬μ ν¨κ» **μ¬νμΌμ  κ³΅μ **λ₯Ό ν  μ μμ΄μ!<br>
  
**[TRIPLAN_VELOG](https://velog.io/@tazdev/triplan)** π λλ¬λ³΄μλ©΄ νλ‘μ νΈμ μ μ²΄μ μΈ κΈ°λ₯κ³Ό λμμΈμ λ³Ό μ μμ΄μ!

<br>

### π¨βπ¨βπ¦βπ¦ μμ κΈ°κ° & νμ κ΅¬μ±
* κΈ°κ° : 2021.11 ~ 2022.03
* **TeamTriplan**
  * PM : κΉν
  * FrontEnd : μ΅μμ
  * BackEnd : λ°μ ν
  * UI/UX λμμ΄λ : κΉμ’ν
<br>

### β κΈ°μ  μ€ν
* FrontEnd : React Native, Node.js
* BackEnd : JAVA(11), SpringBoot(2.6), Spring Security, Spring Data JPA, MariaDB 
<br>

### πΎ Triplan DB diagram
![triplanDB](https://user-images.githubusercontent.com/88026773/154690467-be5a4780-09ec-4007-9419-04a124951226.PNG)
<br>
<br>
### π©βπ§ μ£Όμμλ¬΄ λ° μμΈ μ­ν 
![μΉ΄μΉ΄μ€ μλ²](https://user-images.githubusercontent.com/88026773/148561323-ce041d13-61ca-4830-8cc5-b6431eafe1a0.PNG)
* OAuth2.0μ μ΄μ©ν Kakao νμκ°μ λ° λ‘κ·ΈμΈ (Google, Naver μΆν κ΅¬ν μμ )
* JWT(Access Token, Refresh Token)λ‘ λ³΄μμ± κ°ν λ° μ¬μ©μ νΈμμ± κ³ λν
* RestAPIμ λν μ΄ν΄λ₯Ό λ°νμΌλ‘ API μ€κ³(λͺμ¬λ₯Ό ν΅ν λ¦¬μμ€ μλ³, ν€λμ λ°μ΄ν° ν¬λ§· ν¬ν¨)
* κ°λ¨ μ€μ  κΈ°λ₯, νλ‘ν μμ  κΈ°λ₯, λ¬ΈμνκΈ° κΈ°λ₯
* μ¬ν, μΌμ , μ²΄ν¬λ¦¬μ€νΈ CRUD κΈ°λ₯ (+ μΉκ΅¬μ μ¬νκ³ν κ³΅μ ν  μ μλ κΈ°λ₯ μΆκ°)
* PaaS κΈ°λ° ν΄λΌμ°λ νλ«νΌμΈ Herokuλ₯Ό νμ©νμ¬ μλ² λ°°ν¬ μ§νμ€
<br>

### π νλ‘μ νΈ κ΅¬μ‘°
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
* **[TriplanFigma](https://www.figma.com/file/PrBkB1681e36HTfxL7NacJ/Trip-Planner?node-id=0%3A1)** π λλ¬λ³΄μλ©΄ νλ‘μ νΈμ μ μ²΄μ μΈ λμμΈμ λ³Ό μ μμ΄μ!

![νλ¦_1119](https://user-images.githubusercontent.com/88026773/144074483-6344ffc6-d26b-4465-8b20-d6f3baebf541.PNG)
### π? Triplan μ£Όμ κΈ°λ₯
#### 1. μΉ΄μΉ΄μ€ νμκ°μ λ° λ‘κ·ΈμΈ
![μΉ΄μΉ΄μ€ μλ²](https://user-images.githubusercontent.com/88026773/148561323-ce041d13-61ca-4830-8cc5-b6431eafe1a0.PNG)
<p align="center">[κ·Έλ¦Ό1] μΉ΄μΉ΄μ€ νμκ°μ λ° λ‘κ·ΈμΈ λμλ°©μ</p>
#### 2. λ΄ μ¬νμΌμ  μΉκ΅¬μκ² κ³΅μ νκΈ°

### π‘ νλ‘μ νΈλ₯Ό ν΅ν΄ λλ μ 
* API μ‘°ν μ±λ₯μ νλνμ¬ μ€λ¬΄μμ μ±λ₯μ μ΅μ ν ν  μ μλ λ°©λ²μ λ κ³ λ―Όν΄λ΄μΌ ν  κ² κ°λ€.
* λ³΅μ‘ν λμ©λ λ°μ΄ν°λ₯Ό λ€λ£° λ μ±λ₯μ μ΅μ ννκ³ , λμμ μ μ§λ³΄μνκΈ° μ¬μ΄ μ νλ¦¬μΌμ΄μμ΄ λ¬΄μμΈκ° κ³ λ―Ό
* JPAλ₯Ό μ¬μ©ν  λ λμ  μΏΌλ¦¬μ λ³΅μ‘ν μΏΌλ¦¬ λ¬Έμ λ₯Ό ν΄κ²°νκΈ° μν΄ Querydslμ λν΄ κ³΅λΆ νμν λ―
* μ€λ¬΄μμ μ¬μ©νλ AWS κΈ°λ°μ νλ‘μ νΈμ λ°°ν¬ νκ²½ λ° μΈνλΌ κ΅¬μΆνλλ² κ³΅λΆ
--!>
