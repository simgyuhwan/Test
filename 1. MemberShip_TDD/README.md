## TDD 를 활용한 맴버십 적립 서비스

-------
이 repository 는 [망나니 개발자님의 블로그](https://mangkyu.tistory.com/182)를 보고 연습하기 위해 만들었습니다. 

* 맴버십 적립 서비스
* 맴버십의 종류는 **네이버, 카카오, 라인** 이며, 사용자는 원하는 맴버십을 등록할 수 있다.
* 포인트 적립비율은 결제금액의 1%로 고정되며, 추후 고정 금액으로 변경이 가능하도록 프로그래밍(확장성)

---

### 기능 요구 사항
* 맴버십 연결하기, 나의 맴버십 조회, 맴버십 연결끊기, 포인트 적립 API 구현
* 사용자 식별값은 문자열 형태이며 'X-USER-ID' 라는 HTTP Header 로 전달되며, 이 값은 포인트 적립할 때 바코드 대신 사용됩니다.
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

## 기술 스팩

```
dependencies {
  testImplementation group: 'com.h2database', name: 'h2', version: '1.3.176'
  implementation( group: 'com.google.code.gson', name: 'gson', version: '2.8.7')
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

---

## 예시(맴버십 등록)

### 1. Repository

#### 맴버십 등록 API 구현
  처음은 맴버십 등록에 대한 테스트 코드를 작성한다.
  ```java
    @Test
    public void 맴버십등록(){
        // given
        final MemberShip memberShip = MemberShip.builder()
                .userId("userId")
                .memberShipName("네이버")
                .point(10000)
                .build();

        // when
        final MemberShip result = memberShopRepository.save(memberShip);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getPoint()).isEqualTo(10000);
        assertThat(result.getMemberShipName()).isEqualTo("네이버");
    }
  ```
  * MemberShip 엔티티 안에는 Id 필드밖에 없기 때문에 대부분이 에러가 생긴다. 하나씩 에러를 없애보자.
  * builder() 를 사용하기 위해 ```@Builder``` 를 MemberShip에 추가한다.
  * 내부 필드값으로는 맴버십 등록 요구사항을 분석하면 **_'사용자 식별값, 맴버십 이름, 포인트'_** 가 요청에 사용한 다는 것을 알 수 있다. 그러니 userId, memberShopName, point 를 내부 필드값으로 할당한다. 
  * 테스트 코드를 실행하면 실패한다. 이제 위에 작성한 바를 엔티티에 적용하자.

  ```java
  @Table
  @Getter
  @Builder
  @AllArgsConstructor
  @Entity
  @NoArgsConstructor
  public class MemberShip {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
  
      @Column(nullable = false, length = 20)
      private String memberShipName;
  
      @Column(nullable = false)
      private String userId;
  
      @Column(nullable = false)
      @ColumnDefault("0")
      private Integer point;
  
      @CreationTimestamp
      @Column(nullable = false, length = 20, updatable = false)
      private LocalDateTime createdAt;
  
      @UpdateTimestamp
      @Column(length = 20)
      private LocalDateTime updatedAt;
  }
  ```
에러는 사라지고 테스트에 통과한 것을 볼 수 있다.

---

### 리팩터링
테스트에 통과하면 다음으로 할 것은 리팩터링이다. 중복을 제거하고 좀더 깔끔한 코드로 만든다. 
테스트 코드를 살펴보자. MemberShip을 만드는 Builder 패턴이 적용된 중간 부분이다.

`.memberShipName("네이버")` 요구사항의 맴버십 종류로는 네이버, 카카오, 라인이다. 이를 단순히 String 으로 처리하면 실수할 여지가 있다.
그러니 이 부분을 Enum 타입으로 바꿔주면 관리와 가독성면에서 더 좋을 듯 싶다. TDD 기반이니 먼저 테스트 코드에 Enum 타입으로 가정하고 작성한다.

```java
  final MemberShip memberShip = MemberShip.builder()
          .userId("userId")
          .memberShipName(MemberShipType.NAVER)
          .point(10000)
          .build();
```
MemberShipType 이 없기 때문에 테스트는 실패한다. 이를 만들어주자. 

```java
  @RequiredArgsConstructor
  public enum MemberShipType {
    NAVER("네이버"),
    LINE("라인"),
    KAKAO("카카오");
  
    final private String companyName;
  }
```
이제 이를 내부 필드로 가지는 MemberShip 과 Test 코드도 이에 맞게 변경한다. 

**MemberShip**

```java
@Enumerated(EnumType.STRING)    
private MemberShipType memberShipName;
```
**Test**
```java
final MemberShip memberShip = MemberShip.builder()
        .userId("userId")
        // 변경 부분
        .memberShipName(MemberShipType.NAVER)
        .point(10000)
        .build();

assertThat(result.getMemberShipType()).isEqualTo(MemberShipType.NAVER);
```

테스트는 성공한다.


#### 2. 맴버십 등록 중복 확인

만약 사용자가 맴버십을 등록한 뒤, 동일한 맴버십을 등록하면 이를 검사하여 **중복 등록을 하지 못하게 하는 로직**이 필요하다.
맴버십이 등록됐는지 확인하기 위해 맴버십을 조회하는 테스트 코드를 작성하자.
```java
    @Test
    public void 맴버십이존재하는지테스트(){
        // given
        final MemberShip memberShip = MemberShip.builder()
                .userId("userId")
                .memberShipType(MemberShipType.NAVER)
                .point(10000)
                .build();
        // when
        memberShopRepository.save(memberShip);
        final MemberShip findMemberShip = memberShopRepository.findByUserIdAndMemberShipType("userId", MemberShipType.NAVER);
        
        // then
        assertThat(findMemberShip).isNotNull();
        assertThat(findMemberShip.getId()).isNotNull();
        assertThat(findMemberShip.getUserId()).isEqualTo("userId");
        assertThat(findMemberShip.getMemberShipType()).isEqualTo(MemberShipType.NAVER);
        assertThat(findMemberShip.getPoint()).isEqualTo(10000);
    }
