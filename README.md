# Glass Example Shard
A basic shard that demonstrates **optional implementations**, and **interfaces**.

# Basic Information
A lot of stuff will be more complex than other mod loaders, but it should be make easier in the future. Also keep in mind that glass is in design more complex, so there will always be a little more complex.

**[exec](exec) directory:** Used for running, and eventually will be used for compiling the mod. Allows everything to work with multiple versions of minecraft.

**impl directory(s):** Used for optional implementations (in short, sections of code that are only loaded / ran if their dependencies have already been loaded). In this example they are used for applying the shard to multiple versions.

# Setup
Instructions should be relatively clear, but don't be afraid to ask because there is very likely to be something missing.

## Downloading
Download / Clone the github repository to get the contents locally.

## Running
Kiln does not have any functionality yet in terms of generating run configurations, so here is a manual tutorial.

 - **Classpath:** {project-name}.exec.main
 - **JVM Arguments:** see [jvm arguments](#jvm-arguments)
 - **Main Class:** io.github.glassmc.loader.client.GlassClientMain
 - **Program Arguments:** see [program arguments](#program-arguments)  
 - **Working Directory:** run

### JVM Arguments
To get the proper jvm arguments, run

`./gradlew getRunConfiguration -Penvironment={client/server} -Pversion={version}`

For example, to get the correct arguments for running a 1.8.9 client.

`./gradlew getRunConfiguration -Penvironment=client -Pversion=1.8.9`

You will see a long string printed into the terminal, copy that and add it to your jvm arguments.

### Program Arguments
Most versions will work with supplying

`--accessToken 0 --version {version}`

(for offline mode)

but some versions (at least 1.7.10) require also adding

`--userProperties {}`