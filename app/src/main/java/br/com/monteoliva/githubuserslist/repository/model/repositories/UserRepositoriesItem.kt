package br.com.monteoliva.githubuserslist.repository.model.repositories

import br.com.monteoliva.githubuserslist.repository.model.UserItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRepositoriesItem(
    @SerializedName("allow_forking")
    @Expose val allowForking: Boolean? = null,
    @SerializedName("archive_url")
    @Expose val archiveUrl: String? = null,
    @SerializedName("archived")
    @Expose val archived: Boolean? = null,
    @SerializedName("assignees_url")
    @Expose val assigneesUrl: String? = null,
    @SerializedName("blobs_url")
    @Expose val blobsUrl: String? = null,
    @SerializedName("branches_url")
    @Expose val branchesUrl: String? = null,
    @SerializedName("clone_url")
    @Expose val cloneUrl: String? = null,
    @SerializedName("collaborators_url")
    @Expose val collaboratorsUrl: String? = null,
    @SerializedName("comments_url")
    @Expose val commentsUrl: String? = null,
    @SerializedName("commits_url")
    @Expose val commitsUrl: String? = null,
    @SerializedName("compare_url")
    @Expose val compareUrl: String? = null,
    @SerializedName("contents_url")
    @Expose val contentsUrl: String? = null,
    @SerializedName("contributors_url")
    @Expose val contributorsUrl: String? = null,
    @SerializedName("created_at")
    @Expose val createdAt: String? = null,
    @SerializedName("default_branch")
    @Expose val defaultBranch: String? = null,
    @SerializedName("deployments_url")
    @Expose val deploymentsUrl: String? = null,
    @SerializedName("description")
    @Expose val description: String? = null,
    @SerializedName("disabled")
    @Expose val disabled: Boolean? = null,
    @SerializedName("downloads_url")
    @Expose val downloadsUrl: String? = null,
    @SerializedName("events_url")
    @Expose val eventsUrl: String? = null,
    @SerializedName("fork")
    @Expose val fork: Boolean? = null,
    @SerializedName("forks")
    @Expose val forks: Int? = null,
    @SerializedName("forks_count")
    @Expose val forksCount: Int? = null,
    @SerializedName("forks_url")
    @Expose val forksUrl: String? = null,
    @SerializedName("full_name")
    @Expose val fullName: String? = null,
    @SerializedName("git_commits_url")
    @Expose val gitCommitsUrl: String? = null,
    @SerializedName("git_refs_url")
    @Expose val gitRefsUrl: String? = null,
    @SerializedName("git_tags_url")
    @Expose val gitTagsUrl: String? = null,
    @SerializedName("git_url")
    @Expose val gitUrl: String? = null,
    @SerializedName("has_discussions")
    @Expose val hasDiscussions: Boolean? = null,
    @SerializedName("has_downloads")
    @Expose val hasDownloads: Boolean? = null,
    @SerializedName("has_issues")
    @Expose val hasIssues: Boolean? = null,
    @SerializedName("has_pages")
    @Expose val hasPages: Boolean? = null,
    @SerializedName("has_projects")
    @Expose val hasProjects: Boolean? = null,
    @SerializedName("has_wiki")
    @Expose val hasWiki: Boolean? = null,
    @SerializedName("homepage")
    @Expose val homepage: String? = null,
    @SerializedName("hooks_url")
    @Expose val hooksUrl: String? = null,
    @SerializedName("html_url")
    @Expose val htmlUrl: String? = null,
    @SerializedName("id")
    @Expose val id: Int? = null,
    @SerializedName("is_template")
    @Expose val isTemplate: Boolean? = null,
    @SerializedName("issue_comment_url")
    @Expose val issueCommentUrl: String? = null,
    @SerializedName("issue_events_url")
    @Expose val issueEventsUrl: String? = null,
    @SerializedName("issues_url")
    @Expose val issuesUrl: String? = null,
    @SerializedName("keys_url")
    @Expose val keysUrl: String? = null,
    @SerializedName("labels_url")
    @Expose val labelsUrl: String? = null,
    @SerializedName("language")
    @Expose val language: String? = null,
    @SerializedName("languages_url")
    @Expose val languagesUrl: String? = null,
    @SerializedName("license")
    @Expose val license: License? = null,
    @SerializedName("merges_url")
    @Expose val mergesUrl: String? = null,
    @SerializedName("milestones_url")
    @Expose val milestonesUrl: String? = null,
    @SerializedName("mirror_url")
    @Expose val mirrorUrl: Any? = null,
    @SerializedName("name")
    @Expose val name: String? = null,
    @SerializedName("node_id")
    @Expose val nodeId: String? = null,
    @SerializedName("notifications_url")
    @Expose val notificationsUrl: String? = null,
    @SerializedName("open_issues")
    @Expose val openIssues: Int? = null,
    @SerializedName("open_issues_count")
    @Expose val openIssuesCount: Int? = null,
    @SerializedName("owner")
    @Expose val owner: UserItem? = null,
    @SerializedName("private")
    @Expose val uprivate: Boolean? = null,
    @SerializedName("pulls_url")
    @Expose val pullsUrl: String? = null,
    @SerializedName("pushed_at")
    @Expose val pushedAt: String? = null,
    @SerializedName("releases_url")
    @Expose val releasesUrl: String? = null,
    @SerializedName("size")
    @Expose val size: Int? = null,
    @SerializedName("ssh_url")
    @Expose val sshUrl: String? = null,
    @SerializedName("stargazers_count")
    @Expose val stargazersCount: Int? = null,
    @SerializedName("stargazers_url")
    @Expose val stargazersUrl: String? = null,
    @SerializedName("statuses_url")
    @Expose val statusesUrl: String? = null,
    @SerializedName("subscribers_url")
    @Expose val subscribersUrl: String? = null,
    @SerializedName("subscription_url")
    @Expose val subscriptionUrl: String? = null,
    @SerializedName("svn_url")
    @Expose val svnUrl: String? = null,
    @SerializedName("tags_url")
    @Expose val tagsUrl: String? = null,
    @SerializedName("teams_url")
    @Expose val teamsUrl: String? = null,
    @SerializedName("topics")
    @Expose val topics: MutableList<String?>? = null,
    @SerializedName("trees_url")
    @Expose val treesUrl: String? = null,
    @SerializedName("updated_at")
    @Expose val updatedAt: String? = null,
    @SerializedName("url")
    @Expose val url: String? = null,
    @SerializedName("visibility")
    @Expose val visibility: String? = null,
    @SerializedName("watchers")
    @Expose val watchers: Int? = null,
    @SerializedName("watchers_count")
    @Expose val watchersCount: Int? = null,
    @SerializedName("web_commit_signoff_required")
    @Expose val webCommitSignoffRequired: Boolean? = null
) : java.io.Serializable