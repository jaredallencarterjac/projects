package com.revature.awsimageupload.profile;


import com.revature.awsimageupload.datatstore.DummyUserProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
//fake database just to store profiles
@Repository
public class UserProfileDataAccessService {

    private final DummyUserProfileData dummyUserProfileData;

    @Autowired
    public UserProfileDataAccessService(DummyUserProfileData dummyUserProfileData) {
        this.dummyUserProfileData = dummyUserProfileData;
    }

    List<UserProfile> getUserProfiles() {
        return dummyUserProfileData.getUserProfiles();
    }


}
