package com.portfolio.office;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;


@Controller
@RequestMapping
public class MainController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/add")
    public String addNewArticle(){
        return "add";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewArticle2 (@RequestParam String title, @RequestParam String subtitle, @RequestParam String firstContent, @RequestParam String secondContent, @RequestParam String thirdContent, @RequestParam Integer quest, @RequestParam Integer stage) {
        Article article = new Article(title,subtitle,firstContent,secondContent,thirdContent,quest,stage);
        articleRepository.save(article);
            String s;
            s = "http://localhost:8080/article/"+ quest+"/"+stage + "/";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = qrCodeWriter.encode(s, BarcodeFormat.QR_CODE, 350, 350);
                Path path = FileSystems.getDefault().getPath(quest + "_" + stage + ".png");
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "Your mission was added successfully";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping(path = "/upload")
    public String upload1(Model model){
        return "upload";
    }

    @PostMapping(path="/upload")
    public @ResponseBody
    String upload(@RequestParam("file")MultipartFile image) throws IOException, SQLException {
        Article article = new Article();
        byte[] contents = image.getBytes();
        Blob blob = new SerialBlob(contents);
        article.setImage(blob);
        articleRepository.save(article);
        return "upload";
    }


    @GetMapping(path = "/image/{id}")
    public String findArticle1(@PathVariable("id") Integer id, Model model){
        Article article = articleRepository.findArticleById(id);
        model.addAttribute("img",article.getId());
        return "image";
    }

    @GetMapping(path = "/article/{questId}/{stage}")
    public String findArticle(Model model, @PathVariable("questId") Integer questId, @PathVariable("stage") Integer stage){
        Article article = articleRepository.findArticleByQuestAndStage(questId,stage);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("subtitle",article.getSubtitle());
        return "page";
    }
}