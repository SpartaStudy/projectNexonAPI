package com.dnf.dnfservice.service.character;

import java.util.List;

import com.dnf.dnfservice.dto.response.character.CharacterBasicInfoResponseDto;
import com.dnf.dnfservice.dto.response.character.CharacterSearchResponseDto;

public interface CharacterService {
	List<CharacterSearchResponseDto> searchCharacters(String characterName);

	CharacterBasicInfoResponseDto getCharacterBasicInfo(String serverName, String characterName);

}
