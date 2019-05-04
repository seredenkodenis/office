package com.portfolio.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class MainController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String title, @RequestParam String subtitle, @RequestParam String firstContent, @RequestParam String secondContent, @RequestParam String thirdContent, @RequestParam Integer quest, @RequestParam Integer stage) {
        Article article = new Article(title,subtitle,firstContent,secondContent,thirdContent,quest,stage);
        articleRepository.save(article);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping(path = "/find/{id}")
    public @ResponseBody Article findArt(@PathVariable("id") Integer id){ return articleRepository.findArticleById(id);}
}