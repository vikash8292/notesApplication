package com.notesapp.service;


import org.springframework.http.ResponseEntity;

import com.notesapp.model.Note;
import com.notesapp.model.User;

import java.util.List;
import java.util.Set;

public interface NoteService {

    public Note addNote(Note Note);
    public Note updateNote(Note Note);
    public List<Note> getNotes();
    public Note getNote(Long NoteId);
    public void deleteNote(Long NoteId);

    public List<Note> getNotesOfUser(User user);

    public List<Note> getActiveNotes(User user);
    
    
   public List<Long> getExcessNotesOfUser(long userId);
    public void deleteNotes(List<Long> id) ;
   
   
}
