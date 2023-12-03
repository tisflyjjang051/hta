package com.jjang051.board.controller;


import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dto.*;
import com.jjang051.board.exception.BoardException;
import com.jjang051.board.service.BoardService;
import com.jjang051.board.utils.PaginationMaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @Value("${file.path}")
    private String uploadFolder;


    private final BoardService boardService;

    private final PaginationMaker paginationMaker;

    /*@GetMapping("/list")
    public String list(Model model,
                       @RequestParam(required = false) String category,
                       @RequestParam(required = false) String searchTxt) {
        log.info("category==={}, searchTxt==={}",category, searchTxt);
        List<BoardDto> boardList = boardService.getAllBoard(category,searchTxt);
        model.addAttribute("boardList",boardList);
        return  "/board/list";
    }*/
    @GetMapping("/list")
    public String list(Model model, @ModelAttribute Criteria criteria) {
        List<BoardDto> boardList = boardService.getAllBoard(criteria);
        //paginationMaker.setPageBlock(7);

        paginationMaker.setCriteria(criteria);
        paginationMaker.setTotal(boardService.getTotalCount(criteria));
        model.addAttribute("boardList",boardList);
        model.addAttribute("paginationMaker",paginationMaker);
        model.addAttribute("picked","board");
        log.info("getCurrentPage==={}",paginationMaker.getCriteria().getCurrentPage());

        return  "/board/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        //model.addAttribute("title","write");
        BoardDto boardDto = BoardDto.builder().build();
        model.addAttribute("boardDto", boardDto);
        return  "/board/write";
    }

    @PostMapping("/write")
    public String writeProcess(@Valid @ModelAttribute BoardDto boardDto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes
                               ) {
        if(bindingResult.hasErrors()){
            model.addAttribute("boardDto", boardDto);
            log.info("boardDto=={}",boardDto.toString());
            return "/board/write";
        }
        int result = boardService.insertBoard(boardDto);
        if(result>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .msg("글이 입력되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
        }
        //redirectAttributes.addFlashAttribute("name",boardDto.getName());
        //redirectAttributes.addAttribute("age",20);

        return "redirect:/";
    }


    /*@GetMapping("/view/{id}")
    @ResponseBody
    public Map<String, Object> getOneBoard(@PathVariable int id) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        Map<String, Object> resultMap = new HashMap<>();
        if(boardDto!=null){
            resultMap.put("isState","ok");
            resultMap.put("viewData",boardDto);
        } else {
            resultMap.put("isState", "fail");
            resultMap.put("viewData",null);
        }
        return  resultMap;
    }*/

    @GetMapping("/view/{id}")
    public String getOneBoard(@PathVariable int id,Model model) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        /*if(boardDto==null) {
            //오류 났음....
            throw new BoardException(ErrorCode.INVALID_REQUEST);
        }*/

        model.addAttribute("boardDto",boardDto);

        return  "/board/view";
    }

    @GetMapping("/modify/{id}")
    public String modifyBoard(@PathVariable int id,Model model) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        model.addAttribute("boardDto",boardDto);
        return  "/board/modify";
    }

    @PostMapping("/modify")
    public String modifyProcessBoard(@Valid @ModelAttribute BoardDto boardDto,
                                     BindingResult bindingResult,
                                     Model model,
                                     @RequestParam int currentPage,
                                     @RequestParam String category,
                                     @RequestParam String searchTxt,
                                     RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("boardDto", boardDto);
            return "/board/modify";
        }
        int result = boardService.modifyBoard(boardDto);
        if(result>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글수정")
                    .msg("글이 수정되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
        }
        return
                "redirect:/board/list?currentPage="+currentPage+
                        "&category="+category+
                        "&searchTxt="+searchTxt;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> deleteBoard(@PathVariable int id) {
        log.info("ajax로 넘어언 id==={}",id);
        int result = boardService.deleteBoard(id);
        Map<String, String> resultMap = new HashMap<>();
        if(result>0){
            resultMap.put("isDelete","ok");
        } else {
            resultMap.put("isDelete", "fail");
        }
        return resultMap;
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id,
                              @RequestParam(required = false) int currentPage,
                              RedirectAttributes redirectAttributes
                              ) {
        log.info("currentPage==={}",currentPage);
        int result = boardService.deleteBoard(id);

        if(result>0) {
            log.info("0보다 크다");
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글삭제")
                    .msg(id+"번째 글이 삭제되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        log.info("0보다 작다");
        return "redirect:/board/list?currentPage="+currentPage;
    }

    @PostMapping("/test")
    public String test(@RequestParam int id,
                       @RequestParam(required = false) int currentPage,
                       RedirectAttributes redirectAttributes) {
        log.info("currentPage==={}",currentPage);
        int result = boardService.deleteBoard(id);

        if(result>0) {
            log.info("0보다 크다");
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글삭제")
                    .msg(id+"번째 글이 삭제되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        log.info("0보다 작다");
        return "redirect:/board/list?currentPage="+currentPage;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam MultipartFile upload) {
        log.info("upload===={}",upload);
        log.info("originalFileName==={}",upload.getOriginalFilename());

        String originalFile = upload.getOriginalFilename(); // 이게 진짜 파일 이름...
        String renamedFile = null;
        String folder =  null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        folder = simpleDateFormat.format(now);
        File dir = new File(uploadFolder+File.separator+folder);
        if(!dir.exists()) dir.mkdirs();

        // file이름 분리하고 확장자 분리
        String fileName = originalFile.substring(0,originalFile.lastIndexOf("."));
        String ext = originalFile.substring(originalFile.lastIndexOf("."));
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strNow = simpleDateFormat.format(now);
        log.info("strNow==={}",strNow);
        renamedFile = fileName+"_"+strNow+ext;
        Path imgFilePath = Paths.get(dir+File.separator+renamedFile);

        try {
            Files.write(imgFilePath,upload.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap =  new HashMap<>();
        resultMap.put("uploaded",true);
        resultMap.put("url","/upload/"+folder+"/"+renamedFile);
        return resultMap;
    }

    /*@ExceptionHandler(BoardException.class)
    @ResponseBody
    public ErrorDto runtimeHandle(BoardException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
        return response;
    }*/
}
