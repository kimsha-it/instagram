package com.example.instagram.repository;

import com.example.instagram.entity.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    // 1. 특정 사용자의 북마크 목록 조회 (최신순 정렬, 페이징 포함)
    Slice<Bookmark> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // 2. 특정 게시물이 북마크 되었는지 확인
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    // 3. 북마크 삭제 (특정 게시물 ID와 사용자 ID를 이용)
    void deleteByPostIdAndUserId(Long postId, Long userId);

    // (추가) 특정 게시물의 북마크 수 조회
    long countByPostId(Long postId);

    // (추가) 특정 사용자가 북마크한 게시물 수 조회
    long countByUserId(Long userId);

    // (추가) 특정 게시물과 사용자 ID로 북마크 엔티티 조회 (필요시 사용)
    Optional<Bookmark> findByPostIdAndUserId(Long postId, Long userId);
}
