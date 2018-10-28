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

import com.space.business.program.Program;
import com.space.business.program.ProgramRepository;
import com.space.util.JsonResponse;

@CrossOrigin
@Controller 
@RequestMapping("/Programs")
public class ProgramController {
	
	@Autowired 
	private ProgramRepository programRepository;
	
	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllPrograms() {
		try {	
			return JsonResponse.getInstance(programRepository.findAll());
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Program list failure:"+e.getMessage(), e);
		}
	}
	
	@GetMapping("/Get")
	public @ResponseBody JsonResponse getProgram(@RequestParam int id) {
		try {
			Optional<Program> program = programRepository.findById(id);
			if (program.isPresent())
				return JsonResponse.getInstance(program.get());
			else
				return JsonResponse.getErrorInstance("Program not found for id: "+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting program:  "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addProgram(@RequestBody Program program) {
		return saveProgram(program);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateProgram(@RequestBody Program program) {
		return saveProgram(program);
	}

	private @ResponseBody JsonResponse saveProgram(@RequestBody Program program) {
		try {
			programRepository.save(program);
			return JsonResponse.getInstance(program);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeProgram(@RequestBody Program program) {
		try {
			programRepository.delete(program);
			return JsonResponse.getInstance(program);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
}