```
당연히 repository 내에 **findByUserIdAndMemberShipType** 메서드가 없기 때문에 테스트는 실패한다. 
repository 에 해당 메서드를 추가하자.

```java
  public interface MemberShopRepository extends JpaRepository<MemberShip, Long> {
  
      MemberShip findByUserIdAndMemberShipType(final String userId, final MemberShipType memberShipType);
  }
```
다시 테스트를 실행해보면 **초록불**이 나오는 것을 볼 수 있다.

---

### 2. Service

서비스 계층을 개발할 차례이다. 서비스 계층은 Repository 계층을 의존하기 때문에 이를 모킹하기 위해 MockitoExtension 에서 실행되도록 한다.
먼저 **MembershipServiceTest** 를 만들자. 

```java
@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {
    
}
```

그리고 테스트를 추가하는데. 가장 먼저 **_실패하는 테스트_** 를 추가한다. 실패하는 테스트가 뭐가 있을까? 맴버십을 추가했는데. 동일한 맴버십을 등록했을 때를 생각할 수 있다.

```java
@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {
    
  // 임시용
  private final String userId = 'userId';
  private final MembershipType membershipType = MembershipType.NAVER;
  private final Integer point = 10000;

  // 1. 실패 하는 테스트
  @Test
  public void 맴버십등록_이미존재함() {
    //given
    doReturn(Membership.builder().build()).when(memberShipRepository).findByUserIdAndMemberShipType(userId, memberShipType);

    //when
    final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, memberShipType, point));

    //then
    assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
  }
}
```
바로 컴파일 에러가 날 것이다. `MembershipException, target.addMembership, MembershipErrorResult` 모두 만들지 않았기 때문이다. 
클래스를 만들기 전에 테스트 코드만 하나씩 살펴보자. 

### given
`doReturn(Membership.builder().build()).when(memberShipRepository).findByUserIdAndMemberShipType(userId, memberShipType);`: 
바로 해석이 가능할 정도다. membershipRepository의 findByUserIdAndMemberShipType 메서드를 사용하면 일반 생성자를 사용한 Membership을 반환해달라는 의미다. 
**이번 테스트에 사용할 Repository 메서드를 정의해 놓는 것**이다. 

### when
`final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, memberShipType, point));` :
target 은 이 테스트에 타겟인 membershipService를 의미한다. membershipService의 addMembership 을 싱행할 때 MembershipException이 발생하고 그 값을 result 에 담는다.

### then
`assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);`:
에러 내부 필드 값이 'DUPLICATED_MEMBERSHIP_REGISTER' 가 맞는지 검증한다. 

이제 컴파일 에러를 해결하기 위해 클래스를 생성하자. 

```java
@Getter
@RequiredArgsConstructor
public enum MembershipErrorResult {
    DUPLICATED_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Membership Register Request"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
```

```java
@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException {

    private final MembershipErrorResult errorResult;

}
```
```java
@Service
public class MembershipService {

    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        return null;
    }
}
```

**MembershipErrorResult** 클래스 내부에 httpStatus 와 message를 반환하는 이유는 **RestControllerAdvice**를 통해서 이를 반환해주기 위해서이다.
RuntimeError와 이를 상속받는 클래스는 언체크 예외이다. 언체크 예외란 복구할 수 없는 에러를 의미한다. 
_**트랜잭션에서 언체크 예외만 자동으로 롤백이 된다**_. 체크 예외는 롤백이 되지 않는다. (설정으로 가능)

이제 다시 테스트를 돌려보면 실패하게 될 것이다. 왜냐하면 아직 예외를 던지지 않았기 때문이다. 
에러를 해결하기 위해 membershipRepository 에서 가져오는 객체가 존재한다면 예외를 던지게 작성합니다. 

```java
@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        return null;
    }
}
```

다시 테스트를 돌려보면 중복 검증에 걸려서 예외를 던지고 테스트는 성공하게 됩니다. 이제 테스트를 성공하는 테스트를 작성해야 합니다. 맴버십 등록의 응답값으로는
맴버십 id와 맴버십 타입을 반환해주어야 하므로 두 값이 잘 반환되는지를 통해 검증합니다.
```java
@Test
public void 멤버십등록성공() {
    // given
    doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
    doReturn(membership()).when(membershipRepository).save(any(Membership.class));
 
    // when
    final Membership result = target.addMembership(userId, membershipType, point);

    // then
    assertThat(result.getId()).isNotNull();
    assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

    // verify
    verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
    verify(membershipRepository, times(1)).save(any(Membership.class));
}

private Membership membership() {
    return Membership.builder()
            .id(-1L)
            .userId(userId)
            .point(point)
            .membershipType(MembershipType.NAVER)
            .build();
}
```
테스트를 돌려보면 실패할 것입니다. 그 이유는 MembershipService에서 null 값을 반환하기 떄문이다. 이제 올바른 membership을 반환할 수 있도록 수정해봅니다. 
```java
@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();

        return membershipRepository.save(membership);
    }
}
```

이제 테스트에 성공한 것을 볼 수 있습니다. 이제부터 **리팩토링**을 할 차례입니다. 