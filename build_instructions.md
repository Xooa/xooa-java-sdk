The following document uses Intellij Community Edition for builing. Developers are free to use any maven build tool to build the project.

1. Import the project into Intellij and let the IDE refresh as it downloads all the necessary plugins and dependencies.
2. From the navigation bar, goto `File > Project Structure`.
3. In the **Project Structure** window that opens up, select **Artifacts** in the left navigaton bar.
4. Click on **+** icon and select `JAR > From modules with dependencies`
5. In the **Create JAR from Modules** sub window, select **java-sdk** and click OK. The main **Project Structure** window will now be filled with project contents.
6. Click on **Apply** and close the window.
7. From the top navigation bar, goto `Build > Build Artifacts` and in the popup option, select **Build**

Two new directories will be created inside the project namely **out** and **target**. The **out** directory will contain the **.jar** file which can be added as a dependency for your project.
