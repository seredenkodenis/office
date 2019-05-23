package com.portfolio.office;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class ImageController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "/imageDisplay/{id}")
    public void showImage(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException, SQLException {
        Article article = articleRepository.findArticleById(id);
        Blob b = article.getImage();
        byte[] bytes = b.getBytes(1, (int) b.length());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());

    }
}
