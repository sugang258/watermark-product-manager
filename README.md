# 🖼️ Image Watermark Product Management System

Spring Boot + React 기반의 **상품 등록 및 이미지 워터마크 처리 웹 애플리케이션**입니다.

이미지를 업로드하면 자동으로 워터마크가 적용되고, 첫 번째 이미지는 썸네일로 저장되는 구조의 상품 관리 시스템입니다.

---

# 🚀 Project Overview

이 프로젝트는 다음 기능을 중심으로 구현되었습니다.

✅ 상품 등록 (이미지 다중 업로드)

✅ 첫 번째 이미지 자동 썸네일 지정

✅ 업로드 이미지 자동 워터마크 삽입

✅ 상품 목록 조회

✅ 상품 상세 조회

---

# 📂 Project Structure

```
watermark-product-manager/
├── backend/   # Spring Boot API Server
└── frontend/  # React (Vite)
```

---

# 🧰 Tech Stack
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![React](https://img.shields.io/badge/React-Vite-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.x-orange)

## 🔧 Backend

* Java
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Multipart File Upload
* BufferedImage 기반 Watermark Processing

## 🎨 Frontend

* React
* Vite
* Fetch API

---

# ✨ Features

## 📦 상품 등록 기능

다음 정보를 함께 저장합니다.

* 상품명
* 가격
* 재고 수량
* 이미지 여러 장 업로드

업로드 시 동작:

```
이미지 업로드
 → 서버 저장
 → 워터마크 적용
 → 썸네일 지정
 → DB 저장
```

첫 번째 이미지는 자동으로 **썸네일 이미지**로 저장됩니다.

모든 업로드 이미지는 서버 저장 시 **워터마크 자동 적용**됩니다.

---

# 🖼️ Image Processing Flow

이미지 저장 시 다음 과정 수행:

```
원본 이미지 저장
 → 워터마크 이미지 로드
 → 크기 자동 조정
 → 투명도 적용
 → 원본 이미지에 합성
 → 저장
```

워터마크 위치:

```
오른쪽 하단
```

---

# 📡 API Specification

## 📄 상품 등록

```
POST /api/items
```

Multipart FormData 요청

```
item: JSON
images: files[]
```

예시 JSON:

```
{
  "name": "아이폰 케이스",
  "price": 15000,
  "stockQuantity": 30
}
```

---

## 📋 상품 목록 조회

```
GET /api/items
```

응답 포함:

* 상품 정보
* 썸네일 이미지

---

## 🔍 상품 상세 조회

```
GET /api/items/{id}
```

응답 포함:

* 상품 정보
* 전체 이미지 목록

---

# ⚙️ Backend 실행 방법

```
cd backend
./gradlew bootRun
```

Windows:

```
gradlew bootRun
```

서버 실행 주소:

```
http://localhost:8080
```

---

# 💻 Frontend 실행 방법

```
cd frontend
npm install
npm run dev
```

프론트 실행 주소:

```
http://localhost:5173
```

---

# 🗂️ Image Upload Directory 설정

application.yml 설정 예시:

```
file:
  dir: C:/upload/item-images/
```

해당 경로는 서버에서 자동 생성하거나 미리 생성해야 합니다.

---

# 🖥️ Static Resource Mapping

이미지 접근 경로:

```
http://localhost:8080/images/{filename}
```

Spring 설정:

```
/images/** → file directory mapping
```

---

# 🧪 Development Environment

권장 개발 환경:

* Java 17+
* Node.js 24 LTS
* MySQL 8+

---

# 📈 Future Improvements

추가 예정 기능:

* 상품 수정 기능
* 상품 삭제 기능
* 이미지 미리보기 기능
* Drag & Drop 업로드
* 관리자 인증 기능
* AWS S3 업로드 확장

---

# 👩‍💻 Author

GitHub:

```
https://github.com/sugang258
```

Project:

```
Image Watermark Product Management System
```
