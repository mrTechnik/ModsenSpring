package library.app.webservice;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.app.domain.Book;
import library.app.domain.BookOrderID;
import library.app.domain.Usr;
import library.app.jwtutilities.JwtUtil;
import library.app.repos.BookRepo;
import library.app.repos.UsrRepo;
import library.app.requests.HttpPostJSONRequest;




@RestController
@RequestMapping(path="/service")
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UsrRepo usrRepo;

    @PostMapping(path="/add")
    public ResponseEntity<String> addNewBook(@RequestBody Book book, @RequestHeader("Authorization") String token) throws Exception{
        try{
            if(JwtUtil.isTokenValid(token)){      
                bookRepo.save(book);          
                HttpPostJSONRequest httpPostJSONRequest = new HttpPostJSONRequest();
                httpPostJSONRequest.sendHttpPostJSONReques(new BookOrderID(book.getId()));                
                return ResponseEntity.status(HttpStatus.OK).body("\nSaved");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
        } catch(Exception ex) {
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
        }
    }

    @GetMapping(path="/get/all")
    public ResponseEntity<String> getAllBooks(@RequestHeader("Authorization") String token) {
        if(JwtUtil.isTokenValid(token)){
            String response = "";
            for(Book book: bookRepo.findAll()){
                response = response + book.to_String();
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<String> getBookById(@RequestHeader("Authorization") String token, @PathVariable String id){
        if(JwtUtil.isTokenValid(token)){
            Book book = bookRepo.findById(Integer.parseInt(id)).get();
            if(book != null){
                return ResponseEntity.status(HttpStatus.OK).body("\n" + book.to_String());
            }            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
    }
    
    @GetMapping(path="/get/isbn/{isbn}")
    public ResponseEntity<String> getBookByIsbn(@RequestHeader("Authorization") String token, @PathVariable String isbn){
        if(JwtUtil.isTokenValid(token)){
            Book book = bookRepo.findByIsbnFirst(isbn);
            if(book != null){
                return ResponseEntity.status(HttpStatus.OK).body("\n" + book.to_String());
            }            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
    }
    
    @PutMapping(path="/update/{id}")
    public ResponseEntity<String> updateBook(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody Book newBook) {
        if(JwtUtil.isTokenValid(token)){
            if(newBook != null){
                newBook.setId(Integer.parseInt(id));
                bookRepo.save(newBook);  
                return ResponseEntity.status(HttpStatus.OK).body("\nUpdated");
            }            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String> deleteBook(@RequestHeader("Authorization") String token, @PathVariable String id){
        if(JwtUtil.isTokenValid(token)){
            bookRepo.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body("\nDeleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\nBad request");
    }

    @PostMapping(path="/login")
    public String GetToken(@RequestBody Usr usr) {
        Usr saved_user = usrRepo.findByLogin(usr.getLogin());
        if(Objects.equals(saved_user.getPassword(), usr.getPassword())){            
            String token = JwtUtil.generateToken(usr);
            System.out.println(token);
            saved_user.setToken(token);
            usrRepo.save(saved_user);
            return token;
        } else {
            return "\nWrong login or password";
        }        
    }

    @PostMapping(path="/logup")
    public String LogUp(@RequestBody Usr usr) {
        usrRepo.save(usr);
        return "\nSaved";
    }

}


