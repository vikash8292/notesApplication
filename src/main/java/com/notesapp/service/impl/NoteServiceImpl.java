package com.notesapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesapp.model.Note;
import com.notesapp.model.User;
import com.notesapp.repository.NoteRepository;
import com.notesapp.service.NoteService;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class NoteServiceImpl implements NoteService {
	
    @Autowired
    private NoteRepository noteRepository;
    
    public Note addNote(Note Note) {
    	
        return noteRepository.save(Note);
    }

    public Note updateNote(Note Note) {
        return noteRepository.save(Note);
    }

    public List<Note> getNotes() {
        return (noteRepository.findAll());
    }

    public Note getNote(Long NoteId) {
        return noteRepository.findById(NoteId).get();
    }

    public void deleteNote(Long NoteId) {

    	noteRepository.deleteById(NoteId);
    }

    
   

    
    public List<Note> getActiveNotes() {
        return noteRepository.findByActive(true);
    }

	public List<Note> getNotesOfUser(User user) {
		// TODO Auto-generated method stub
		return noteRepository.findByUser(user);
	}

	public List<Note> getActiveNotes(User user) {
		// TODO Auto-generated method stub
		 return noteRepository.findByUser(user);
	}

	@Override
	public List<Long> getExcessNotesOfUser(long userId) {
		return noteRepository.getNoteIdHavingMoreThanTenNotes(userId);
	}

	@Override
	public void deleteNotes(List<Long> ids) {
		noteRepository.deleteAllByIdInBatch(ids);
		
	}

   


}
