# Board

React + Spring 를 TDD를 사용하여 구현할 Repository 입니다. Back 만 TDD를 사용할 예정이며
React는 프레임워크 연습용으로 사용할 것입니다.
구현중 이슈나 새로 알게된 점을 기록할 예정입니다.

---

### 요구 사항

1. 회원(Member)

   - 회원 가입

     - 이메일, 닉네임, 비밀번호를 입력받는다.
     - 닉네임은 최소 3글자이다.
     - 비밀번호는 최소 8자 이상, 문자와 특수문자가 1개 이상 포함되어야 한다.
     - 닉네임은 중복이 허용되지 않는다.
     - 회원가입시 잘못된 값은 모두 하나의 에러메시지로 통일한다.

   - 로그인

     - 이메일, 비밀번호 입력
     - 잘못된 값을 입력받는다면 'Please check your ID or password.' 메시지를 출력해준다.
     - 로그인, 회원가입, 비밀번호 찾기 버튼이 있다.
     - Github, kakao 소셜 로그인 버튼을 추가

   - 비밀번호 변경
     - 기존 비밀번호와 새 비밀번호를 입력받는다.
     - 기존 비밀번호가 일치하지 않으면 "Invalid password." 메시지를 출력해준다.

2. 게시글(Posts)
   - 게시글 관리
     - USER, ADMIN 사용자만 게시글을 생성할 수 있다.
     - GUEST 사용자가 게시글 생성 요청시 로그인 권한을 요청한다.
     - 자신이 작성한 게시글만 수정 및 삭제가 가능하다.
     - ADMIN은 모든 게시글의 수정 및 삭제가 가능하다.
     - 5 페이지까지 화면에 출력되고 누르면 페이지 위치로 이동된다.
     - 게시글 이름, 작성자명으로 게시글 조회가 가능하다.
   - 댓글
     - 게시글 하단에는 댓글이 표시된다.
     - GUEST의 경우, 댓글 입력창을 입력할 수 없다.
     - USER, ADMIN 사용자(로그인)는 댓글 입력창을 입력할 수 있다.
     - GUEST가 입력 버튼을 누를 경우 '로그인이 필요합니다' 라는 메시지를 띄운다.
     - 댓글 내용이 없는 상태에서 작성 버튼을 누르면 '내용을 입력해주세요.' 라는 메시지를 띄운다.
     - 댓글 내용을 입력 후 작성 버튼을 누르면 작성한 댓글이 추가된다.
     - 자신이 작성한 댓글만 수정 및 삭제가 가능하다.(자신이 작성한 것만 수정, 삭제 버튼이 나온다.)
     - 수정버튼을 누르면 기존 입력 내용이 기본 값으로 나오게 한다.
     - 저장 버튼을 누르면 수정이 된다.
     - 삭제 버튼 클릭시 삭제 확인 메시지를 띄운 후 확인을 눌러야 삭제가 되게 한다.

---

## Back

### &nbsp; 1. 기술 스팩

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	// flyway
	implementation 'org.flywaydb:flyway-core:9.0.4'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'mysql:mysql-connector-java'

	// h2
	testImplementation group: 'com.h2database', name: 'h2', version: '1.4.197'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.hamcrest:hamcrest:2.2'

	// lombok
	implementation 'org.projectlombok:lombok:1.18.24'
	annotationProcessor 'org.projectlombok:lombok:1.18.24'

	// mapstruct
	implementation 'org.mapstruct:mapstruct:1.5.2.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.2.Final'
	annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0' // v1.18.16+ 부터

	// querydsl
	implementation group: 'com.querydsl', name: 'querydsl-jpa', version: '5.0.0'
	implementation group: 'com.querydsl', name: 'querydsl-apt', version: '5.0.0'

	// gson
	implementation group: 'com.google.code.gson', name: 'gson', version: '2.9.0'

	// validation
	implementation group: 'org.springframework.boot', name: 'spring-boot-starter-validation', version: '2.7.0'
}
```

---

### &nbsp; 2. API

#### &nbsp;&nbsp; Member (/api/v1/members)

- 회원 등록
  - 기능 : 회원 등록
  - 요청 : 이메일, 닉네임, 비밀번호
  - 응답 : X

---

## Front

---

## Issue

1. Response 전용 객체 생성
   원하는 형태로 Response를 주기위해 따로 클래스 생성. 성공시 원하는 값을 T 타입으로 넣어서 전달할 수 있으며 에러 발생시, 메시지만 전송하도록 구현

   ```java
   @Getter
   @AllArgsConstructor(access = AccessLevel.PRIVATE)
   public class Response {

       private boolean success;
       private Result result;

       public static Response success(){
           return new Response(true, null);
       }

       public static <T> Response success(T data) {
           return new Response(true, new Success<T>(data));
       }

       public static Response failure(String message) {
           return new Response(false, new Failure(message));
       }

       public static <T> Response failure(String message, T data){
           return new Response(false, new Failure<T>(message,data));
       }
   }
   ```

2. 디자인 패턴 적용

   - **스테이트 패턴(State Pattern)**

     스테이트 패턴을 응용하여 실패 상태에 따라 다른 에러 메시지를 전달하도록 한다.

     ```java
     @Getter
     @RequiredArgsConstructor
     public enum MemberErrorResult{
         DUPLICATED_MEMBER_REGISTER(HttpStatus.BAD_REQUEST, "Duplicate Members Information."),
         INCORRECT_REGISTRATION_INFORMATION(HttpStatus.BAD_REQUEST, "Incorrect registration information"),
         ;

         private final HttpStatus httpStatus;
         private final String message;
     }
     ```

   - **정적 팩터리 메서드 패턴(Static Factory Method)**

     MemberException 의 내부 생성자는 모두 막아놓고 오로지 지정된 메서드로만 생성하도록 허용(불변)

     ```java
     @Getter
     public class MemberException extends RuntimeException{
         private MemberErrorResult memberErrorResult;

         private MemberException(MemberErrorResult memberErrorResult) {
             this.memberErrorResult = memberErrorResult;
         }

         private MemberException(){}

         public static MemberException of(final MemberErrorResult memberErrorResult){
             return new MemberException(memberErrorResult);
         }
     }
     ```
