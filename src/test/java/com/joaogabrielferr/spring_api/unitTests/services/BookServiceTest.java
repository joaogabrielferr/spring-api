package com.joaogabrielferr.spring_api.unitTests.services;

import com.joaogabrielferr.spring_api.data.VO.v1.BookVO;
import com.joaogabrielferr.spring_api.exceptions.RequiredObjectIsNullException;
import com.joaogabrielferr.spring_api.model.Book;
import com.joaogabrielferr.spring_api.repositories.BookRepository;
import com.joaogabrielferr.spring_api.services.BookService;
import com.joaogabrielferr.spring_api.unitTests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity();
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), book.getId());
        assertEquals(result.getAuthor(),book.getAuthor());
        assertEquals(result.getTitle(),book.getTitle());
        assertEquals(result.getLaunchDate(),book.getLaunchDate());
        assertEquals(result.getPrice(),book.getPrice());

    }

    @Test
    void findAll() {
        List<Book> bookList = input.mockEntityList();

        when(repository.findAll()).thenReturn(bookList);

        List<BookVO> books = service.findAll();

        assertNotNull(books);
        assertEquals(10,books.size());

        for(int i = 0;i<10;i++){
        assertNotNull(books.get(i).getMyId());
        assertNotNull(books.get(i).getLinks());
        assertTrue(books.get(i).toString().contains(String.format("links: [</api/book/v1/%d>;rel=\"self\"]", books.get(i).getMyId())));
        assertEquals(books.get(i).getMyId(), bookList.get(i).getId());
        assertEquals(books.get(i).getAuthor(),bookList.get(i).getAuthor());
        assertEquals(books.get(i).getTitle(),bookList.get(i).getTitle());
        assertEquals(books.get(i).getLaunchDate(),bookList.get(i).getLaunchDate());
        assertEquals(books.get(i).getPrice(),bookList.get(i).getPrice());

        }

    }

    @Test
    void create() {

        Book entity = input.mockEntity(1);
        Book persisted = input.mockEntity(1);


        BookVO vo = input.mockVO(1);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), persisted.getId());
        assertEquals(result.getAuthor(), persisted.getAuthor());
        assertEquals(result.getTitle(), persisted.getTitle());
        assertEquals(result.getLaunchDate(), persisted.getLaunchDate());
        assertEquals(result.getPrice(), persisted.getPrice());
    }

    @Test
    void createWithNullBook(){
        Exception ex = assertThrows(RequiredObjectIsNullException.class,()->{
            service.create(null);
        });

        String expectedMessage = "Can't persist a null object!";
        String message = ex.getMessage();

        assertTrue(message.contains(expectedMessage));
    }


    @Test
    void update() {

        Book entity = input.mockEntity(1);
        Book persisted = input.mockEntity(1);


        BookVO vo = input.mockVO(1);
        vo.setMyId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo,1L);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), persisted.getId());
        assertEquals(result.getAuthor(), persisted.getAuthor());
        assertEquals(result.getTitle(), persisted.getTitle());
        assertEquals(result.getLaunchDate(), persisted.getLaunchDate());
        assertEquals(result.getPrice(), persisted.getPrice());

    }

    @Test
    void UpdateWithNullBook(){
        Exception ex = assertThrows(RequiredObjectIsNullException.class,()->{
            service.update(null,1L);
        });

        String expectedMessage = "Can't persist a null object!";
        String message = ex.getMessage();

        assertTrue(message.contains(expectedMessage));
    }


}