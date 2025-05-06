package com.example.demo.model.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//自訂義 Rest 統一對外的資料格式
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {
	private int status ; //狀態
	private String message; //訊息
	private T dataT; //回應資料
	
	//成功回應
	public static <T> ApiResponse<T> success(String message,T data) {
		return new ApiResponse<T>(HttpStatus.OK.value(),message,data);
		
	}
	
	//失敗回應
	public static <T> ApiResponse<T> error(int status,String message) {
		return new ApiResponse<T>(status,message,null);
		
	}	
}
