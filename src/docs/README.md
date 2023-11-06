## 회원 요구사항 명세서

### 회원가입

- **기능 요구사항**
    - [x] 회원가입시 socialId, socialType, 이름을 전달한다.
    - [x] 첫 회원가입시 이름을 추가로 입력합니다.
        - [x] 1차 회원가입 이후, 생성된 id값을 반환하면 해당 id값으로 요청하도록 함?
    - [x] 첫 회원가입시 온보딩 목적의 정보를 입력합니다.
        - `운동목적1`, `운동목적2`, `식닥/운동 밸런스`, `운동 관심도`, `생활습관`, `식습관`
- **검증 사항**
    - [x] 휴대폰 번호는 검증이 필요하다.