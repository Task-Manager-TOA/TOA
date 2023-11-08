// https://blog.sebastiano.dev/ooga-chaka-git-hooks-to-enforce-code-quality/
/**
 * FIX: Wrong tasks formatting it can not run using ./gradlew to install hooks
 */
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


static fun isLinuxOrMacOs() {
    def osName = System.getProperty('os.name').toLowerCase(Locale.ROOT)
    return osName.contains('linux') || osName.contains('mac os') || osName.contains('macos')
}

fun copyGitHooks("copy_git_hooks", type = DefaultTask::class) {
    description 'Copies the git hooks from /git-hooks to the .git folder.'
    from("${rootDir}/git-hooks/") {
        include '**/*.sh'
        rename '(.*).sh', '$1'
    }
    into "${rootDir}/.git/hooks"
    onlyIf { isLinuxOrMacOs() }
}

fun installGitHooks("install_git_hooks", type = DefaultTask::class) {
    description 'Installs the pre-commit git hooks from /git-hooks.'
    group 'git hooks'
    workingDir rootDir
            commandLine 'chmod'
    args '-R', '+x', '.git/hooks/'
    dependsOn copyGitHooks
            onlyIf { isLinuxOrMacOs() }
    doLast {
        logger.info('Git hook installed successfully.')
    }
}

tasks.register("copy_git_hooks")
tasks.register("install_git_hooks")
