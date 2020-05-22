# HOWTO - end-to-end using Java firebase cloud function

Can't seem to find any documentation on how to deploy a java based firebase server function to firebase! So... I am trying to put together a document and sample.

## Java setup
1. Create a maven project:
`mvn archetype:generate -DgroupId=com.myapp.firebaase -DartifactId=useradmin-firebase -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`
1. update pom.xml: set source compatibility to 1.8 (that’s min of what Firebase supports)
1. setup a .gitignore


## Firebase setup
1. Add `com.google.firebase` dependency to `pom.xml` (see https://firebase.google.com/docs/admin/setup#java)
1. Download newly added dependencies (my IDE - IntelliJ IDEA does it for me).
1. Generate/download and setup your private key json file (many tutorials already available on how to do this). 
1. Add private key json to .gitignore.

## Firebase admin code
1. Write your code into the App class. (https://firebase.google.com/docs/auth/admin/manage-users is a good place to start from)
1. Make sure code compiles: `mvn install`

## Deploy it
1. Install firebase cli (Follow https://firebase.google.com/docs/cli)
1. Run: `firebase use --add` this creates .firebaserc with the firebase project info.
1. Create `firebase.json` (`firebase deploy` fails without this file!)
1. Run `firebase deploy --only functions` - NOT WORKING YET :(

## Call it
TODO
