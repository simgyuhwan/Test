# Board
React + Spring 를 TDD를 사용하여 구현할 Repository 입니다. Back 만 TDD를 사용할 예정이며 
React는 프레임워크 연습용으로 사용할 것입니다.
구현중 이슈나 새로 알게된 점을 기록할 예정입니다.

---

### 요구 사항

1. 회원 가입 페이지
    * 회원가입 버튼 클릭하기
    * 이메일, 닉네임, 비밀번호를 입력받는다.
    * 닉네임은 최소 2글자 이상, 특수문자는 사용할 수 없다.
    * 비밀번호는 최소 6자 이상, 문자와 특수문자가 1개 이상 포함되어야 한다.
    * 비밀번호 확인과 비밀번호는 일치해야 한다.
    * 닉네임은 중복이 허용되지 않는다.
    * 회원가입시 잘못된 값은 모두 하나의 에러메시지로 통일한다.

2. 로그인 페이지
   * 이메일, 비밀번호 입력
   * 잘못된 값을 입력받는다면 '아이디 또는 비밀번호를 확인해주세요.' 메시지를 출력해준다.
   * 로그인, 회원가입, 비밀번호 찾기 버튼

3. 비밀번호 찾기
    * 이메일 주소를 입력받고 인증코드를 이메일에 전송해준다.
    * 이메일 전송이 완료되면 인증코드를 입력받을 창을 띄운다.
    * 인증코드 인증은 최대 5번까지
    * 인증이 완료되면 비밀번호를 변경할 수 있다.

4. 소셜 로그인 
   * 카카오 소셜 로그인 기능

5. 게시글
   * USER, ADMIN 사용자만 게시글을 생성할 수 있다.
   * GUEST 사용자가 게시글 생성 요청시 로그인 권한을 요청한다.
   * 자신이 작성한 게시글만 수정 및 삭제가 가능하다.
   * ADMIN은 모든 게시글의 수정 및 삭제가 가능하다.
   * 5 페이지까지 화면에 출력되고 누르면 페이지 위치로 이동된다.
   * 게시글 이름, 작성자명으로 게시글 조회가 가능하다.

6. 댓글
    * 게시글 하단에는 댓글이 표시된다.
    * GUEST의 경우, 댓글 입력창을 입력할 수 없다.
    * USER, ADMIN 사용자(로그인)는 댓글 입력창을 입력할 수 있다.
    * GUEST가 입력 버튼을 누를 경우 '로그인이 필요합니다' 라는 메시지를 띄운다.
    * 댓글 내용이 없는 상태에서 작성 버튼을 누르면 '내용을 입력해주세요.' 라는 메시지를 띄운다.
    * 댓글 내용을 입력 후 작성 버튼을 누르면 작성한 댓글이 추가된다.
    * 자신이 작성한 댓글만 수정 및 삭제가 가능하다.(자신이 작성한 것만 수정, 삭제 버튼이 나온다.)
    * 수정버튼을 누르면 기존 입력 내용이 기본 값으로 나오게 한다.
    * 저장 버튼을 누르면 수정이 된다.
    * 삭제 버튼 클릭시 삭제 확인 메시지를 띄운 후 확인을 눌러야 삭제가 되게 한다.
   
---

## Back

### 1. 기술 스팩
```
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.flywaydb:flyway-core:9.0.4'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'mysql:mysql-connector-java'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation group: 'com.h2database', name: 'h2', version: '1.4.197'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
```
---
### 2. API


---

## Front

