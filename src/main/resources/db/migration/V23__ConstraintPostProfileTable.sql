ALTER TABLE POST_PROFILE
    ADD CONSTRAINT unique_profile_post
        UNIQUE (PROFILE_ID, POST_ID);