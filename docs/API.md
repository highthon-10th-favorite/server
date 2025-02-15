# API 명세서

## 1. 회원(Member) API

### 1.1. 회원가입
- **URL**: `/api/members/signup`
- **Method**: `POST`
- **Request Body**:
```json
{
    "id": "string",            // 필수, Firebase UUID
    "nickname": "string",      // 필수, 닉네임
    "gender": "MALE" | "FEMALE", // 필수, 성별
    "preferredGender": "MALE" | "FEMALE", // 필수, 선호하는 성별
    "height": "number",        // 필수, 키
    "hobbies": ["string"],     // 선택, 취미 목록
    "job": "string",           // 선택, 직업
    "location": "string",      // 필수, 사는 곳
    "age": "number",           // 필수, 나이
    "introduction": "string",  // 선택, 자기소개
    "mbti": "string",         // 선택, MBTI
    "favoriteImageUrl": "string", // 선택, 최애 이미지 URL
    "profileImageUrl": "string"   // 선택, 프로필 이미지 URL
}
```
- **Response**:
```json
{
    "success": true,
    "message": "회원가입이 완료되었습니다.",
    "data": "string(UUID)"
}
```

### 1.2. ID로 프로필 조회
- **URL**: `/api/members/{id}`
- **Method**: `GET`
- **Path Variable**: 
  - `id`: 회원 UUID
- **Response**:
```json
{
    "success": true,
    "data": {
        "nickname": "string",
        "gender": "MALE" | "FEMALE",
        "preferredGender": "MALE" | "FEMALE",
        "height": "number",
        "hobbies": ["string"],
        "job": "string",
        "location": "string",
        "age": "number",
        "introduction": "string",
        "mbti": "string",
        "favoriteImageUrl": "string",
        "profileImageUrl": "string"
    }
}
```

### 1.3. 닉네임으로 프로필 조회
- **URL**: `/api/members/profile/{nickname}`
- **Method**: `GET`
- **Path Variable**: 
  - `nickname`: 회원 닉네임
- **Response**: ID로 프로필 조회와 동일

### 1.4. 전체 회원 조회
- **URL**: `/api/members`
- **Method**: `GET`
- **Response**:
```json
{
    "success": true,
    "data": [
        {
            // 프로필 정보 (1.2와 동일한 구조)
        }
    ]
}
```

### 1.5. 성별로 회원 조회
- **URL**: `/api/members/gender/{gender}`
- **Method**: `GET`
- **Path Variable**: 
  - `gender`: "MALE" 또는 "FEMALE"
- **Response**: 전체 회원 조회와 동일

### 1.6. 프로필 수정
- **URL**: `/api/members/{id}`
- **Method**: `PUT`
- **Path Variable**: 
  - `id`: 회원 UUID
- **Request Body**:
```json
{
    "nickname": "string",      // 선택
    "gender": "MALE" | "FEMALE", // 선택
    "preferredGender": "MALE" | "FEMALE", // 선택
    "height": "number",        // 선택
    "hobbies": ["string"],     // 선택
    "job": "string",           // 선택
    "location": "string",      // 선택
    "introduction": "string",  // 선택
    "mbti": "string",         // 선택
    "favoriteImageUrl": "string", // 선택
    "profileImageUrl": "string"   // 선택
}
```

## 2. 찜하기(Favorite) API

### 2.1. 찜하기
- **URL**: `/api/favorites/{toMemberId}`
- **Method**: `POST`
- **Path Variable**: 
  - `toMemberId`: 찜할 회원의 UUID
- **Headers**:
  - `X-Member-Id`: 현재 로그인한 회원의 UUID
- **Response**:
```json
{
    "success": true,
    "message": "찜하기가 완료되었습니다.",
    "data": null
}
```

### 2.2. 찜 취소
- **URL**: `/api/favorites/{toMemberId}`
- **Method**: `DELETE`
- **Path Variable**: 
  - `toMemberId`: 찜 취소할 회원의 UUID
- **Headers**:
  - `X-Member-Id`: 현재 로그인한 회원의 UUID
- **Response**:
```json
{
    "success": true,
    "message": "찜하기가 취소되었습니다.",
    "data": null
}
```

### 2.3. 내가 찜한 목록 조회
- **URL**: `/api/favorites/to-me`
- **Method**: `GET`
- **Headers**:
  - `X-Member-Id`: 현재 로그인한 회원의 UUID
- **Response**:
```json
{
    "success": true,
    "data": [
        {
            // 프로필 정보 (1.2와 동일한 구조)
        }
    ]
}
```

### 2.4. 나를 찜한 목록 조회
- **URL**: `/api/favorites/from-me`
- **Method**: `GET`
- **Headers**:
  - `X-Member-Id`: 현재 로그인한 회원의 UUID
- **Response**: 내가 찜한 목록 조회와 동일

### 2.5. 찜 여부 확인
- **URL**: `/api/favorites/check/{toMemberId}`
- **Method**: `GET`
- **Path Variable**: 
  - `toMemberId`: 찜 여부를 확인할 회원의 UUID
- **Headers**:
  - `X-Member-Id`: 현재 로그인한 회원의 UUID
- **Response**:
```json
{
    "success": true,
    "data": true | false
}
```

## 공통 에러 응답
```json
{
    "success": false,
    "message": "에러 메시지",
    "data": null
}
```

주요 에러 메시지:
- "존재하지 않는 회원입니다."
- "이미 존재하는 닉네임입니다."
- "이미 가입된 회원입니다."
- "자기 자신을 찜할 수 없습니다."
- "이미 찜한 회원입니다." 