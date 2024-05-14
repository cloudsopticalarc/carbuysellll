package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.InspectorProfileDto;

public interface InspectorProfileService {
    public String updateProfile(InspectorProfileDto inspectorProfileDto, Integer  InspectorProfileId);

    public InspectorProfileDto getProfileById(Integer inspectorProfileId);

    public String deleteProfile(Integer inspectorProfileId);

    public InspectorProfileDto getProfileByUserId(Integer userId);
}


