package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.controllers.BookController;
import com.joaogabrielferr.spring_api.data.VO.v1.BookVO;
import com.joaogabrielferr.spring_api.exceptions.RequiredObjectIsNullException;
import com.joaogabrielferr.spring_api.exceptions.ResourceNotFoundException;
import com.joaogabrielferr.spring_api.mapper.ObjectMapper;
import com.joaogabrielferr.spring_api.model.Book;
import com.joaogabrielferr.spring_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {


    @Autowired
    BookRepository repository;

    private final Logger logger = Logger.getLogger(BookService.class.getName());


    public BookVO findById(Long id){
        logger.info("Finding one book");
        var entity =  repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        BookVO vo = ObjectMapper.parseObject(entity,BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll(){
        logger.info("Finding all");

        List<BookVO> books =  ObjectMapper.parseListObject(repository.findAll(),BookVO.class);

        books.forEach(p->{
            System.out.println(p.getMyId());
            p.add(linkTo(methodOn(BookController.class).findById(p.getMyId())).withSelfRel());
        });

        return books;
    }

    public BookVO create(BookVO book){
        logger.info("Creating one book");

        if(book == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = ObjectMapper.parseObject(book,Book.class);
        BookVO vo = ObjectMapper.parseObject(repository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getMyId())).withSelfRel());
        return vo;
    }


    public BookVO update(BookVO book,Long id){
        logger.info("Updating one book");

        if(book == null){
            throw new RequiredObjectIsNullException();
        }

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + book.getMyId() + " does not exist"));

        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());

        BookVO vo = ObjectMapper.parseObject(repository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getMyId())).withSelfRel());
        return vo;
    }

    public void delete(Long id){

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " does not exist"));

        repository.delete(entity);
    }



}
