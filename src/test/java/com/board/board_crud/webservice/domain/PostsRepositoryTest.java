package com.board.board_crud.webservice.domain;

import com.board.board_crud.webservice.domain.posts.Posts;
import com.board.board_crud.webservice.domain.posts.PostsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        /**
         * 이후 테스트 코드에 영향을 끼치지 않기 위해
         * 테스트 메소드가 끝날때 마다 repository 전체를 비우는 코드
         * */

        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        /*given : 테스트 기반 환경을 구축, @builder의 사용법도 같이 확인
        * */
        //given
        postsRepository.save(Posts.builder()
        .title("테스트 게시글")
        .content("테스트 본문")
        .author("zone7907@gmail.com")
        .build());

        /* when 테스트 하고자 하는 행위 선언, Posts가 DB에 insert 되는것을 확인하기 위함
        * */
        //when
        List<Posts> postsList = postsRepository.findAll();

        /*then 테스트 결과 검증, 결과값 확인*/
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }
    
    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("zone7907@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));

    }
}
