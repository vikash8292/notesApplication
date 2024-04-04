package com.notesapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
    private Long noteId;
    private String title;
    
    
    @Column(length = 600)
    private String  noteText;
    @Column(name="active", columnDefinition = "BOOLEAN")
    private Boolean active=true;
    private Date createdOn;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
   

   

    public Long getNoteId() {
		return noteId;
	}


	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}


	public String getNoteText() {
		return noteText;
	}


	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Note() {
    }


 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}
    
    
}
