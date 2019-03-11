Run the following command in CMD in the main directory
of the project (publish):

dotnet web.dll

or simply run RUN.bat executable.

NOTE: The C# server depends on the Java business layer running which depends
on the database layer running.

!!!!
---> NOTE2: This C# compiled project does not properly connect with the bussiness layer. We could not
figure out this, as it only works on the computer that it was deployed, so, as a backup option, 
please use the source code for when launching the
C# server.