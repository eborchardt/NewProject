import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

version = "2022.10"

project {

    vcsRoot(HttpsGithubComJetBrainsTeamcityDocumentation)

    buildType(DSLTest)
}

object DSLTest : BuildType({
    name = "DSLTest"

    vcs {
        root(HttpsGithubComJetBrainsTeamcityDocumentation)
    }

    steps {
        script {
            scriptContent = """echo "build step""""
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }
})

object HttpsGithubComJetBrainsTeamcityDocumentation : GitVcsRoot({
    name = "https://github.com/JetBrains/teamcity-documentation"
    url = "https://github.com/JetBrains/teamcity-documentation"
    branch = "refs/heads/2022.10"
    checkoutPolicy = AgentCheckoutPolicy.SHALLOW_CLONE
})
