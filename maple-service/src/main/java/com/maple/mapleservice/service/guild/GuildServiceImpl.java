package com.maple.mapleservice.service.guild;

import com.maple.mapleservice.dto.feign.guild.GuildBasicDto;
import com.maple.mapleservice.dto.response.Character.CharacterBasicInfoResponseDto;
import com.maple.mapleservice.entity.Character;
import com.maple.mapleservice.exception.CustomException;
import com.maple.mapleservice.exception.ErrorCode;
import com.maple.mapleservice.repository.character.CharacterRepository;
import com.maple.mapleservice.service.character.CharacterApiService;
import com.maple.mapleservice.service.character.CharacterService;
import com.maple.mapleservice.util.WorldName;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

	private final GuildApiService guildApiService;

	private final CharacterService characterService;

	private final CharacterApiService characterApiService;
	private final CharacterRepository characterRepository;

	@Override
	public List<GuildBasicDto> getGuildBasicInfosByServer(String guildName) {
		List<GuildBasicDto> guildBasicList = new ArrayList<>();
		List<String> oguildIdList = getAllOguildIdByGuildName(guildName);

		for (String oguildId : oguildIdList) {
			guildBasicList.add(guildApiService.getGuildBasic(oguildId));
		}

		System.out.println(guildBasicList.size());

		return guildBasicList;
	}

	public List<String> getAllOguildIdByGuildName(String guildName) {
		List<String> oguildIdList = new ArrayList<>();

		for (WorldName worldName : WorldName.values()) {
			Optional<String> oguildId = Optional.ofNullable(
				guildApiService.getOguildIdKey(guildName, worldName.name()));
			oguildId.ifPresent(oguildIdList::add);
		}

		return oguildIdList;
	}

	@Override
	@Cacheable(value = "guild-members", key = "#worldName + '_' + #guildName")
	public List<Character> getGuildMembers(String guildName, String worldName) {
		String oguildId = guildApiService.getOguildIdKey(guildName, worldName);
		if (oguildId == null || oguildId.isBlank()) {
			throw new CustomException(ErrorCode.GUILD_NOT_FOUND);
		}

		GuildBasicDto guildBasicDto = guildApiService.getGuildBasic(oguildId);

		List<String> characterNames = guildBasicDto.getGuild_member();

		characterService.addCharacterInformationToDB(characterNames);

		List<Character> characterList = characterRepository.getGuildMembersInfo(oguildId, characterNames);

		return characterList;
	}
}
