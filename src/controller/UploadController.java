package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {
    private final int max = 8;//最大传输文件数
    int i = 0;//Number of files selected
    @RequestMapping("upload.do")
    public String upload(HttpServletRequest request, MultipartRequest multipartRequest, Model model) throws IOException {
        List<MultipartFile> uploadFiles = multipartRequest.getFiles("uploadFiles");
        List<String> imageUrls = new ArrayList<>(); // Use a new list to store the image URLs.
        List<String> names = new ArrayList<>(); // Use a new list to store the name URLs.
        List<String> sizes = new ArrayList<>(); // Use a new list to store the size URLs.
        List<String> extensions = new ArrayList<>(); // Use a new list to store the extension URLs.
        for (MultipartFile uploadFile : uploadFiles) {
            String name=("文件名：" + uploadFile.getOriginalFilename());
            String extension=("文件类型：" + uploadFile.getContentType());
            String size=("文件的大小：" + uploadFile.getSize() + " bytes");
            names.add(name); // Add the new name URL to the list.
            model.addAttribute("filename", names); // Add the list to the Model.
            sizes.add(size); // Add the new size URL to the list.
            model.addAttribute("filesize", sizes); // Add the list to the Model.
            extensions.add(extension); // Add the new extension URL to the list.
            model.addAttribute("fileextension", extensions); // Add the list to the Model.
            String dirPath = request.getServletContext().getRealPath("/upload");
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String fileExtension = "";
            try {
                fileExtension = Files.probeContentType(Paths.get(uploadFile.getOriginalFilename())).split("/")[0];
            } catch (IOException e) {
                e.printStackTrace();
                return "failure";
            }
            if (!fileExtension.equals("image")) {
                return "failure";
            }
            try {
                uploadFile.transferTo(new File(filePath + "/" + uploadFile.getOriginalFilename()));
                String imageUrl = request.getContextPath() + "/upload/" + uploadFile.getOriginalFilename();
                imageUrls.add(imageUrl); // Add the new image URL to the list.
                model.addAttribute("images", imageUrls); // Add the list to the Model.
            } catch (IOException e) {
                e.printStackTrace();
                return "failure";
            }
            i++;
        }
        if (i > max) {
            return "failure";
        }
        return "success";
    }
}