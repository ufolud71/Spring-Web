./runcrud.bat

:openbrowser
start chrome
if "%ERRORLEVEL%" == "0" goto openurl
echo.
echo Could not open browser.
goto fail

:openurl
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Could not open url.
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished. Tasks are showed in the browser.

