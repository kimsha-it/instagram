package com.example.instagram.service;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Bookmark;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.repository.BookmarkRepository;
import com.example.instagram.repository.CommentRepository;
import com.example.instagram.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostService postService;
    private final UserService userService;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void toggleBookmark(Long postId, Long userId) {
        Optional<Bookmark> existingBookmark = bookmarkRepository.findByPostIdAndUserId(postId, userId);

        if (existingBookmark.isPresent()) {
            // 북마크가 이미 존재하면 삭제
            bookmarkRepository.delete(existingBookmark.get());
        } else {
            // 북마크가 없으면 생성
            Post post = postService.findById(postId);
            User user = userService.findById(userId);

            Bookmark bookmark = Bookmark.builder()
                    .post(post)
                    .user(user)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public boolean isBookmarked(Long postId, Long userId) {
        return bookmarkRepository.existsByPostIdAndUserId(postId, userId);
    }

    @Override
    public long getBookmarkCount(Long postId) {
        return bookmarkRepository.countByPostId(postId);
    }

    @Override
    public Slice<PostResponse> getBookmarkedPosts(Long userId, Pageable pageable) {
        Slice<Bookmark> bookmarks = bookmarkRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        // Slice의 map 기능을 사용하여 Slice<Bookmark>를 Slice<PostResponse>로 변환
        return bookmarks.map(bookmark -> {
            Post post = bookmark.getPost();
            long likeCount = likeRepository.countByPostId(post.getId());
            long commentCount = commentRepository.countByPostId(post.getId());
            long bookmarkCount = bookmarkRepository.countByPostId(post.getId()); // 해당 게시물의 전체 북마크 수
            boolean isBookmarkedByCurrentUser = true; // 이 메소드에서 반환되는 게시물은 현재 사용자가 북마크한 것이므로 true
            return PostResponse.from(post, commentCount, likeCount, bookmarkCount, isBookmarkedByCurrentUser);
        });
    }
}
