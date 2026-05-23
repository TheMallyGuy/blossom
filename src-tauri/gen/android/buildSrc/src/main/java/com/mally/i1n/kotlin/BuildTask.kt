import java.io.File
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class BuildTask : DefaultTask() {
    @Input
    var rootDirRel: String? = null
    @Input
    var target: String? = null
    @Input
    var release: Boolean? = null

    @TaskAction
    fun assemble() {
        val rootDirRel = rootDirRel ?: throw GradleException("rootDirRel cannot be null")
        val target = target ?: throw GradleException("target cannot be null")
        val release = release ?: throw GradleException("release cannot be null")

        // List of commands to try. Each entry is a list of strings representing the command and its initial arguments.
        val commandsToTry = listOf(
            listOf("bun", "tauri"),
            listOf("npx", "@tauri-apps/cli"),
            listOf("npm", "run", "tauri", "--")
        )

        var lastException: Exception? = null

        for (command in commandsToTry) {
            val executableName = command[0]
            val initialArgs = command.drop(1)
            
            val executablesToTry = if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                listOf(executableName, "$executableName.exe", "$executableName.cmd", "$executableName.bat")
            } else {
                listOf(executableName)
            }

            for (exe in executablesToTry) {
                try {
                    project.exec {
                        workingDir(File(project.projectDir, rootDirRel))
                        executable(exe)
                        
                        val argsList = mutableListOf<String>()
                        argsList.addAll(initialArgs)
                        argsList.add("android")
                        argsList.add("android-studio-script")
                        
                        if (project.logger.isEnabled(LogLevel.DEBUG)) {
                            argsList.add("-vv")
                        } else if (project.logger.isEnabled(LogLevel.INFO)) {
                            argsList.add("-v")
                        }
                        
                        if (release) {
                            argsList.add("--release")
                        }
                        
                        argsList.add("--target")
                        argsList.add(target)
                        
                        args(argsList)
                    }.assertNormalExitValue()
                    return // Success!
                } catch (e: Exception) {
                    lastException = e
                    // Continue to next executable or next command
                }
            }
        }

        throw GradleException("Failed to run Tauri CLI using bun, npx, or npm. Last error: ${lastException?.message}", lastException)
    }
}
