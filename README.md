# Trả lời câu hỏi Lab 1

## 2. Ràng buộc Khóa Chính (Primary Key)

Khi cố tình insert một sinh viên có `id` trùng, database báo lỗi: UNIQUE constraint failed

Lý do: cột `id` được khai báo là `PRIMARY KEY`, nên mỗi giá trị phải **duy nhất** và **không được NULL**.

Database chặn thao tác này để:
- tránh trùng dữ liệu
- đảm bảo mỗi sinh viên là một bản ghi duy nhất
- tránh lỗi khi update/delete hoặc truy vấn dữ liệu

Nếu cho phép trùng khóa chính, hệ thống sẽ không xác định được bản ghi nào là đúng.

---

## 3. Toàn vẹn dữ liệu (Constraints)

Khi insert sinh viên với `name = NULL`, database **không báo lỗi** vì cột `name` không có ràng buộc `NOT NULL`.

Điều này gây rủi ro khi Java đọc dữ liệu:
- có thể nhận giá trị `null`
- dễ gây `NullPointerException`
- hiển thị dữ liệu sai
- logic chương trình bị lỗi

Thiếu ràng buộc dữ liệu làm giảm độ tin cậy của hệ thống.

---



