package com.bridgeit.fundoo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.repository.NotesRepo;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.service.INoteService;
import com.bridgeit.fundoo.service.NoteServiceImpl;
import com.bridgeit.fundoo.utility.TokenUtility;


@RunWith(SpringRunner.class)
@SpringBootTest
class NoteTest {
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
	}

//	@Rule public MockitoRule rule=MockitoJUnit.rule();
	
	@Mock
	TokenUtility tokenUtility;

	@Mock
	NotesRepo noterepository;

	@Mock
	Note note;

	@Mock
	ModelMapper mapper;

	@Mock
	Responce responce;

	@Mock
	INoteService inoteservice;
	
	
	@InjectMocks
	NoteServiceImpl noteservice;
	
	@Before
    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testCreateNote() {
		Notedto notedto = new Notedto("test", "test");      
		Note note=new Note(123, "12345", "test", "white", "test", "12/12/12",
				"12/12/12", false, false, false);
		Responce resp = new Responce(200, "note is saved", "");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmdb9Ob3RlcyIsImV4cCI6MTU2MjczODYxNSwianRpIjoiNWNlNjc1OWY2OGUyZDM0NmZlOGUyYjIyIn0.FSqDfmUxEhyQodc5eCYcEjFj4LD8r40_VIuo4e5OUIY";
		when(tokenUtility.verifyToken(token));
		when(mapper.map(notedto, Note.class)).thenReturn(note);
		when(responce.sendResponse(Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(resp);
		assertEquals(resp.getStatusCode(), noteservice.createNote(notedto, token).getStatusCode());
	}

//	@Test
//	public void check() {
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmdW5kb29Ob3RlcyIsImV4cCI6MTU2MjczODYxNSwianRpIjoiNWNlNjc1OWY2OGUyZDM0NmZlOGUyYjIyIn0.FSqDfmUxEhyQodc5eCYcEjFj4LD8r40_VIuo4e5OUIY";
//		String userId="1234567889";
//		when(tokenUtility.verifyToken(token)).thenReturn(userId);
//
//	}

//	@Test
//	public void testCreateNote2() {
//		Notedto notedto = new Notedto("test", "test");
//		Note note = new Note();
//
//	}

//	@Test
//	void test() {
//		System.out.println("started");
//	}

}
