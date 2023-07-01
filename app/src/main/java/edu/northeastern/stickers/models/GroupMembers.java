package edu.northeastern.stickers.models;

public class GroupMembers {

    private String name;
    private String githubId;
    private String email;

    public GroupMembers(String name, String githubId, String email) {
        this.name = name;
        this.githubId = githubId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getGithubId() {
        return githubId;
    }

    public String getEmail() {
        return email;
    }
}
