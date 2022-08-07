## TDD를 활용한 맴버십 적립 서비스

-------
이 repository 는 [망나니 개발자님의 블로그](https://mangkyu.tistory.com/182)를 보고 연습하기 위해 만들었습니다. 

* 맴버십 적립 서비스
* 맴버십의 종류는 **네이버, 카카오, 라인** 이며, 사용자는 원하는 맴버십을 등록할 수 있다.
* 포인트 적립비율은 결제금액의 1%로 고정되며, 추후 고정 금액으로 변경이 가능하도록 프로그래밍(확장성)

---

### 기능 요구 사항
* 맴버십 연결하기, 나의 맴버십 조회, 맴버십 연결끊기, 포인트 적립 API 구현
* 사용자 식별값은 문자열 형태이며 'X-USER-ID'라는 HTTP Header 로 전달되며, 이 값은 포인트 적립할 때 바코드 대신 사용됩니다.
* Content-Type 응답 형태는 'application/json' 형식입니다.
* 각 기능 및 제약사항에 대한 개발을 TDD, 단위 테스트를 기반으로 진행합니다.

---

### 상세 기술 구현 사항
* 나의 맴버십 등록 API
  * 기능 : 나의 맴버십 등록
  * 요청 : 사용자 식별값, 맴버십 이름, 포인트
  * 응답 : 맴버십 ID, 맴버십 이름

* 나의 맴버십 전체 조회 API
  * 기능 : 내가 가진 모든 맴버십을 조회
  * 요청 : 사용자 식별값
  * 응답 : {맴버십 ID, 맴버십 이름, 포인트, 가입일시}의 맴버십 리스트

* 나의 맴버십 상세 조회 API
  * 기능 : 나의 1개의 맴버십을 상세 조회
  * 요청 : 사용자 식별값, 맴버십 ID
  * 응답 : 맴버십 ID, 맴버십 이름, 포인트, 가입일시

* 나의 맴버십 삭제 API
  * 기능 : 나의 맴버십을 삭제합니다.
  * 요청 : 사용자 식별값, 맴버십 번호
  * 응답 : X

* 맴버십 포인트 적립 API
  * 기능 : 나의 맴버십 포인트를 결제 금액의 1% 만큼 적립
  * 요청 : 사용자 식별값, 맴버십 ID, 사용 금액을 입력값으로 받는다.
  * 응답 : X

---

### 기술 스팩

```yaml
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'mysql:mysql-connector-java'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```