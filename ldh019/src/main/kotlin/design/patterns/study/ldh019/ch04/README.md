# 4장 코딩 규칙

## 명명과 주석

- 변수를 사용하는 범위에 따라 변수의 이름을 정하는 것이 좋음
    - 함수에서 사용하는 임시 변수의 경우, 짧은 이름을 사용하는 것이 좋음
    - 전역 변수처럼 범위가 크다면, 긴 이름으로 의미를 표현하는 것이 좋음
- 문맥 정보를 이용해서 중복되는 접두사를 사용하지 말기
- 보편적으로 사용하는 비즈니스 용어를 사용해 가독성 유지
- 너무 모호해서 작업을 알 수 없는 단어는 지양하기

- 주석에는 **무엇을, 왜, 어떻게** 를 담을 것
    - 함수는 이름만으로 무엇을 하는지 알 수 있음, 클래스의 경우에는 주석으로 **무엇을** 하는 클래스인지 설명하는 것이 필요
    - 코드의 구현 사상이나 특수한 경우에 대한 설명을 작성해 **어떻게** 사용하는지 알릴 수 있음
    - 복잡한 함수를 나누기 힘든 경우, **왜** 이렇게 사용하는지에 대한 가독성을 도와줄 수 있음
- 주석도 너무 많으면 가독성 떨어짐

## 코드 스타일

- 클래스나 함수의 크기도 적당히 구성해야 함, 너무 크면 이해에 도움이 되지 않음
- 한 줄의 길이는 슬라이더를 움직이지 않는 적당한 정도가 좋음
- 빈 줄을 이용하여 모듈이나 코드의 경계를 주어 구조를 정리할 수 있음
- 들여쓰기는 다 다르지만 공간 절약을 위해 2칸을 권장, Tab 키 사용하지 않는 것이 좋음
- 여는 중괄호의 위치도 알잘딱깔센 → 응 위에 적을거야

## 코딩 팁

- 모듈화를 통한 복잡한 코드 정리하기 → 코드 가독성 향상
- 매개변수가 너무 많으면 함수를 나눠서 정리할 것
- 플래그 매개변수는 함수를 나눌 것 → SRP, ISP 위반
- 너무 깊은 중첩은 가독성에 영향
    - 중복되는 조건문을 없애고 제어문으로 중첩에서 나오기
    - 실행 순서를 바꿔 중첩 단계 줄이기
    - 코드 일부를 함수로 캡슐화해 중첩 단계 줄이기
- 매직 넘버 대신 상수 사용
- 설명 변수로 복잡한 표현 설명