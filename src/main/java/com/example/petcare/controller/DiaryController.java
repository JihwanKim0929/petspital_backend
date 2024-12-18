package com.example.petcare.controller;

import com.example.petcare.dto.DiaryDto;
import com.example.petcare.dto.DiaryPageDto;
import com.example.petcare.entity.Diary;
import com.example.petcare.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    @GetMapping("/diary/{id}")
    public ResponseEntity<DiaryDto> getDiary(@PathVariable Long id) {
        DiaryDto dto = diaryService.getDiary(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/pet/{petId}/diary")
    public ResponseEntity<List<DiaryDto>> getDiaryByPet(@PathVariable Long petId) {
        List<DiaryDto> dtos = diaryService.get_diary_list(petId);
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping("/pet/{petId}/diary")
    public ResponseEntity<DiaryDto> createDiary(@PathVariable Long petId, @RequestBody DiaryDto dto) {
        DiaryDto createdDto = diaryService.createDiary(dto, petId);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping("/diary/{id}")
    public ResponseEntity<DiaryDto> deleteDiary(@PathVariable Long id) {
        DiaryDto deletedDto = diaryService.deleteDiary(id);
        if(deletedDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/updateDiary/{id}")
    public ResponseEntity<DiaryDto> updateDiary(@PathVariable Long id, @RequestBody DiaryDto diaryDto) {
        DiaryDto deletedDto = diaryService.updateDiary(id, diaryDto);
        if(deletedDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
