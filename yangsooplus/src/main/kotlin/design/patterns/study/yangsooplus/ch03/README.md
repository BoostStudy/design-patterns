### Chapter3 설계 원칙

## SRP
- 클래스에 적용할 것인가, 모듈 단위에 적용할 것인가?
- 어떤 책임까지 단일 책임이라고 볼 수 있을까?
  - 유저 클래스에 이름, 나이, 주소(도, 시, 구, 상세주소)가 있다고 했을때,
    - 주소는 유저와 관련된 데이터다. 고로 타당함
    - 주소 정보는 독립적인 클래스로 분할하여 책임을 단일화 해야한다.
- 결정원칙
    1. 클래스에 코드, 함수, 속성이 너무 많아 가독성과 유지보수성에 영향을 미치는 경우
    2. 너무 과하게 다른 클래스에 의존한다면
    3. 클래스에 private 메서드가 너무 많은 경우 - private 메서드를 새 클래스로 분리하고 public으로 돌려서 재사용성 향상
    4. 클래스 이름을 비즈니스적으로 지정하기 어려울 때 (Manager, Context 처럼 일반적인 네이밍을 하게 될 때) - 클래스의 정의가 명확하지 않음
    5. 클래스의 많은 메서드가 일부 속성에서만 작동하는 경우

## OCP
- 확장에는 개방, 수정에는 폐쇄
- 근데.. 코드를 변경할 때 그 결과를 확장으로 봐야할까, 수정으로 봐야할까?
- 수정을 가능한 상위 수준에서 진행하고, 코드의 핵심부분/복잡한 부분/공통코드/기반코드가 개방 폐쇄를 충족하도록

## ISP
- 여기서 말하는 인터페이스는?
  - API나 기능의 집합
  - 단일 API 또는 기능
  - 객체지향 프로그래밍의 인터페이스

## KISS 원칙
- 코드를 가능한 간단하게 유지하자
  - 간단한 코드란 무엇인가?
    - 짧은 코드? ❌
    - 복잡한 정규식, 높은 레벨의 코드 등 복잡한 기술을 사용하지 않음
    - 바퀴를 다시 발명하지 않고 라이브러리 사용 고려
    - 과도하게 최적화 하지 않기 (산술 대신 비트 연산...)

## YAGNI 원칙
- 현재 사용하지 않는 기능을 설계하지 말고, 현재 사용되지 않는 코들르 작성하지 않는다
- 과도한 설계 하지마라

## Dry 원칙
- 중복코드를 작성하지 말라
- 프로젝트에 동일한 코드가 여러개 존재하면 Dry 위반인가?
  - 그건 또 아님
  - 코드 자체를 위반 대상에 퐇마되지 않는다
- 코드 논리 중복, 의미론적인 기능 중복, 코드 중복 실행이 DRY 위반이다

## 데메테르의 법칙 LoD
- 최소 지식의 원칙이라고도 함
  - 모든 유닛이 자신 + 자신과 밀접하게 관련된 유닛에 대해 제한된 지식만 알아야 한다.
