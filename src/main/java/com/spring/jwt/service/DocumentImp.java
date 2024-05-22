package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IDocument;
import com.spring.jwt.dto.DocumentDto;
import com.spring.jwt.entity.Document;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.DocumentRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DocumentImp implements IDocument {
    private final UserRepository userRepository;
    private final DocumentRepo documentRepo;

    @Override
    public String addDocument(DocumentDto documentDto) {
        Optional<User> userDetails = userRepository.findById(documentDto.getUserId());
        if (userDetails.isEmpty()) {
            throw new RuntimeException("user not found by id ");
        }
        Document document = new Document(documentDto);
        documentRepo.save(document);
        return "Document uploaded successfully";


    }

    @Override
    public List<Document> getAllDocument(Integer userId, String DocumentType) {
        List<Document> documentDetails =  documentRepo.findByDocumentTypeAndUserID(userId,DocumentType);
        if (documentDetails.isEmpty()){
            throw new RuntimeException("document not found by id");
        }
        return documentDetails;


    }

    @Override
    public List<Document> getByUserId(Integer userId) {
        List<Document> document = documentRepo.findByUserId(userId);
        if(document.isEmpty()){
            throw new RuntimeException("document not found by user id");
        }
        return document;



    }


}
