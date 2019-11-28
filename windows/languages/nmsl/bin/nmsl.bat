@echo off
Setlocal EnableDelayedExpansion
set location=%~dp0
set library=
IF EXIST "libs\" (
for %%f in (libs\*.jar) do set library=%%f;!library!
)
set "sysproperty=-Dnmsl.library=%location%..\libs\lib.nmsl"
set "launchercp=%location%..\launcher\nmsl-launcher.jar"
set "languagecp=%library%%location%..\nmsl.jar"
set "realcp=-Dtruffle.class.path.append=%languagecp%"
echo "%location%..\..\..\bin\java %realcp% -cp %launchercp% %sysproperty% com.github.wooyme.nmsl.launcher.SLMain %1"
"%location%..\..\..\bin\java" "%realcp%" -cp "%launchercp%" %sysproperty% com.github.wooyme.nmsl.launcher.SLMain %1
