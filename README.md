# Highthon 10th - 찜하기 서버

## 프로젝트 소개
하이톤 10기 프로젝트의 백엔드 서버입니다. 회원 관리와 찜하기 기능을 제공합니다.

## 기술 스택
- Java 17
- Spring Boot 3.2.2
- Spring Security
- Spring Data JPA
- MySQL
- AWS S3

## 주요 기능
1. 회원 관리
   - 회원가입
   - 프로필 조회 (ID, 닉네임)
   - 전체 회원 조회
   - 성별별 회원 조회
   - 프로필 수정

2. 찜하기 기능
   - 찜하기/취소
   - 내가 찜한 목록 조회
   - 나를 찜한 목록 조회
   - 찜 여부 확인

## API 문서
자세한 API 명세는 [여기](/docs/API.md)에서 확인할 수 있습니다.

## 로컬 실행 방법
1. 저장소 클론
```bash
git clone https://github.com/highthon-10th-favorite/server.git
cd server
```

2. MySQL 데이터베이스 생성
```sql
CREATE DATABASE highthon;
```

3. 환경 변수 설정
`.env.example` 파일을 참고하여 `.env` 파일을 생성하고 필요한 환경 변수를 설정합니다:
```bash
cp .env.example .env
```
그리고 `.env` 파일에서 다음 환경 변수들을 설정합니다:
- `DB_USERNAME`: 데이터베이스 사용자 이름
- `DB_PASSWORD`: 데이터베이스 비밀번호
- `JWT_SECRET`: JWT 시크릿 키
- `AWS_ACCESS_KEY`: AWS 액세스 키
- `AWS_SECRET_KEY`: AWS 시크릿 키
- `AWS_S3_BUCKET`: S3 버킷 이름

4. 프로젝트 실행
```bash
./gradlew bootRun
```

## 개발 환경 설정
- Java 17 설치
- MySQL 설치
- AWS S3 버킷 생성 및 설정 