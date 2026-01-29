@echo off
echo ================================================
echo  Intelligent Registration System - Test Runner
echo ================================================
echo.

cd /d "%~dp0selenium-tests"

:menu
echo.
echo Select an option:
echo 1. Run ALL tests
echo 2. Run Negative Scenario tests only
echo 3. Run Positive Scenario tests only
echo 4. Run Logic Validation tests only
echo 5. Install/Update dependencies
echo 6. View latest test screenshots
echo 7. Exit
echo.

set /p choice="Enter your choice (1-7): "

if "%choice%"=="1" goto runall
if "%choice%"=="2" goto runnegative
if "%choice%"=="3" goto runpositive
if "%choice%"=="4" goto runlogic
if "%choice%"=="5" goto install
if "%choice%"=="6" goto screenshots
if "%choice%"=="7" goto end
echo Invalid choice. Please try again.
goto menu

:runall
echo.
echo Running ALL tests...
echo.
call mvn clean test
goto complete

:runnegative
echo.
echo Running Negative Scenario tests...
echo.
call mvn test -Dtest=NegativeScenarioTest
goto complete

:runpositive
echo.
echo Running Positive Scenario tests...
echo.
call mvn test -Dtest=PositiveScenarioTest
goto complete

:runlogic
echo.
echo Running Logic Validation tests...
echo.
call mvn test -Dtest=LogicValidationTest
goto complete

:install
echo.
echo Installing/Updating dependencies...
echo.
call mvn clean install -DskipTests
echo.
echo Dependencies installed successfully!
pause
goto menu

:screenshots
echo.
echo Opening screenshots folder...
start "" "test-screenshots"
goto menu

:complete
echo.
echo ================================================
echo  Test execution completed!
echo ================================================
echo.
echo Screenshots saved in: test-screenshots\
echo Test reports available in: target\surefire-reports\
echo.
pause
goto menu

:end
echo.
echo Exiting...
echo.
exit
