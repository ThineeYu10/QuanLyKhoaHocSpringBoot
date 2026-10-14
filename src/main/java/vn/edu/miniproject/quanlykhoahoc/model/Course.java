package vn.edu.miniproject.quanlykhoahoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(max = 150, message = "Tên khóa học tối đa 150 ký tự")
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank(message = "Tên giảng viên không được để trống")
    @Size(max = 100, message = "Tên giảng viên tối đa 100 ký tự")
    @Column(nullable = false, length = 100)
    private String instructor;

    @NotBlank(message = "Danh mục không được để trống")
    @Column(nullable = false, length = 80)
    private String category;

    @NotNull(message = "Học phí không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Học phí phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Học phí không hợp lệ")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Thời lượng không được để trống")
    @Min(value = 1, message = "Thời lượng tối thiểu là 1 giờ")
    @Max(value = 10000, message = "Thời lượng quá lớn")
    @Column(nullable = false)
    private Integer durationHours;

    @Size(max = 1000, message = "Mô tả tối đa 1000 ký tự")
    @Column(length = 1000)
    private String description;

    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getDurationHours() { return durationHours; }
    public void setDurationHours(Integer durationHours) { this.durationHours = durationHours; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
