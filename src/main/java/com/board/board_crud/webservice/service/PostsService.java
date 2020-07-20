package com.board.board_crud.webservice.service;


import com.board.board_crud.webservice.domain.posts.Posts;
import com.board.board_crud.webservice.domain.posts.PostsRepository;
import com.board.board_crud.webservice.dto.posts.PostsMainResponseDto;
import com.board.board_crud.webservice.dto.posts.PostsResponseDto;
import com.board.board_crud.webservice.dto.posts.PostsSaveRequestDto;
import com.board.board_crud.webservice.dto.posts.PostsUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto)
    {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지않습니다."));

        posts.update(dto.getTitle(), dto.getContent());
        return id;
    }

    public PostsResponseDto findById(long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }


    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

}