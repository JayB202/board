package com.sparta.board.repository;

import com.sparta.board.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMembersIdAndBoardId(Long userId, Long boardId);

    Optional<Likes> findByMembersIdAndBoardIdAndCommentId(Long userId, Long boardId, Long commentId);
    Likes deleteByMembersIdAndBoardId(Long boardId, Long userId);

    Likes deleteByMembersIdAndBoardIdAndCommentId(Long userId, Long boardId, Long commentId);

    void deleteAllByCommentId(Long commentId);

    void deleteAllByBoardId(Long boardId);
}
