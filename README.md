# Mini Project: Quản lý khóa học bằng Java Spring Boot

Ứng dụng web quản lý khóa học sử dụng **Java 17, Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, MySQL và Bootstrap 5**.

## 1. Chức năng
- Hiển thị danh sách khóa học.
- Thêm khóa học.
- Xem chi tiết khóa học.
- Cập nhật thông tin khóa học.
- Xóa khóa học (có hộp thoại xác nhận).
- Tìm kiếm theo tên khóa học hoặc giảng viên.
- Kiểm tra dữ liệu đầu vào: không để trống tên/giảng viên/danh mục, học phí phải lớn hơn 0, thời lượng tối thiểu 1 giờ.
- Tự tạo bảng `courses` khi chạy ứng dụng lần đầu.

## 2. Công cụ cần có
- JDK 17 trở lên.
- MySQL Server 8.x và MySQL Workbench (hoặc công cụ MySQL tương đương).
- IntelliJ IDEA / Eclipse / VS Code.
- Maven (hoặc dùng Maven Wrapper nếu bổ sung sau).

## 3. Cấu hình MySQL
1. Mở file `src/main/resources/application.properties`.
2. Sửa `spring.datasource.username` và `spring.datasource.password` theo tài khoản MySQL trên máy.
3. Mặc định ứng dụng kết nối MySQL ở `localhost:3306`. Cấu hình `createDatabaseIfNotExist=true` sẽ tự tạo database `quanlykhoahoc` nếu tài khoản có quyền tạo database.
4. Nếu muốn tạo database thủ công, chạy:

```sql
CREATE DATABASE quanlykhoahoc
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

## 4. Chạy project
### Cách 1: IntelliJ IDEA
1. Giải nén project.
2. Chọn **Open** và mở thư mục `QuanLyKhoaHocSpringBoot`.
3. Chờ Maven tải thư viện.
4. Kiểm tra cấu hình MySQL trong `application.properties`.
5. Mở `QuanLyKhoaHocApplication.java`, bấm nút Run.

### Cách 2: Terminal
Mở terminal tại thư mục có `pom.xml` rồi chạy:

```bash
mvn spring-boot:run
```

Khi console báo ứng dụng đã khởi động, mở trình duyệt tại:

http://localhost:8080

Trang gốc sẽ tự chuyển đến `/courses`.

## 5. Cấu trúc thư mục

```text
QuanLyKhoaHocSpringBoot/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/vn/edu/miniproject/quanlykhoahoc/
        │   ├── QuanLyKhoaHocApplication.java
        │   ├── controller/CourseController.java
        │   ├── model/Course.java
        │   └── repository/CourseRepository.java
        └── resources/
            ├── application.properties
            ├── static/css/style.css
            └── templates/courses/
                ├── list.html
                ├── form.html
                └── detail.html
```

## 6. Các URL chính
| Chức năng | URL / phương thức |
|---|---|
| Danh sách + tìm kiếm | `GET /courses` |
| Hiển thị form thêm | `GET /courses/new` |
| Lưu khóa học mới | `POST /courses` |
| Chi tiết | `GET /courses/{id}` |
| Form chỉnh sửa | `GET /courses/{id}/edit` |
| Lưu cập nhật | `POST /courses/{id}/update` |
| Xóa | `POST /courses/{id}/delete` |

## 7. Lưu ý
- Không đưa mật khẩu MySQL thật lên GitHub.
- Nếu lỗi `Access denied for user`, hãy kiểm tra username/password.
- Nếu lỗi `Communications link failure`, kiểm tra MySQL Server đã chạy và cổng 3306 đúng chưa.
- Nếu cổng 8080 bị sử dụng, đổi `server.port=8081` trong `application.properties`.
