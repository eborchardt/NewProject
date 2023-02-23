import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.smbUpload
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.10"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "*.txt"

    params {
        password("superSecret", "credentialsJSON:8d9a48f8-57f0-477c-9426-c2cd8288582d")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        smbUpload {
            targetUrl = """\\192.168.0.63\example1"""
            username = "example1"
            password = "credentialsJSON:84729875-c892-4417-972c-c7a245083ff2"
            sourcePath = "."
        }
        smbUpload {
            targetUrl = """\\localhost\folder"""
            username = "testuser"
            password = "credentialsJSON:19701ee5-3223-4bff-8ee1-b947c237365f"
            sourcePath = "."
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
