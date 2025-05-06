package com.example.demo.model.dto;


import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO: Data Transfer Object
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	
	@NotNull(message = "id 不可以是空值")
	private Integer id; // 對應 room.roomId 欄位
	
	@NotNull(message = "房名不可以是空值")
	@Size(min = 1, max = 10, message = "⚠️名稱必須限定在 {min} ~ {max} 個字間")
	private String name; // 對應 room.roomName 欄位
	
	@NotNull(message = "人數不可以是空值")
	@Range(min = 1, max = 10, message = "⚠️人數必須限定在 {min} ~ {max} 個人")
	private Integer size; // 對應 room.roomSize 欄位
}
