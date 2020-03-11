
:openurl
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto runcrud
echo.
echo Could not open url.
goto fail

:runcrud
./runcrud.bat
if "%ERRORLEVEL%" == "0" goto end
goto fail

:fail
echo.
echo There were errors

:end
echo Work is finished. Tasks are showed in the browser.

