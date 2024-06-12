package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.PlayList;
import com.example.demo.entities.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;


@Controller
public class PlayListController 
{
	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model)
	{
		//Fetching the songs using song service
		List<Song> songslist = sserv.fetchAllSongs();
		//Adding the songs in the model
		model.addAttribute("songslist", songslist);
		//sending createplaylist
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute PlayList playlist)
	{
		//adding playlist
		pserv.addPlaylist(playlist);
		//getting the song list from playlist
		List<Song> songlist = playlist.getSong();
		
		for(Song song : songlist)
		{
			//for each song adding the playlist
			song.getPlaylist().add(playlist);
			//save it inside the db
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	@GetMapping("/viewplaylist")
	public String viewPlayList(Model model)
	{
		List<PlayList> plist = pserv.fetchPlayList();
	
		model.addAttribute("plist" , plist);
		return "viewplaylist";
	}
	
	@GetMapping("/createcustplaylist")
	public String createCustomerPlayList(Model model)
	{
		//Fetching the songs using song service
		List<Song> songslist = sserv.fetchAllSongs();
		//Adding the songs in the model
		model.addAttribute("songslist", songslist);
		//sending createplaylist
		return "createcustplaylist";
	}
	@PostMapping("/addcustplaylist")
	public String addCustomerPlaylist(@ModelAttribute PlayList playlist)
	{
		//adding playlist
		pserv.addPlaylist(playlist);
		//getting the song list from playlist
		List<Song> songlist = playlist.getSong();
		
		for(Song song : songlist)
		{
			//for each song adding the playlist
			song.getPlaylist().add(playlist);
			//save it inside the db
			sserv.updateSong(song);
		}
		
		return "custplaylistsuccess";
	}
}
