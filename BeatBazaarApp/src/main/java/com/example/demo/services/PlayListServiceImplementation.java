package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PlayList;
import com.example.demo.repositories.PlayListRepository;

@Service
public class PlayListServiceImplementation implements PlayListService
{
	@Autowired
	PlayListRepository prepo;
	
	@Override
	public String addPlaylist(PlayList playlist) {
		prepo.save(playlist);
		return "Playlist created and saaved";
	}

	@Override
	public List<PlayList> fetchPlayList() {
		List<PlayList> plist = prepo.findAll();
		return plist;
	}

	
}
