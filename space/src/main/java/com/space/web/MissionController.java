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

import com.space.business.mission.Mission;
import com.space.business.mission.MissionRepository;
import com.space.util.JsonResponse;

@CrossOrigin
@Controller 
@RequestMapping("/Missions")
public class MissionController {
	
	@Autowired 
	private MissionRepository missionRepository;
	
	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllMissions() {
		try {	
			return JsonResponse.getInstance(missionRepository.findAll());
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Mission list failure:"+e.getMessage(), e);
		}
	}
	
	@GetMapping("/Get")
	public @ResponseBody JsonResponse getMission(@RequestParam int id) {
		try {
			Optional<Mission> mission = missionRepository.findById(id);
			if (mission.isPresent())
				return JsonResponse.getInstance(mission.get());
			else
				return JsonResponse.getErrorInstance("Mission not found for id: "+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting mission:  "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addMission(@RequestBody Mission mission) {
		return saveMission(mission);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateMission(@RequestBody Mission mission) {
		return saveMission(mission);
	}

	private @ResponseBody JsonResponse saveMission(@RequestBody Mission mission) {
		try {
			missionRepository.save(mission);
			return JsonResponse.getInstance(mission);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeMission(@RequestBody Mission mission) {
		try {
			missionRepository.delete(mission);
			return JsonResponse.getInstance(mission);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

}
