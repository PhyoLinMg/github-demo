@file:OptIn(InternalSerializationApi::class)
package dev.linmaung.data.dto

 import kotlinx.serialization.InternalSerializationApi
 import kotlinx.serialization.SerialName
 import kotlinx.serialization.Serializable


 @Serializable
data class RepoItemDto(
    @SerialName("allow_forking")
    val allowForking: Boolean? = false,
    @SerialName("archive_url")
    val archiveUrl: String? = "",
    @SerialName("archived")
    val archived: Boolean? = false,
    @SerialName("assignees_url")
    val assigneesUrl: String? = "",
    @SerialName("blobs_url")
    val blobsUrl: String? = "",
    @SerialName("branches_url")
    val branchesUrl: String? = "",
    @SerialName("clone_url")
    val cloneUrl: String? = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String? = "",
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("commits_url")
    val commitsUrl: String? = "",
    @SerialName("compare_url")
    val compareUrl: String? = "",
    @SerialName("contents_url")
    val contentsUrl: String? = "",
    @SerialName("contributors_url")
    val contributorsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("default_branch")
    val defaultBranch: String? = "",
    @SerialName("deployments_url")
    val deploymentsUrl: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("disabled")
    val disabled: Boolean? = false,
    @SerialName("downloads_url")
    val downloadsUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("fork")
    val fork: Boolean? = false,
    @SerialName("forks")
    val forks: Int? = 0,
    @SerialName("forks_count")
    val forksCount: Int? = 0,
    @SerialName("forks_url")
    val forksUrl: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String? = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String? = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String? = "",
    @SerialName("git_url")
    val gitUrl: String? = "",
    @SerialName("has_discussions")
    val hasDiscussions: Boolean? = false,
    @SerialName("has_downloads")
    val hasDownloads: Boolean? = false,
    @SerialName("has_issues")
    val hasIssues: Boolean? = false,
    @SerialName("has_pages")
    val hasPages: Boolean? = false,
    @SerialName("has_projects")
    val hasProjects: Boolean? = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean? = false,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("hooks_url")
    val hooksUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_template")
    val isTemplate: Boolean? = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String? = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String? = "",
    @SerialName("issues_url")
    val issuesUrl: String? = "",
    @SerialName("keys_url")
    val keysUrl: String? = "",
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("language")
    val language: String? = "",
    @SerialName("languages_url")
    val languagesUrl: String? = "",
    @SerialName("license")
    val license: License? = License(),
    @SerialName("merges_url")
    val mergesUrl: String? = "",
    @SerialName("milestones_url")
    val milestonesUrl: String? = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("notifications_url")
    val notificationsUrl: String? = "",
    @SerialName("open_issues")
    val openIssues: Int? = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int? = 0,
    @SerialName("owner")
    val owner: Owner? = Owner(),
    @SerialName("private")
    val isRepoPrivate: Boolean? = false,
    @SerialName("pulls_url")
    val pullsUrl: String? = "",
    @SerialName("pushed_at")
    val pushedAt: String? = "",
    @SerialName("releases_url")
    val releasesUrl: String? = "",
    @SerialName("size")
    val size: Int? = 0,
    @SerialName("ssh_url")
    val sshUrl: String? = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int? = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String? = "",
    @SerialName("statuses_url")
    val statusesUrl: String? = "",
    @SerialName("subscribers_url")
    val subscribersUrl: String? = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String? = "",
    @SerialName("svn_url")
    val svnUrl: String? = "",
    @SerialName("tags_url")
    val tagsUrl: String? = "",
    @SerialName("teams_url")
    val teamsUrl: String? = "",
    @SerialName("topics")
    val topics: List<String?>? = listOf(),
    @SerialName("trees_url")
    val treesUrl: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("visibility")
    val visibility: String? = "",
    @SerialName("watchers")
    val watchers: Int? = 0,
    @SerialName("watchers_count")
    val watchersCount: Int? = 0,
    @SerialName("web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean? = false
)