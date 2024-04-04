package com.notesapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.notesapp.model.Note;
import com.notesapp.model.User;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note,Long> {
    public List<Note> findByUser(User user);
    public List<Note> findByActive(Boolean b);
    
    
    @Query(value="SELECT note_id FROM note WHERE user_id=?1 ORDER BY created_on DESC LIMIT 10, 1000", nativeQuery=true)
	List<Long> getNoteIdHavingMoreThanTenNotes(long userId);
    
    
  
    public void  deleteByNoteIdIn(List<Long> id);
 
}
