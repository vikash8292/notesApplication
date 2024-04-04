package com.notesapp.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.notesapp.service.NoteService;
import com.notesapp.service.UserService;

@Component
public class NotesManager {

	@Autowired
	NoteService noteService;
	
	@Autowired
	UserService userService;
	
	//@Scheduled(cron = "0 0 * * * *")
	@Scheduled(fixedRate=60*60*1000)
	public void removeExtraNotes() {
		List<Long> userIds= userService.getUserIds();
		userIds.forEach(id->{
			List<Long> noteIds= noteService.getExcessNotesOfUser(id);
			noteService.deleteNotes(noteIds);
		});
	}
}
