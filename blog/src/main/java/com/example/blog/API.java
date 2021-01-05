package com.example.blog;

import com.example.blog.Repositories.BlogsRepo;
import com.example.blog.dao.Blogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class API {
    @Autowired
    private BlogsRepo blogsRepo;

    // Index page render by resource/static/index    

    @GetMapping("/api/v1/blogs")
    public List<Blogs> findAllBlogs() {
        return blogsRepo.findAll();
    }

    @GetMapping("/api/v1/blogs/{id}")
    public Blogs findBlogById (@PathVariable("id") Integer id) {
        return blogsRepo.findById(id);
    }

    @PostMapping("/api/v1/blogs")
    public Blogs createBlog (@RequestBody Blogs blogs) {
        blogs.setPostDate(LocalDateTime.now());
        return blogsRepo.save(blogs);
    }

    @PutMapping("/api/v1/blogs/{id}")
    public Blogs editBlog(@PathVariable("id") Integer id, @RequestBody Blogs payload) {
        Blogs blog = blogsRepo.findById(id);

        blog.setDetail(payload.getDetail());
        blog.setAuthor(payload.getAuthor());
        blog.setLove(payload.getLove());
        blog.setPostDate(LocalDateTime.now());

        return blogsRepo.save(blog);
    }

    @PutMapping("/api/v1/blogs/love/{blogId}")
    public Blogs increaseBlogLove(@PathVariable("blogId") Integer id) {
        Blogs blog = blogsRepo.findById(id);
        blog.setLove(blog.getLove()+1);
        return blogsRepo.save(blog);
    }

    @DeleteMapping("/api/v1/blogs/{id}")
    public void deleteBlog (@PathVariable("id") Integer id) {
        blogsRepo.delete(blogsRepo.findById(id));
    }
}
