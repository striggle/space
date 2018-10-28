package com.space.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.space.business.commander.Commander;
import com.space.business.commander.CommanderRepository;
import com.space.util.JsonResponse;

@CrossOrigin
@Controller 
@RequestMapping("/Commanders")
public class CommanderController {
	
	@Autowired 
	private CommanderRepository commanderRepository;
	
	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllCommanders() {
		try {	
			return JsonResponse.getInstance(commanderRepository.findAll());
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Commander list failure:"+e.getMessage(), e);
		}
	}
	
	@GetMapping("/Get")
	public @ResponseBody JsonResponse getProduct(@RequestParam int id) {
		try {
			Optional<Commander> commander = commanderRepository.findById(id);
			if (commander.isPresent())
				return JsonResponse.getInstance(commander.get());
			else
				return JsonResponse.getErrorInstance("Commander not found for id: "+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting Commander:  "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addCommander(@RequestBody Commander commander) {
		return saveCommander(commander);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateCommander(@RequestBody Commander commander) {
		return saveCommander(commander);
	}

	private @ResponseBody JsonResponse saveCommander(@RequestBody Commander commander) {
		try {
			commanderRepository.save(commander);
			return JsonResponse.getInstance(commander);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeCommander(@RequestBody Commander commander) {
		try {
			commanderRepository.delete(commander);
			return JsonResponse.getInstance(commander);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
}
