package com.board.board_crud.webservice.web;

import com.board.board_crud.webservice.domain.posts.PostsRepository;
import com.board.board_crud.webservice.dto.posts.PostsResponseDto;
import com.board.board_crud.webservice.dto.posts.PostsSaveRequestDto;
import com.board.board_crud.webservice.dto.posts.PostsUpdateRequestDto;
import com.board.board_crud.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;
    private PostsService postsService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PostMapping("/update/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody PostsUpdateRequestDto dto)
    {
        return postsService.update(id, dto);
    }

    @PostMapping("/delete/{id}")
    public void deletePosts(@PathVariable Long id)
    {
        postsService.delete(id);
    }
}