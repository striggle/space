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

import com.space.business.spacecraft.Spacecraft;
import com.space.business.spacecraft.SpacecraftRepository;
import com.space.util.JsonResponse;

@CrossOrigin
@Controller 
@RequestMapping("/Spacecraft")
public class SpacecraftController {
	
	@Autowired 
	private SpacecraftRepository spacecraftRepository;
	
	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllSpacecrafts() {
		try {	
			return JsonResponse.getInstance(spacecraftRepository.findAll());
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Spacecraft list failure:"+e.getMessage(), e);
		}
	}
	
	@GetMapping("/Get")
	public @ResponseBody JsonResponse getSpacecraft(@RequestParam int id) {
		try {
			Optional<Spacecraft> spacecraft = spacecraftRepository.findById(id);
			if (spacecraft.isPresent())
				return JsonResponse.getInstance(spacecraft.get());
			else
				return JsonResponse.getErrorInstance("Spacecraft not found for id: "+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting spacecraft:  "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addSpacecraft(@RequestBody Spacecraft spacecraft) {
		return saveSpacecraft(spacecraft);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateSpacecraft(@RequestBody Spacecraft spacecraft) {
		return saveSpacecraft(spacecraft);
	}

	private @ResponseBody JsonResponse saveSpacecraft(@RequestBody Spacecraft spacecraft) {
		try {
			spacecraftRepository.save(spacecraft);
			return JsonResponse.getInstance(spacecraft);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeSpacecraft(@RequestBody Spacecraft spacecraft) {
		try {
			spacecraftRepository.delete(spacecraft);
			return JsonResponse.getInstance(spacecraft);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

}
