package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.dto.RoomDTO;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

/**
 * Method URI                  功能
 * --------------------------------------------------------------------
 * GET    /room                 查詢所有會議室(多筆)
 * GET    /room/{roomId}        查詢指定會議室(單筆)
 * POST   /room                 新增會議室
 * POST   /room/update/{roomId} 完整修改會議室(同時修改 roomName 與 roomSize)
 * GET    /room/delete/{roomId} 刪除會議室
 * --------------------------------------------------------------------
 * */

@Controller
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping // 查詢所有會議室(多筆) / 首頁
	public String getRooms(Model model, RoomDTO roomDTO) {
		List<RoomDTO> roomDTOs = roomService.getAllRooms(); 
		model.addAttribute("roomDTOs", roomDTOs); // 相當於 req.setAttribute("roomDTOs", roomDTOs);
		return "room/room"; // 指向 jsp 檔名
	}
	
	@GetMapping("/{roomId}") // 查詢指定會議室(單筆)
	//@ResponseBody
	public String getRoom(@PathVariable Integer roomId,Model model) {
		//得到要修改的資料
		RoomDTO roomDTO = roomService.getRoomById(roomId);
		//將要修改的資料傳給jsp
		model.addAttribute("roomDTO",roomDTO);
		return "/room/room_update";
	}
	
	@PostMapping
	public String addRoom(@Valid RoomDTO roomDTO, BindingResult result,Model model) { // 新增會議室
		if(result.hasErrors()) {
			List<RoomDTO> roomDTOs = roomService.getAllRooms(); 
			model.addAttribute("roomDTOs", roomDTOs); // 相當於 req.setAttribute("roomDTOs", roomDTOs);
			return "room/room";
		}
		
		roomService.addRoom(roomDTO); //儲存
		// 通知瀏覽器要去的地方(重導到...)
		return "redirect:/room";
	}	
	
	@PostMapping("/update/{roomId}")
	//@ResponseBody
	//@Valid RoomDTO roomDTO = >檢查roomDTO是否符合驗證規則
	//BindingResult result => 存放檢查roomDTO的驗證結果
	public String updateRoom(@PathVariable Integer roomId,@Valid RoomDTO roomDTO, BindingResult result) { // 修改會議室
		if(result.hasErrors()) {  //若有驗證錯誤產生
			return "/room/room_update"; //回到jsp去顯示錯誤
		}
		
		roomService.updateRoom(roomId, roomDTO); //修改
		// 通知瀏覽器要去的地方(重導到...)
		return "redirect:/room";
	}
	
	@GetMapping("/delete/{roomId}") // 刪除會議室
	public String deleteRoom(@PathVariable Integer roomId) {
		roomService.deleteRoom(roomId);
		// 通知瀏覽器要去的地方(重導到...)
		return "redirect:/room";
	}
	
}