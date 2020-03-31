package com.revature.awsimageupload.datatstore;

import com.revature.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class DummyUserProfileData {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static{
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "jaredcarter", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "jaredcarter1", null));

    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
