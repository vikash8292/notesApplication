package com.notesapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.notesapp.model.Note;
import com.notesapp.model.User;
import com.notesapp.service.NoteService;
import com.notesapp.service.UserService;
import com.notesapp.service.impl.UserDetailsServiceImpl;

import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //add note
    @PostMapping("/")
    public ResponseEntity<Note> add(@RequestParam String noteTitle, @RequestParam String noteText, @RequestParam String userName)
    {
    	Note Note= new Note();
    	Note.setTitle(noteTitle);
    	Note.setNoteText(noteText);
    	System.out.println(noteTitle);
    	System.out.println(noteText);
    	System.out.println(userName);
    	Note.setCreatedOn(Calendar.getInstance().getTime());
    	Note.setUser(((User)this.userDetailsService.loadUserByUsername(userName)));
        return ResponseEntity.ok(this.noteService.addNote(Note));
    }

    //update note
    @PostMapping("/update")
    public ResponseEntity<Note> update(@RequestParam long noteId, @RequestParam String noteTitle, @RequestParam String noteText)
    {
    	Note note=  noteService.getNote(noteId);
    	note.setTitle(noteTitle);
    	note.setNoteText(noteText);
        return ResponseEntity.ok(this.noteService.updateNote(note));
    }
    //get note
    @GetMapping("/")
    public ResponseEntity<?> notes()
    {
        return ResponseEntity.ok(this.noteService.getNotes());
    }

    //get single note
    @GetMapping("/{noteId}")
    public Note Note(@PathVariable("noteId")Long noteId)
    {
        return this.noteService.getNote(noteId);
    }
    //delete
    @DeleteMapping("/{pid}")
    public void delete(@PathVariable("pid")Long pid)
    {
        this.noteService.deleteNote(pid);
    }

    @GetMapping("/user/{username}")
    public List<Note> getNotesOfUser(@PathVariable("username") String username)
    {
        User user= userService.getUser(username);
        
        return this.noteService.getNotesOfUser(user);
    }

    


}
