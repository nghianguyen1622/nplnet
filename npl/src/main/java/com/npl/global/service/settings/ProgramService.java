package com.npl.global.service.settings;

import java.util.List;

import com.npl.global.dto.settings.ProgramDto;
import com.npl.global.model.settings.ProgramModel;

public interface ProgramService {

	public List<ProgramModel> listProgram();
	
	public List<ProgramDto> findMenuByUser(String userid);
}
