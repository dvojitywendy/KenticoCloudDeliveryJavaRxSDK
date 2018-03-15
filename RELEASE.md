Release Guide for Delivery JavaRX SDK

Before releasing a new version, ensure the following:

New tests have been created for any new functionality you have added
All existing tests pass
Pull latest from master branch

To release a new version of the SDK you will need to follow the below guide:

First, add the following lines to your local.properties within the Android SDK project:

bintray.user=<Username>
bintray.apikey=<API Key>
bintray.gpg.password=<GPG Password>

Username is a JCenter/ Bintray user that will be used for distributing the new version. The user must be part of the "Kentico" organization on JCenter before publishing. To request access to 'Kentico' organization please email developerscommunity@kentico.com.

API Key is the Bintray API key for this specific user, you can locate this from your user profile page.

GPG Password is used for the public/ private GPG keypair, this is used for signing the library during upload to make it compatible with Maven. You will need to generate new GPG keys using git: https://help.github.com/articles/generating-a-new-gpg-key/ and then add the keys to your user profile on JCenter. The Password is the password you created while generating your GPG keys.

Once you have the Bintray local properties in place you are ready to push a new version to Bintray. To do this do the following:

1. Open the build.gradle for each library and iterate the library version however suits the change you made.
2. Open 'Terminal' in Android Studio
3. Type 'gradlew install' and wait for 'BUILD SUCCESSFUL'
4. Type 'gradlew bintrayUpload' and wait for 'SUCCESSFUL' 
5. Login to http://bintray.com (Jcenter)
  a. Username: <Username>
  b. Password: <Password>
6. Click on the 'KenticoCloudDeliveryJavaRxSDK' repository (only visible when in the 'Kentico' organization)
7. Confirm that the artifact versions were updated
  
Now that the artifacts are up on JCenter we need to sync them over to Maven to ensure all developers will be able to have access to them. In order to do this you will need to ensure you have a Sonatype account setup and then request access to the Maven repo by sending us an email at developerscommunity@kentico.com. Once you have access to the repo you can do the following to commit your JCenter changes over to Maven

1. Go back to Bintray and login
2. Click on the name of an artifact (for example 'delivery-android')
3. Click the 'Maven Central' tab
4. Click 'Sync' ( this will synchronize the new version of the artifact to Maven )
5. Repeat steps 2 – 4 for each of the artifacts

Once you have completed these steps the artifacts should be fully up to date on both JCenter and Maven.
