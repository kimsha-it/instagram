package com.example.instagram.service;

import com.example.instagram.dto.response.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkService {

    /**
     * 게시물에 대한 북마크를 추가하거나 삭제합니다 (토글 방식).
     * @param postId 게시물 ID
     * @param userId 사용자 ID
     */
    void toggleBookmark(Long postId, Long userId);

    /**
     * 사용자가 특정 게시물을 북마크했는지 확인합니다.
     * @param postId 게시물 ID
     * @param userId 사용자 ID
     * @return 북마크 여부
     */
    boolean isBookmarked(Long postId, Long userId);

    /**
     * 특정 게시물의 총 북마크 수를 조회합니다.
     * @param postId 게시물 ID
     * @return 북마크 수
     */
    long getBookmarkCount(Long postId);

    /**
     * 특정 사용자가 북마크한 게시물 목록을 조회합니다.
     * @param userId 사용자 ID
     * @param pageable 페이징 정보
     * @return 페이징 처리된 게시물 목록
     */
    Slice<PostResponse> getBookmarkedPosts(Long userId, Pageable pageable);
}
