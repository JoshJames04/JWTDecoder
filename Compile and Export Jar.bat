@echo off

cd %cd%

echo.
echo --Preparing--
@echo off
del jarclasses\* /S /Q >nul 2>&1

echo.
echo --Building sources file--
dir /s /b src\*.java > sources.txt
echo .java > exclude.txt

echo.
echo --Compiling Java--
javac --module-path "%PATH_TO_FX%" --add-modules=javafx.controls,javafx.fxml,javafx.graphics -d jarclasses @sources.txt
xcopy src\*.* jarclasses /S /I /E /Y /EXCLUDE:exclude.txt

echo.
echo --Extracting JavaFX Jars--
cd jarclasses
jar xf "%PATH_TO_FX%\javafx.base.jar"
jar xf "%PATH_TO_FX%\javafx.graphics.jar"
jar xf "%PATH_TO_FX%\javafx.controls.jar"
jar xf "%PATH_TO_FX%\javafx.fxml.jar"

echo.
echo --Coping Windows dynamic link library files--
cd ..
copy "%PATH_TO_FX%\..\bin\prism*.dll" jarclasses
copy "%PATH_TO_FX%\..\bin\javafx*.dll" jarclasses

copy "%PATH_TO_FX%\..\bin\glass.dll" jarclasses
echo.
echo --Removing additional, non required files--
copy "%PATH_TO_FX%\..\bin\decora_sse.dll" jarclasses

del jarclasses\META-INF\MANIFEST.MF
del jarclasses\module-info.class
del sources.txt
echo.
del exclude.txt

echo --Starting packaging process--
mkdir libs
for %%I in ("%~dp0\.") do set ParentFolderName=%%~nxI
jar --create --file=libs/%ParentFolderName%.jar --main-class=application.Launcher -C jarclasses .

echo.
echo --Packaging into MSI Installer--
jpackager create-installer --output build --input libs --name %ParentFolderName% --main-jar %ParentFolderName%.jar --module-path "%JAVA_HOME%"\jmods --icon jarclasses\application\resources\img\JWTIcon.ico --singleton --win-shortcut --win-menu

echo.

echo ----Complete. Closing in 5 seconds----
TIMEOUT /T 5