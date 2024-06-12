package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

@Controller
public class SongsController 
{
	@Autowired
	SongService songserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Song song)
	{
		boolean res = songserv.songExist(song.getName());
		if(res==false)
		{
			songserv.addSongs(song);
			return "songsuccess";
		}
		else
		{
			return "songfail";
		}
		
	}
	@GetMapping("/viewsongs")
	public String viewSongs(Model model)
	{
		List<Song> songList = songserv.fetchAllSongs();
		model.addAttribute("songslist", songList);
		return "displaysongs";
	}
	
	
	@GetMapping("/customerviewsongs")
	public String customerViewSongs(Model model)
	{
		List<Song> songList = songserv.fetchAllSongs();
		model.addAttribute("customersongslist", songList);
		return "customerdisplaysongs";
	}
}
