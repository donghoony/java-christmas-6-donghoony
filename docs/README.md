# 크리스마스 프로모션 미션

### 이메일에서 요구사항 꺼내기

`이벤트 예산을 넉넉히 확보해 두었으니, 예산은 걱정하지 마세요~` : 예산이 매우 커질 수 있으므로, long보다 큰 범위를 담당할 수 있는 `BigInteger`등을 고려하자

`"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.`: 에러 메시지 출력 시, 3주 차 미션처럼 다시 입력하도록 하자

`12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것` : 1월에도 같은 형식의 이벤트가 진행될 수 있으므로, 1월로의 확장성도 고려해서 작성하자

# [도메인](#도메인)

#### [카테고리](#카테고리)

- [x] 애피타이저, 메인, 디저트, 음료

#### [메뉴](#메뉴)

- [x] [카테고리](#카테고리), 메뉴이름, 가격

#### [날짜](#날짜)

이벤트 날짜를 기록, 이벤트의 종류에 따라 범위 이벤트, 특정 날짜 이벤트, 범위 + 특정 요일 이벤트가 있다

- [x] 날짜 범위를 표현
  - [x] 어떤 날짜가 해당 범위에 포함되는지 여부
  - [x] 필요하다면, 특정 날짜가 행사 요일에 포함되는지 여부
- [x] 특정 행사일들을 표현
  - [x] 어떤 날짜가 행사일에 해당하는지 여부

#### [돈](#돈)

프로그램 내부에서 돈을 나타낸다. 우아한테크코스의 예산을 신경쓰지 않아도 되므로, `long` 범위를 벗어나는 가격 등을 고려한다.
- [x] 세 자리 단위로 끊어 포매팅한다.
- [x] 화폐 단위(원)을 끝에 나타내도록 한다.
- [x] 서로 같은 가격인 경우, 프로그램 상에서 같다고 판단한다.

#### [할인](#할인)

- [x] 기본적으로 **총주문 금액이** `10,000`원 이상부터 적용

##### 할인의 종류에 따라,

- [x] 음식 카테고리별 할인
- [x] 총 주문 금액 할인


- [x] **공통 사항**
  - 할인 행사 일정
  - 할인 카테고리 (총 주문, 메뉴 등)
  - 할인 금액


- [x] 디데이 할인
  - 12/1 ~ 12/**25**
  - **총 주문 할인**
  - 12/1일부터 `1,000`원 할인, 25일까지 `100`원씩 증가해 25일에 `3,400`원 할인


- [x] 특별 할인
  - 달력에 별이 그려진 날짜 (3, 10, 17, 24, 25, 31일)
  - **총 주문 할인**
  - `1,000`원 할인


- [x] 평일 할인 (일~목): **일요일은 평일**이다
  - 12/1 ~ 12/31
  - **디저트 메뉴 할인**
  - 개당 `2,023`원 할인


- [x] 주말 할인 (금~토): 주말은 **금요일과 토요일**이다
  - 12/1 ~ 12/31
  - **메인 메뉴 할인**
  - 개당 `2,023`원 할인

#### [증정](#증정)

- [x] **공통 사항**
  - 증정 행사 일정
  - 증정 메뉴


- [x] 샴페인 증정
  - 12/1 ~ 12/31
  - **총주문 금액**이 `120,000`원 이상이면 샴페인(`25,000`원) 증정

#### [메뉴 목록](#메뉴-목록)

어떤 메뉴를 얼마나 시켰는지에 대한 정보를 가지고 있다.

(검증)

- [x] 음료가 아닌 다른 카테고리의 메뉴가 존재해야 한다. (주문 불가)
- [x] 메뉴가 20개 이하여야 한다. (주문 불가)
- [x] 중복 메뉴가 존재하면 안 된다 (주문 불가)

(확인)

- [x] 총 주문 금액이 `10,000`원 이상인지 확인한다
- [x] 주문 메뉴에 특정 카테고리에 해당하는 메뉴가 몇 개인지 확인한다
- [x] 주문 내역의 총 가격을 반환한다.

#### [주문 영수증](#주문-영수증)

총 주문 금액, 총 혜택 금액, 할인 후 예상 결제 금액, 할인 내역 등을 가지고 있음, 바뀌지 않음

#### [이벤트 배지](#이벤트-배지)

- [x] **혜택 금액(=할인 금액 + 증정 금액)**에 따른 배지 부여
- 5천 원 이상: 별
- 1만 원 이상: 트리
- 2만 원 이상: 산타

## [로직 수행 클래스](#로직-수행-클래스)

### [할인 서비스](#할인-서비스)

- [x] 주문 내역을 활용해 가능한 할인을 적용한 뒤, 할인이 적용된 내역을 반환한다

### [증정 서비스](#증정-서비스)

- [x] 주문 내역을 활용해 총 주문 금액을 확인한 뒤, 증정 메뉴를 반환한다

### [배지 서비스](#배지-서비스)

- [x] 할인 적용 내역 및 증정 내역을 받아서 뱃지를 반환한다

### [입력](#입력)
- [x] 날짜를 입력받는다
- [x] 주문 내역을 입력받는다  

발생할 수 있는 예외 사항: 유효하지 않은 주문
- 주어진 날짜가 특정 월에 포함되지 않는 경우
- 주어진 날짜가 정수가 아닌 경우
- 주문 내역이 구분자로 올바르게 구분되지 않은 경우
- 주문 개수가 정수가 아닌 경우
- 주문 개수가 1보다 작은 경우