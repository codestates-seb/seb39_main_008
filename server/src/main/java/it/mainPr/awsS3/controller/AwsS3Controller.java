//package it.mainPr.awsS3.controller;
//
//import it.mainPr.awsS3.service.AwsS3Service;
//import it.mainPr.dto.global.SingleResponseDto;
//import it.mainPr.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class AwsS3Controller {
//
//    private final AwsS3Service awsS3Service;
//    private final MemberService memberService;
//
//    @Value("${cloud.aws.credentials.secretKey}")
//    private String secretKey;
//
//    @PostMapping("member/upload")
//    public ResponseEntity uploadFile(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
//
//        long memberId = memberService.getLoginMember().getMemberId();
//        System.out.printf("upload userId: {}\n", userId);
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(awsS3Service.uploadFile(userId,multipartFiles)), HttpStatus.CREATED
//        );
//    }
//}
